package KrymchakRodak.Game;

import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Board.GraphicBoard;
import KrymchakRodak.Board.WrongNumberOfPlayers;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.lang.reflect.Field;

public class GameData {
    private GraphicBoard graphicBoard = null;
    private boolean active = false;

    public GraphicBoard getBoard() {
        return this.graphicBoard;
    }

    public boolean isActive() {
        return active;
    }

    public GameData(JsonNode node) {
        int numberOfPlayers = node.get("GameSize").asInt();
        boolean firstTurn = node.get("FirstTurn").asBoolean();


        try {
            Field field = Color.class.getField(node.get("CheckersColor").asText());
            Color checkerColor = (Color) field.get(null);
            this.graphicBoard = new GraphicBoard(new CreatorBoard().createBoard(numberOfPlayers), checkerColor);
            this.active = firstTurn;
            System.out.println(checkerColor.toString() + firstTurn);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
