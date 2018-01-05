package KrymchakRodak.Server;

import java.util.ArrayList;

/**
 * Singleton holding all GameLobby instances
 */
class Lobby {
    private static volatile Lobby instance;
    //sizes of lobbies that will be created
    private static int lobbySizes[] = {2, 3, 4, 6};
    private ArrayList<GameLobby> lobbies;

    static Lobby getInstance() {
        if (instance == null) {
            synchronized (Lobby.class) {
                if (instance == null) {
                    if (lobbySizes != null) {
                        instance = new Lobby();
                    }
                }
            }
        }

        return instance;
    }

    private Lobby() {
        initialize();
    }

    private void initialize() {
        int id = 0;
        this.lobbies = new ArrayList<>();

        for (int size : lobbySizes) {
            this.lobbies.add(new GameLobby(size, id++));
        }
    }

    void addPlayerToLobby(Client client, int lobbyID) {
        this.lobbies.forEach(lobby -> {
            if (lobby.getLobbyID() == lobbyID) {
                lobby.addPlayer(client);
            }
        });
    }

    void removePlayer(Client client) {
        for (GameLobby lobby : this.lobbies) {
            if (lobby.getPlayers().contains(client)) {
                lobby.removePlayer(client);
            }
        }
    }
}
