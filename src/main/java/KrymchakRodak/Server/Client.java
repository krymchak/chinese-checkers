package KrymchakRodak.Server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{
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

    private void parseJson(JsonNode node) {
        System.out.println(node.toString());
        switch (node.get("RequestType").asText()) {
            case "LOGIN":
                String username = node.get("username").asText();
                if(Server.usernames.contains(username)) {
                    ServerCommunication.badLogin(this);
                } else {
                    Server.usernames.add(username);
                    this.username = username;
                    ServerCommunication.loginSuccess(this);
                }
                break;
            case "JOIN_LOBBY":
                int lobbyID = node.get("LobbyID").asInt();
                Lobby.getInstance().addPlayerToLobby(this, lobbyID);
                break;
            case "MOVE_CHECKER":
                ServerCommunication.moveChecker();

        }

    }

    void sendMessage(String JsonString) {
        this.out.println(JsonString);
        this.out.flush();
    }

    String getUsername() {
        return this.username;
    }
}
