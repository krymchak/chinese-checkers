package KrymchakRodak.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Player {
    Socket socket;
    String name = "";
    DataInputStream is;
    DataOutputStream os;

    public Player(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }
}
