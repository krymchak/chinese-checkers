package KrymchakRodak.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 2137;
    static ArrayList<Client> clients = new ArrayList<>();
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

    Client getByUsername(String username) {
        for (Client connection: clients) {
            if(connection.username.equals(username)) {
                return connection;
            }
        }
        return null;
    }

}
