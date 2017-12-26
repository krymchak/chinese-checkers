package KrymchakRodak.Client;

import KrymchakRodak.Game.GameData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Client {
    private static ClientConnection connection = null;
    public static void main(String[] args) {
        try {
            connection = new ClientConnection();
            startApp();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void startApp() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Login");
                frame.setSize(500, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JPanel cards = new JPanel(new CardLayout());
                frame.add(cards);

                JTextField loginTextField = new JTextField();
                loginTextField.setColumns(16);
                JButton loginButton = new JButton("Login");


                GridBagConstraints gbc = new GridBagConstraints();
                JPanel loginPanel = new JPanel();
                loginPanel.setLayout(new GridBagLayout());

                gbc.gridx = 0;
                gbc.gridy = 0;
                loginPanel.add(loginTextField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                loginPanel.add(loginButton, gbc);
                cards.add(loginPanel, "LOGIN");

                JPanel lobbyPanel = new JPanel();
                lobbyPanel.setLayout(new GridBagLayout());
                String [] lobbyStrings = {"2 Players", "3 Players", "4 Players", "6 Players"};
                JComboBox<String> lobbyList = new JComboBox<>(lobbyStrings);
                JButton joinButton = new JButton("Join");

                gbc.gridx = 0;
                gbc.gridy = 0;
                lobbyPanel.add(lobbyList, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                lobbyPanel.add(joinButton, gbc);
                cards.add(lobbyPanel, "LOBBY");

                JPanel waitPanel = new JPanel();
                waitPanel.setLayout(new GridBagLayout());
                JLabel waitLabel = new JLabel("Waiting for other players...");
                waitPanel.add(waitLabel);
                cards.add(waitPanel, "WAIT");

                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, "LOGIN");


                loginButton.addActionListener(al -> {
                    String username = loginTextField.getText();

                    if(Client.connection.login(username)) {
                        Client.connection.setUsername(username);
                        cardLayout.show(cards, "LOBBY");
                    }
                });

                joinButton.addActionListener(al -> {
                    cardLayout.show(cards, "WAIT");
                    int lobbyID = lobbyList.getSelectedIndex();
                    JFrame frame2 = new JFrame();
                    GameData game = Client.connection.joinLobby(lobbyID);
                    frame2.setEnabled(game.isActive());
                    frame2.add(game.getBoard());
                    frame2.setSize(17*40,17*40);
                    frame2.setVisible(true);
                    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                });

            }
        });
    }

}
