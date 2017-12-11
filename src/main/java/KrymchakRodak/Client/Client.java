package KrymchakRodak.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final int PORT = 2137;
    private String username = "";
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;

    public Client() throws Exception {
        socket = new Socket("localhost", PORT);
        out = new OutputStreamWriter(socket.getOutputStream(),
                StandardCharsets.UTF_8);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    void setUsername(String name) {
        this.username = name;
    }

    void loginToLobby() {
    }
}
