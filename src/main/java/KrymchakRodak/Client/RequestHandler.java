package KrymchakRodak.Client;

import KrymchakRodak.Board.MoveInfo;
import KrymchakRodak.Game.ClientGameData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;

class RequestHandler {
    private ClientConnection connection = null;
    private ClientUI clientUI = null;
    private ClientGameData gameData = null;

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
            this.connection.login(username);
            parseLoginResponse(this.connection.waitForResponse());
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
        } else if (response.get("Response").asText().equals("JOIN_SUCCESS")) {

            try {

                ArrayList<String> names = this.connection.getMapper().
                        readValue(response.get("Users").toString(), new TypeReference<ArrayList<String>>() {});

                this.clientUI.setWaitPanel(names);
            } catch (IOException e) {
                e.printStackTrace();
            }

            parseJoinLobbyResponse(this.connection.waitForResponse());

        } else if (response.get("Response").asText().equals("UPDATE_LOBBY")) {

            this.clientUI.updatePlayerList(response.get("NewUser").asText());

            parseJoinLobbyResponse(this.connection.waitForResponse());

        } else if (response.get("Response").asText().equals("GAME_READY")) {

            startGame(response);

        }
    }

    private void startGame(JsonNode node) {
        this.gameData = new ClientGameData(node);

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
}
