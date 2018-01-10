package KrymchakRodak.Server;

import KrymchakRodak.Game.ServerGameData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 2137;
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<ServerGameData> games = new ArrayList<>();
    static ArrayList<String> usernames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);

        while(true) {
            final Socket client = server.accept();
            System.out.println("server accept");
            Client newClient = new Client(client);
            newClient.start();
            clients.add(newClient);
        }

    }
    static void removeClient(Client client) {
        if (clients.contains(client)) {
            clients.remove(client);
        }
        if (usernames.contains(client.getUsername())) {
            usernames.remove(client.getUsername());
        }
    }

    static void interruptGame(Client client) {
        for (ServerGameData game : games) {
            if (game.getPlayers().contains(client)) {
                game.getPlayers().remove(client);
                ServerCommunication.interruptGame(game.getPlayers());
            }
        }
    }

    static ServerGameData getGame(int gameID) {
        for (ServerGameData gameData : games) {
            if (gameData.getGameID() == gameID) {
                return gameData;
            }
        }
        return null;
    }
    static void addGame(ServerGameData game) {
        games.add(game);
    }

}
