package KrymchakRodak.Server;

import java.util.ArrayList;

public class Lobby {
    private static volatile Lobby instance;
    private static int lobbySizes[];
    private ArrayList<GameLobby> lobbies;

    public static Lobby getInstance() {
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

    public void addPlayerToLobby(Player player, int lobbySize) {
        this.lobbies.forEach(lobby -> {
            if (lobby.getSize() == lobbySize) {
                lobby.addPlayer(player);

                if (lobby.checkIfEnoughPlayers()) {
                    startNewGame(lobby);
                }
            }
        });
    }

    public void startNewGame(GameLobby gameLobby) {

    }
}
