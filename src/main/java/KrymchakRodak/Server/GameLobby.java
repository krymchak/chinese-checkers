package KrymchakRodak.Server;

import java.util.HashSet;

public class GameLobby {
    private int numberOfPlayers;
    private HashSet<Player> players;
    private int lobbyID;


    public GameLobby(int numOfPlayers, int lobbyID) {
        this.numberOfPlayers = numOfPlayers;
        this.players = new HashSet<Player>(numOfPlayers);
        this.lobbyID = lobbyID;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public int getPlayerCount() {
        return this.players.size();
    }

    public int getSize() {
        return this.numberOfPlayers;
    }

    public int getLobbyID() {
        return this.lobbyID;
    }

    public void resetLobby() {
        this.players.clear();
    }

    public boolean checkIfEnoughPlayers() {
        return (this.numberOfPlayers == getPlayerCount());
    }
}
