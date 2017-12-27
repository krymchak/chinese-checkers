package KrymchakRodak.Client;

import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Game.ClientGameData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection {
    private static final int PORT = 2137;
    private  static ObjectMapper mapper = new ObjectMapper();
    private String username = "";
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    ClientConnection() throws IOException {
        socket = new Socket("localhost", PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    boolean login(String username) {
        ObjectNode node = mapper.createObjectNode();
        node.put("RequestType", "LOGIN");
        node.put("username", username);
        out.println(node.toString());
        out.flush();

        return waitForResponse().get("Response").asText().equals("LOGIN_SUCCESS");
    }

    void move(ArrayList<MoveInfo> moves, int gameID) {
        ObjectNode node = mapper.createObjectNode();
        node.put("RequestType", "MOVE_CHECKER");
        node.put("GameID", gameID);
        ArrayNode moveArray = mapper.valueToTree(moves);
        node.putArray("Moves").addAll(moveArray);

        out.println(node.toString());
        out.flush();
    }

    ClientGameData joinLobby(int lobbyID) {
        ObjectNode node = mapper.createObjectNode();
        node.put("RequestType", "JOIN_LOBBY");
        node.put("LobbyID", lobbyID);
        out.println(node.toString());
        out.flush();

        return new ClientGameData(waitForResponse());
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

    private JsonNode waitForResponse() {
        String jsonString = "";
        try {
            if ((jsonString = in.readLine()) != null) {
                return mapper.readTree(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    void closeConnection() throws IOException {
        socket.close();
        in.close();
        out.close();
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return this.username;
    }
}
