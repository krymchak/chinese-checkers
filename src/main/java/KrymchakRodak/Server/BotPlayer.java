package KrymchakRodak.Server;

import KrymchakRodak.Board.AbstractBoard;
import KrymchakRodak.Bot.Bot;

import java.awt.*;

public class BotPlayer extends Client {
    private Bot botLogic = null;

    BotPlayer(String name) {
        super.setUsername(name);
    }

    void startBot(AbstractBoard board, Color color) {
        this.botLogic = new Bot(board, color);
    }

    public void setBoard(AbstractBoard board) {
        botLogic.setBoard(board);
    }

    void moveBot(int gameID) {
        ServerCommunication.moveChecker(gameID, this, botLogic.moveBot());
        ServerCommunication.newTurn(gameID);
    }

    @Override
    void sendMessage(String jsonString) {

    }

}
