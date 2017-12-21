package KrymchakRodak.Client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientConnection {
    private static final int PORT = 2137;
    private  static ObjectMapper mapper = new ObjectMapper();
    private String username = "";
    private Socket socket;
    private PrintWriter out;
    BufferedReader in;

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
        if (!waitForResponse().equals("LOGIN_SUCCESS")) {
            return true;
        }
        return false;
    }

    void joinLobby(int lobbyID) {
        ObjectNode node = mapper.createObjectNode();
        node.put("RequestType", "JOIN_LOBBY");
        node.put("LobbyID", lobbyID);
        out.println(node.toString());
    }

    private String waitForResponse() {
        String jsonString;
        try {
            if ((jsonString = in.readLine()) != null) {
                JsonNode node = mapper.readTree(jsonString);
                return node.get("Response").asText();
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
}
