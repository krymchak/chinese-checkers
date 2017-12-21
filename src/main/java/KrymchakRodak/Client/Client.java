package KrymchakRodak.Client;

import javax.swing.*;
import java.io.IOException;

public class Client {
    static ClientConnection connection = null;
    public static void main(String[] args) {
        try {
            connection = new ClientConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startApp();
        String line;
        try {
            while ((line = connection.in.readLine()) != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startApp() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainFrame("Login");
                frame.setSize(500, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
