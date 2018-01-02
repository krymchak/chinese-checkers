package KrymchakRodak.Game;

import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Board.GraphicBoard;
import KrymchakRodak.Bot.Bot;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Client's object used to monitor game state
 */

public class ClientGameData {
    public boolean gameActive = true;
    private GraphicBoard graphicBoard = null;
    private boolean active = false;
    private int gameID = 0;
    private ArrayList<Bot> bots = null;

    public GraphicBoard getBoard() {
        return this.graphicBoard;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = true;
    }

    public int getGameID() {
        return this.gameID;
    }

    public ArrayList<Bot> getBots() {
        return this.bots;
    }

    public ClientGameData(JsonNode node) {

        int numberOfPlayers = node.get("GameSize").asInt();
        boolean firstTurn = node.get("FirstTurn").asBoolean();


        try {
            Field field = Color.class.getField(node.get("CheckersColor").asText());
            Color checkerColor = (Color) field.get(null);
            this.graphicBoard = new GraphicBoard(new CreatorBoard().createBoard(numberOfPlayers), checkerColor);
            this.active = firstTurn;
            this.graphicBoard.setActiveTurn(firstTurn);
            this.gameID = node.get("GameID").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
