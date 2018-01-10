package KrymchakRodak.Client;

import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Game.ClientGameData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class RequestHandler {
    private ClientConnection connection = null;
    private ClientUI clientUI = null;
    private ClientGameData gameData = null;
    private CustomLobby lobby = null;

    RequestHandler(ClientUI clientUI) {
        this.clientUI = clientUI;
    }

    /*
    attempt to connect to server
     */
    private void setConnection() {
        try {
            connection = new ClientConnection();
        } catch (Exception e) {
            this.clientUI.addErrorLabel("Error Connecting To Server.");
        }
    }

    /*
    send request to login with username
     */
    void requestLogin(String username) {
        if (this.connection == null) {
            setConnection();
        }
        if (this.connection != null) {
            if (username.length() > 20) {
                this.clientUI.addErrorLabel("Maximum username length is 20");
            } else {
                this.connection.login(username);
                parseLoginResponse(this.connection.waitForResponse());
            }
        }
    }

    /*
    send request to join lobby
     */
    void requestJoinLobby(int lobbyID) {
        this.connection.joinLobby(lobbyID);

        parseJoinLobbyResponse(this.connection.waitForResponse());
    }

    private void parseLoginResponse(JsonNode response) {
        if (response.get("Response").asText().equals("BAD_LOGIN")) {
            this.clientUI.addErrorLabel("Username Already Taken.");
        } else if (response.get("Response").asText().equals("LOGIN_SUCCESS")) {
            this.clientUI.changePanel("LOBBY");
        }
    }

    private void parseJoinLobbyResponse(JsonNode response) {
        if(response.get("Response").asText().equals("WRONG_LOBBY")) {
            this.clientUI.addErrorLabel("");
        } else if (response.get("Response").asText().equals("UPDATE_LOBBY")) {
            try {

                ArrayList<String> names = this.connection.getMapper().
                        readValue(response.get("Users").toString(), new TypeReference<ArrayList<String>>() {});

                this.clientUI.setWaitPanel(names);
            } catch (IOException e) {
                e.printStackTrace();
            }

            parseJoinLobbyResponse(this.connection.waitForResponse());

        } else if (response.get("Response").asText().equals("GAME_READY")) {

            startGame(response);

        }
    }

    private void startGame(JsonNode node) {
        this.gameData = new ClientGameData(node);

        this.clientUI.changePanel("GAME");
        this.clientUI.createGameFrame(this.gameData.getBoard());

        if (!this.gameData.getBoard().isActiveTurn()) {
            new Thread(() -> waitForTurn(this.connection.waitForResponse())).start();
        } else {
            this.clientUI.startNewTurn();
        }
    }

    /*
    wait for messages from server and update board with new moves
    until player's turn
     */
    private void waitForTurn(JsonNode node) {
        if (node.get("Response").asText().equals("UPDATE_BOARD")) {

            updateBoard(node);

            waitForTurn(connection.waitForResponse());

        } else if (node.get("Response").asText().equals("TURN_ACTIVE")) {
            clientUI.startNewTurn();

        } else if (node.get("Response").asText().equals("GAME_INTERRUPTED")) {
            clientUI.endGame();
        }
    }

    /*
    send request to notify other players about new move
     */
    void requestMoveChecker(ArrayList<MoveInfo> moves) {
        this.connection.moveChecker(this.gameData.getGameID(), moves);

        waitForTurn(this.connection.waitForResponse());
    }

    /*
    update board with new moves received from server
     */
    private void updateBoard(JsonNode movesNode) {
        ArrayList<MoveInfo> moves = null;

        try {
            //deserialize Json ArrayNode with MoveInfo objects
            moves = this.connection.getMapper().readValue(movesNode.get("Moves").toString(),
                    new TypeReference<ArrayList<MoveInfo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.clientUI.updateGraphicBoard(moves);
    }

    void requestCustomLobbiesList() {
        this.connection.getCustomLobbies();
        prepareCustomLobbiesPanel(this.connection.waitForResponse());
    }

    void leaveLobby() {
        connection.leaveLobby();
        requestCustomLobbiesList();
        this.clientUI.changePanel("LIST");
    }

    private void prepareCustomLobbiesPanel(JsonNode node) {
        Map<Integer, String> map = new HashMap<>();
        if (node.get("MapSize").asInt() > 0) {
            map = deserializeMap(node.get("Map").asText());
        }

        this.lobby = new CustomLobby(map, this.clientUI, this);
        this.clientUI.prepareCustomLobbyPanels(this.lobby);
    }

    Map<Integer, String> requestUpdateList() {
        this.connection.getUpdatedLobbyList();

        return deserializeMap(this.connection.waitForResponse().get("Map").asText());
    }

    private Map<Integer, String> deserializeMap(String jsonString) {
        Map<Integer, String> map = new HashMap<>();
        try {
            map = connection.getMapper().readValue(jsonString, TypeFactory.defaultInstance()
                    .constructMapType(HashMap.class, Integer.class, String.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    void requestCreateLobby(int gameSize, String name, CustomLobby lobby) {
        connection.createCustomLobby(gameSize, name);
        JsonNode node = connection.waitForResponse();
        if (node.get("Response").asText().equals("SUCCESS")) {
            connection.setLobbyID(node.get("LobbyID").asInt());
            this.clientUI.changePanel("ROOM");
            this.lobby.unlockButtons();
            this.lobby.updatePlayers(deserializeArrayList(node.get("Players").toString()));
            updateCustomLobby(connection.waitForResponse());
        }
    }

    void removePlayerFromLobby(String name) {
        connection.removePlayerFromLobby(name);
    }

    private ArrayList<String> deserializeArrayList(String jsonString) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            arrayList = this.connection.getMapper().
                    readValue(jsonString, new TypeReference<ArrayList<String>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    void requestJoinCustomLobby(int lobbyID) {
        connection.joinCustomLobby(lobbyID);
        updateCustomLobby(connection.waitForResponse());
    }

    private void updateCustomLobby(JsonNode node) {
        if (node.get("Response").asText().equals("UPDATE_LOBBY")) {
            this.clientUI.changePanel("ROOM");
            lobby.updatePlayers(deserializeArrayList(node.get("Users").toString()));

            updateCustomLobby(connection.waitForResponse());
        } else if (node.get("Response").asText().equals("GAME_READY")) {
            startGame(node);
        } else if (node.get("Response").asText().equals("LOBBY_DISBANDED")) {

        } else if (node.get("Response").asText().equals("KICKED")) {
            this.clientUI.changePanel("LIST");
        }

    }

    void addBot() {
        connection.addBot();
    }

    void requestStartGame(int gameSize) {
        this.connection.startGame(gameSize);
    }
}
