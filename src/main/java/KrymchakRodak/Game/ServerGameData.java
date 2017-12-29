package KrymchakRodak.Game;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Server.Client;

import java.util.LinkedList;

/**
 * Object used by Server to keep track of a game
 */
public class ServerGameData {
    private Board board = null;
    private LinkedList<Client> players = null;
    private int gameID = 0;
    private int activePlayer = 0;

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

    public Client getPlayerToNotify() {
        return this.players.get(getActivePlayer());
    }

    private int getActivePlayer() {
        this.activePlayer = ++this.activePlayer % this.players.size();

        return this.activePlayer;
    }
}
