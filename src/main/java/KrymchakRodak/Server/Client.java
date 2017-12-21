package KrymchakRodak.Server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    private static ObjectMapper mapper = new ObjectMapper();
    private BufferedReader in;
    private PrintWriter out;
    private Socket client;
    String username;

    Client(Socket clientSocket) {
        this.client = clientSocket;
    }

    @Override
    public void run() {
        try {
            String line;
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream());
            while ((line = in.readLine()) != null) {
                JsonNode request = mapper.readTree(line);
                parseJson(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void parseJson(JsonNode node) {
        switch (node.get("RequestType").asText()) {
            case "LOGIN":
                String username = node.get("username").asText();
                if(Server.usernames.contains(username)) {
                    out.println(ServerCommunication.badLogin());
                } else {
                    Server.usernames.add(username);
                    out.println(ServerCommunication.loginSuccess());
                }
            case "JOIN_LOBBY":

        }

    }
}
