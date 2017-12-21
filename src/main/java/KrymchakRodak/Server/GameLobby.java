package KrymchakRodak.Server;

import java.util.HashSet;

public class GameLobby {
    private int numberOfPlayers;
    private HashSet<String> players;
    private int lobbyID;

    public GameLobby(int numOfPlayers, int lobbyID) {
        this.numberOfPlayers = numOfPlayers;
        this.players = new HashSet<String>(numOfPlayers);
        this.lobbyID = lobbyID;
    }

   public void addPlayer(String player) {
        this.players.add(player);
        if (enoughPlayers()) {
            resetLobby();
        }
    }

    private int getPlayerCount() {
        return this.players.size();
    }

    public int getLobbyID() {
        return this.lobbyID;
    }

    public void resetLobby() {
        this.players.clear();
    }

    public boolean enoughPlayers() {
        return (this.numberOfPlayers == getPlayerCount());
    }
}
