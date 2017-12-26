package KrymchakRodak.Server;

import java.util.ArrayList;
import java.util.LinkedList;

class GameLobby {
    private int numberOfPlayers;
    private LinkedList<Client> players;
    private int lobbyID;

    GameLobby(int numOfPlayers, int lobbyID) {
        this.numberOfPlayers = numOfPlayers;
        this.players = new LinkedList<>();
        this.lobbyID = lobbyID;
    }

   void addPlayer(Client player) {
        this.players.add(player);
        if (enoughPlayers()) {
            ServerCommunication.startGame(getPlayers(), getLobbyID());
            resetLobby();
            //return;
        }
        //ServerCommunication.updateLobby(getPlayers(), getUsernames(), getLobbyID());
    }

    private int getPlayerCount() {
        return this.players.size();
    }

    int getLobbyID() {
        return this.lobbyID;
    }

    private void resetLobby() {
        this.players.clear();
    }

    private boolean enoughPlayers() {
        return (this.numberOfPlayers == getPlayerCount());
    }

    private ArrayList<String> getUsernames() {
        ArrayList<String> names = new ArrayList<>();

        players.forEach(c -> {
            names.add(c.getUsername());
        });

        return names;
    }

    private LinkedList<Client> getPlayers() {
        return players;
    }
}
