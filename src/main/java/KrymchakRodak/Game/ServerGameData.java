package KrymchakRodak.Game;

import KrymchakRodak.Board.Board;
import KrymchakRodak.Board.CreatorBoard;
import KrymchakRodak.Server.Client;

import java.util.LinkedList;

public class ServerGameData {
    private Board board;
    private LinkedList<Client> players;
    private int gameID;

    public ServerGameData(LinkedList<Client> players, int gameSize, int gameID) {
        try {
            this.board = new CreatorBoard().createBoard(gameSize);
            this.players = players;
            this.gameID = gameID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
