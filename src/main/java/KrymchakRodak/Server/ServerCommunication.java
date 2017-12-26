package KrymchakRodak.Server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

class ServerCommunication {
    private static ObjectMapper mapper = new ObjectMapper();

    static void badLogin(Client client) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "BAD_LOGIN");

        client.sendMessage(jsonNode.toString());
    }

    static void loginSuccess(Client client) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "LOGIN_SUCCESS");

        client.sendMessage(jsonNode.toString());
    }

    static void updateLobby(LinkedList<Client> players, ArrayList<String> names, int lobbyID) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "UPDATE_LOBBY");
        jsonNode.put("LobbyID", lobbyID);
        ArrayNode usersArray = mapper.valueToTree(names);
        jsonNode.putArray("Users").addAll(usersArray);

        players.forEach(p -> {
            p.sendMessage(jsonNode.toString());
        });
    }

    static void startGame(LinkedList<Client> players, int lobbyID) {
        int gameSize = 0;
        ArrayList<String> checkerColor = null;
        switch (lobbyID) {
            case 0:
                gameSize = 2;
                checkerColor = new ArrayList<>();
                checkerColor.add("ORANGE");
                checkerColor.add("GREEN");
                break;
            case 1:
                gameSize = 3;
                checkerColor = new ArrayList<>();
                checkerColor.add("RED");
                checkerColor.add("GREEN");
                checkerColor.add("BLUE");
                break;
            case 2:
                gameSize = 4;
                checkerColor = new ArrayList<>();
                checkerColor.add("RED");
                checkerColor.add("BLUE");
                checkerColor.add("GREEN");
                checkerColor.add("ORANGE");
                break;
            case 3:
                gameSize = 6;
                checkerColor = new ArrayList<>();
                checkerColor.add("ORANGE");
                checkerColor.add("RED");
                checkerColor.add("BLUE");
                checkerColor.add("GREEN");
                checkerColor.add("CYAN");
                checkerColor.add("PINK");
                break;
        }
        for (Client client: players) {
            ObjectNode jsonNode = mapper.createObjectNode();
            jsonNode.put("Response", "START_GAME");
            jsonNode.put("GameSize", gameSize);
            jsonNode.put("CheckersColor", checkerColor.get(players.indexOf(client)));
            jsonNode.put("FirstTurn", players.getFirst().equals(client));
            client.sendMessage(jsonNode.toString());
        }
    }
}
