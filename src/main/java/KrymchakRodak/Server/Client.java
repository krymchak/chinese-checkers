package KrymchakRodak.Server;

import KrymchakRodak.Board.MoveInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing client connection data, received Json requests are parsed,based on type of request,
 * in parseJson() method.
 */

public class Client extends Thread {
    private static ObjectMapper mapper = new ObjectMapper();
    private BufferedReader in;
    private PrintWriter out;
    private Socket client;
    private String username;

    Client(Socket clientSocket) {
        this.client = clientSocket;
    }

    @Override
    public void run() {
        try {
            String line;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream());
            while ((line = in.readLine()) != null) {
                JsonNode request = mapper.readTree(line);
                parseJson(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Parse message from client to create response
     * @param node received message from client
     */
    private void parseJson(JsonNode node) {
        /*
         * received message
         */
        System.out.println(node.toString());
        switch (node.get("RequestType").asText()) {
            /*
            Client requested to log in, the username provided is being checked,
            if username is not already used, then response "LOGIN_SUCCESS" is sent,
            else client need to choose different username
             */
            case "LOGIN":
                String username = node.get("username").asText();
                if(Server.usernames.contains(username)) {
                    //notify client of login failure
                    ServerCommunication.badLogin(this);
                } else {
                    Server.usernames.add(username);
                    this.username = username;
                    //notify client about login success
                    ServerCommunication.loginSuccess(this);
                }
                break;
            /*
             Message received is a request to join lobby with specified lobbyID,
             if lobby is full the game will start
             */
            case "JOIN_LOBBY":
                int lobbyID = node.get("LobbyID").asInt();
                Lobby.getInstance().addPlayerToLobby(this, lobbyID);
                break;
            /*
            Request to move checker and notify all clients playing the game
             */
            case "MOVE_CHECKER":
                int gameID = node.get("GameID").asInt();
                /*
                moves is a list with moves made by client sending MOVE_CHECKER request,
                the list will be checked to determine if all moves were allowed
                 */
                ArrayList<MoveInfo> moves = null;
                try {
                    //deserialize Json ArrayNode with MoveInfo objects
                    moves = mapper.readValue(node.get("Moves").toString(), new TypeReference<ArrayList<MoveInfo>>() {});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ServerCommunication.moveChecker(gameID, moves);

        }

    }

    /**
     * Send message, with response, back to client
     * @param JsonString JsonNode converted to string
     */
    void sendMessage(String JsonString) {
        this.out.println(JsonString);
        this.out.flush();
    }

    String getUsername() {
        return this.username;
    }
}
