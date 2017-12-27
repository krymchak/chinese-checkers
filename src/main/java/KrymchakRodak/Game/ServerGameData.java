package KrymchakRodak.Game;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Server.Client;

import java.util.LinkedList;

/**
 * Object used by Server to keep track of a game
 */
public class ServerGameData {
    private Board board;
    private LinkedList<Client> players = null;
    private int gameID;

    public ServerGameData(LinkedList<Client> players, int gameSize, int gameID) {
        try {
            this.board = new CreatorBoard().createBoard(gameSize);
            this.players = new LinkedList<>(players);
            this.gameID = gameID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getGameID() {
        return this.gameID;
    }

    public LinkedList<Client> getPlayers() {
        return this.players;
    }
}
