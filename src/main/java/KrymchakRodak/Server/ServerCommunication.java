package KrymchakRodak.Server;

import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Game.ServerGameData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * class provides methods to send responses to clients and notify them about changes
 */
class ServerCommunication {
    private static int gameID = 0;
    private static ObjectMapper mapper = new ObjectMapper();

    //send message about login failure
    static void badLogin(Client client) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "BAD_LOGIN");

        client.sendMessage(jsonNode.toString());
    }

    //send message about login success
    static void loginSuccess(Client client) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "LOGIN_SUCCESS");

        client.sendMessage(jsonNode.toString());
    }

    //let connected users know how many people are in lobby
    static void updateLobby(LinkedList<Client> players, ArrayList<String> names) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "UPDATE_LOBBY");
        ArrayNode usersArray = mapper.valueToTree(names);

        jsonNode.putArray("Users").addAll(usersArray);

        players.forEach(p ->
            p.sendMessage(jsonNode.toString())
        );
    }

    //start game
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
            jsonNode.put("Response", "GAME_READY");
            jsonNode.put("GameSize", gameSize);
            jsonNode.put("CheckersColor", checkerColor.get(players.indexOf(client)));
            jsonNode.put("FirstTurn", players.getFirst().equals(client));
            jsonNode.put("GameID", gameID);
            client.sendMessage(jsonNode.toString());
        }
        Server.addGame(new ServerGameData(players, gameSize, gameID++));

    }

    //notify clients about new move
    void moveChecker(int gameID, Client sender, ArrayList<MoveInfo> moves) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "UPDATE_BOARD");

        ArrayNode moveArray = mapper.valueToTree(moves);
        jsonNode.putArray("Moves").addAll(moveArray);

        LinkedList<Client> players = Server.getGame(gameID).getPlayers();

        for (Client client : players)  {
            if(players.indexOf(client) != players.indexOf(sender)) {
                client.sendMessage(jsonNode.toString());
            }
        }
    }

    /*
    notify player of new turn
     */
    static void newTurn(int gameID) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "TURN_ACTIVE");

        Server.getGame(gameID).getPlayerToNotify().sendMessage(jsonNode.toString());
    }

    static void interruptGame(LinkedList<Client> players) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "GAME_INTERRUPTED");

        players.forEach(p -> p.sendMessage(jsonNode.toString()));

    }
}
