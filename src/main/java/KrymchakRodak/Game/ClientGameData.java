package KrymchakRodak.Game;

import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Board.GraphicBoard;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Class containing graphical board component
 */

public class ClientGameData {
    public boolean gameActive = true;
    private GraphicBoard graphicBoard = null;
    private boolean active = false;
    private int gameID = 0;

    public GraphicBoard getBoard() {
        return this.graphicBoard;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = true;
    }

    public int getGameID() {
        return this.gameID;
    }

    public ClientGameData(JsonNode node) {
        int numberOfPlayers = node.get("GameSize").asInt();
        boolean firstTurn = node.get("FirstTurn").asBoolean();


        try {
            Field field = Color.class.getField(node.get("CheckersColor").asText());
            Color checkerColor = (Color) field.get(null);
            this.graphicBoard = new GraphicBoard(new CreatorBoard().createBoard(numberOfPlayers), checkerColor);
            this.active = firstTurn;
            this.gameID = node.get("GameID").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
