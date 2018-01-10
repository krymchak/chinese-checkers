package KrymchakRodak.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

class CustomLobby {
    private Map<Integer, String> lobbies = null;
    private JList<String> lobbyList = null;
    private JPanel listPanel = null;
    private DefaultListModel<String> model = null;
    private DefaultListModel<String> usernameModel = null;
    private ClientUI clientUI = null;
    private RequestHandler requestHandler = null;
    private JList<String> roomPlayers = null;
    private JPanel roomPanel = null;
    private JPanel newLobbyPanel = null;
    private JButton addBotButton = null;
    private JButton startButton = null;
    private JButton removePlayerButton = null;
    private int gameSize = 0;
    private int playersInLobby = 0;

    CustomLobby (Map<Integer, String> lobbies, ClientUI clientUI, RequestHandler requestHandler) {
        this.lobbies = lobbies;
        this.clientUI = clientUI;
        this.requestHandler = requestHandler;
        createListPanel();
        createNewLobbyPanel();
        createRoomPanel();
    }

    JPanel getListPanel() {
        return this.listPanel;
    }

    JPanel getCreatePanel() {
        return this.newLobbyPanel;
    }

    JPanel getRoomPanel() {
        return  this.roomPanel;
    }

    private void createListPanel() {
        this.listPanel = new JPanel();
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton joinButton = new JButton("Join Lobby");
        JButton createButton = new JButton("Create Custom");
        JButton backButton = new JButton("Return");
        JButton refreshButton = new JButton("Refresh");
        JLabel topLabel = new JLabel("Currently available lobbies:");

        this.model = new DefaultListModel<>();
        for (Map.Entry<Integer, String> entry : this.lobbies.entrySet()) {
            this.model.addElement(entry.getValue().replaceAll("\"",""));
        }

        this.lobbyList = new JList<>(this.model);
        this.lobbyList.setFont(new Font("Arial",Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        this.listPanel.add(topLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        this.listPanel.add(backButton, gbc);

        gbc.gridx = 1;
        this.listPanel.add(refreshButton, gbc);

        gbc.gridx = 2;
        this.listPanel.add(joinButton, gbc);

        gbc.gridx = 3;
        this.listPanel.add(createButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 4;
        JScrollPane scrollPane = new JScrollPane(this.lobbyList);
        scrollPane.setPreferredSize(new Dimension(420, 275));
        this.listPanel.add(scrollPane, gbc);

        backButton.addActionListener(al ->
            this.clientUI.changePanel("LOBBY"));

        joinButton.addActionListener(al -> {
            new Thread( () -> {
            int key = 0;
            for (Map.Entry<Integer, String> entry : this.lobbies.entrySet()) {
                if (entry.getValue().replaceAll("\"", "").equals(lobbyList.getSelectedValue())) {
                    key = entry.getKey();
                    break;
                }
            }
            if (key != 0) {
                this.requestHandler.requestJoinCustomLobby(key);
            }
            }).start();
        }
        );

        createButton.addActionListener(al ->
        this.clientUI.changePanel("CREATE"));

        refreshButton.addActionListener(al ->
        updateList(this.requestHandler.requestUpdateList()));

    }

    private void createNewLobbyPanel() {
        this.newLobbyPanel = new JPanel();
        this.newLobbyPanel.setLayout(new GridBagLayout());

        JLabel promptLabel = new JLabel("Select game size:");
        JLabel textFieldLabel = new JLabel("Enter Lobby Name");

        String[] lobbyStrings = {"2 Players", "3 Players", "4 Players", "6 Players"};
        JComboBox<String> gameSizes = new JComboBox<>(lobbyStrings);
        gameSizes.setEditable(false);

        JButton createButton = new JButton("Create Lobby");
        JButton cancelButton = new JButton("Return");
        JTextField textField = new JTextField();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.newLobbyPanel.add(promptLabel, gbc);

        gbc.gridy = 4;
        this.newLobbyPanel.add(cancelButton, gbc);

        gbc.gridx = 1;
        this.newLobbyPanel.add(createButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        this.newLobbyPanel.add(textFieldLabel, gbc);

        gbc.gridy = 3;
        textField.setColumns(30);
        gbc.gridwidth = 2;
        this.newLobbyPanel.add(textField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        this.newLobbyPanel.add(gameSizes, gbc);

        cancelButton.addActionListener(al ->
        this.clientUI.changePanel("LIST"));

        createButton.addActionListener(al -> {
            final int size;
            if (gameSizes.getSelectedIndex() != 3) {
                size = gameSizes.getSelectedIndex() + 2;
            } else {
                size = 6;
            }
            this.gameSize = size;

        new Thread(() -> {
                this.requestHandler.requestCreateLobby(size, textField.getText(), this);
        })
        .start();

        });
    }

    private void createRoomPanel() {
        this.usernameModel = new DefaultListModel<>();
        this.roomPlayers = new JList<>(usernameModel);
        JLabel topLabel = new JLabel("Players waiting in lobby:");
        JButton returnButton = new JButton("Leave");
        startButton = new JButton("Start Game");
        startButton.setEnabled(false);
        addBotButton = new JButton("Add bot");
        addBotButton.setEnabled(false);
        removePlayerButton = new JButton("Remove Player");
        removePlayerButton.setEnabled(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        this.roomPanel = new JPanel(new GridBagLayout());

        this.roomPanel.add(topLabel, gbc);

        gbc.gridy = 1;
        gbc.gridheight = 2;
        this.roomPlayers.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(this.roomPlayers);
        scrollPane.setPreferredSize(new Dimension(180, 130));
        this.roomPanel.add(scrollPane, gbc);

        gbc.gridx = 1;
        this.roomPanel.add(addBotButton, gbc);

        gbc.gridx = 2;
        this.roomPanel.add(removePlayerButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.roomPanel.add(startButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.roomPanel.add(returnButton, gbc);

        returnButton.addActionListener(al -> {
            this.requestHandler.leaveLobby();
            this.gameSize = 0;
            this.playersInLobby = 0;
        });

        addBotButton.addActionListener(al -> {
            if (this.playersInLobby < this.gameSize)
                this.requestHandler.addBot();
        });

        startButton.addActionListener(al -> {
            if (this.gameSize == this.playersInLobby) {
                new Thread( () ->
                this.requestHandler.requestStartGame(this.gameSize)).start();
            }
        });

        removePlayerButton.addActionListener(al ->
        requestHandler.removePlayerFromLobby(roomPlayers.getSelectedValue()));
    }

    private void updateList(Map<Integer, String> map) {
        this.model.clear();
        this.lobbies.clear();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            this.lobbies.put(entry.getKey(), entry.getValue().replaceAll("\"", ""));
            this.model.addElement(entry.getValue().replaceAll("\"", ""));
        }
    }

    void updatePlayers(ArrayList<String> names) {
        this.usernameModel.clear();
        this.playersInLobby = names.size();

        for (String name : names) {
            this.usernameModel.addElement(name);
        }
    }

    void unlockButtons() {
        this.addBotButton.setEnabled(true);
        this.startButton.setEnabled(true);
        this.removePlayerButton.setEnabled(true);
    }
}
