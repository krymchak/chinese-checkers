package KrymchakRodak.Client;

import KrymchakRodak.Board.MoveInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

class ClientConnection {
    private static final int PORT = 2137;
    private ObjectMapper mapper;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /*
    connect to the server and set InputStream and OutputStream
     */
    ClientConnection() throws IOException {
        this.mapper = new ObjectMapper();
        socket = new Socket("localhost", PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /*
    attempt to login with passed username String
     */
    void login(String username) {
        ObjectNode node = mapper.createObjectNode();

        node.put("RequestType", "LOGIN");
        node.put("username", username);

        this.out.println(node.toString());
        this.out.flush();
    }

    /*
    Update game board with moves received from server
     */
    void moveChecker(int gameID, ArrayList<MoveInfo> moves) {
        ObjectNode node = mapper.createObjectNode();

        node.put("RequestType", "MOVE_CHECKER");
        node.put("GameID", gameID);

        ArrayNode moveArray = mapper.valueToTree(moves);
        node.putArray("Moves").addAll(moveArray);

        out.println(node.toString());
        out.flush();
    }

    /*
    attempt to join lobby with specified ID
     */
    void joinLobby(int lobbyID) {
        ObjectNode node = mapper.createObjectNode();

        node.put("RequestType", "JOIN_LOBBY");
        node.put("LobbyID", lobbyID);

        this.out.println(node.toString());
        this.out.flush();
    }

    /*
    wait for message from server and create JsonNode from received String
     */
    JsonNode waitForResponse() {
        String jsonString;

        try {
            if ((jsonString = in.readLine()) != null) {
                System.out.println(mapper.readTree(jsonString).toString());
                return mapper.readTree(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*void closeConnection() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
    ArrayList<MoveInfo> waitForTurn() {
        JsonNode node = waitForResponse();
        System.out.println(node.toString());
        ArrayList<MoveInfo> moves = null;
        try {
            moves = mapper.readValue(node.get("Moves").toString(), new TypeReference<ArrayList<MoveInfo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moves;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return this.username;
    }*/

    ObjectMapper getMapper() {
        return this.mapper;
    }
}
