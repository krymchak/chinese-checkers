package KrymchakRodak.Server;

import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Game.ServerGameData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * class provides methods to send responses to clients and notify them about changes
 */
class ServerCommunication {
    private static int gameID = 0;
    static int lobbyID = 1000;
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

        players.forEach(p -> {
            if (!(p instanceof BotPlayer))
            p.sendMessage(jsonNode.toString());
        });
    }

    //start game
    static void startGame(LinkedList<Client> players, int gameSize) {
        ArrayList<String> checkerColor = createColorsArray(gameSize);
        for (Client client: players) {
            client.sendMessage(startGameJson(gameSize, players, client, checkerColor));
        }
        Server.addGame(new ServerGameData(players, gameSize, gameID++));

    }

    static void startCustomGame(int lobbyID, LinkedList<Client> players, int gameSize) {
        ArrayList<String> checkerColor = createColorsArray(gameSize);
        ServerGameData gameData = new ServerGameData(players, gameSize, gameID++);
        Server.addGame(gameData);
        for (Client client : players) {
            if (!(client instanceof BotPlayer)) {
                client.sendMessage(startGameJson(gameSize, players, client, checkerColor));
            } else {
                try {
                    Field field = Color.class.getField(checkerColor.get(players.indexOf(client)));
                    Color color = (Color) field.get(null);
                    ((BotPlayer) client).startBot(gameData.getBoard(), color);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String startGameJson(int gameSize,
                                            LinkedList<Client> players, Client client,
                                            ArrayList<String> checkerColor) {
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("Response", "GAME_READY");
        jsonNode.put("GameSize", gameSize);
        jsonNode.put("CheckersColor", checkerColor.get(players.indexOf(client)));
        jsonNode.put("FirstTurn", players.getFirst().equals(client));
        jsonNode.put("GameID", gameID - 1);

        return jsonNode.toString();
    }

    private static ArrayList<String> createColorsArray(int gameSize) {
        ArrayList<String> checkerColor = null;
        switch (gameSize) {
            case 2:
                checkerColor = new ArrayList<>();
                checkerColor.add("ORANGE");
                checkerColor.add("GREEN");
                break;
            case 3:

                checkerColor = new ArrayList<>();
                checkerColor.add("RED");
                checkerColor.add("GREEN");
                checkerColor.add("BLUE");
                break;
            case 4:

                checkerColor = new ArrayList<>();
                checkerColor.add("RED");
                checkerColor.add("BLUE");
                checkerColor.add("GREEN");
                checkerColor.add("ORANGE");
                break;
            case 6:
                checkerColor = new ArrayList<>();
                checkerColor.add("ORANGE");
                checkerColor.add("RED");
                checkerColor.add("BLUE");
                checkerColor.add("GREEN");
                checkerColor.add("CYAN");
                checkerColor.add("PINK");
                break;
        }

        return checkerColor;
    }

    //notify clients about new move
    static void moveChecker(int gameID, Client sender, ArrayList<MoveInfo> moves) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "UPDATE_BOARD");

        ArrayNode moveArray = mapper.valueToTree(moves);
        jsonNode.putArray("Moves").addAll(moveArray);

        LinkedList<Client> players = Server.getGame(gameID).getPlayers();

        for (Client client : players)  {
            if(players.indexOf(client) != players.indexOf(sender) && !(client instanceof BotPlayer)) {
                client.sendMessage(jsonNode.toString());
            } else {
            }
        }
    }

    /*
    notify player of new turn
     */
    static void newTurn(int gameID) {
        Client client = Server.getGame(gameID).getPlayerToNotify();
        if (!(client instanceof BotPlayer)) {

            ObjectNode jsonNode = mapper.createObjectNode();

            jsonNode.put("Response", "TURN_ACTIVE");

            client.sendMessage(jsonNode.toString());
        } else {
            ((BotPlayer) client).moveBot(gameID);
        }
    }

    static void interruptGame(LinkedList<Client> players) {
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("Response", "GAME_INTERRUPTED");

        players.forEach(p -> p.sendMessage(jsonNode.toString()));

    }

    static void sendCustomLobbiesList(Client client) {
        ObjectNode jsonNode = mapper.createObjectNode();

        Map<Integer, String> lobbies = new HashMap<>();

        for (GameLobby lobby : Lobby.getInstance().getLobbies()) {
            if (lobby instanceof CustomLobby) {
                lobbies.put(lobby.getLobbyID(), (((CustomLobby) lobby).getName()));
            }
        }

        jsonNode.put("Response", "CUSTOM_LOBBIES");
        try {
            jsonNode.put("Map", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lobbies));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        jsonNode.put("MapSize", lobbies.size());

        client.sendMessage(jsonNode.toString());

    }

    static void notifyLobbyCreated(Client client, int lobbyID) {
        ObjectNode node = mapper.createObjectNode();

        node.put("Response", "SUCCESS");
        node.put("LobbyID", lobbyID);
        ArrayNode usersArray = mapper.valueToTree(
                Lobby.getInstance().getByID(lobbyID).getUsernames());

        node.putArray("Players").addAll(usersArray);

        client.sendMessage(node.toString());
    }

    static void lobbyDisbanded(Client client) {
        ObjectNode node = mapper.createObjectNode();

        node.put("Response", "LOBBY_DISBANDED");

        client.sendMessage(node.toString());
    }

    static void removeFromLobby(Client client) {
        ObjectNode node = mapper.createObjectNode();

        node.put("Response","KICKED");

        client.sendMessage(node.toString());
    }
}
