package KrymchakRodak.Client;

import KrymchakRodak.Board.*;
import KrymchakRodak.Bot.Bot;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ClientUI extends Thread {
    private RequestHandler requestHandler = null;
    private GraphicBoard graphicBoard = null;
    private GridBagConstraints gbc = null;
    private JFrame clientFrame = null;
    private JFrame gameFrame = null;
    private JPanel panelCards = null;
    private JPanel loginPanel = null;
    private JPanel lobbyPanel = null;
    private JPanel waitPanel = null;
    private JPanel gamePanel = null;
    private JTextField loginTextField = null;
    private JTextArea lobbyUsers = null;
    private JLabel waitLabel = null;
    private JButton loginButton = null;
    private JButton joinButton = null;
    private JButton customLobbyButton = null;
    private JComboBox<String> lobbyList = null;
    private CardLayout cardLayout = null;
    private CustomLobby customLobby = null;

    /*
    Default constructor, creates new RequestHandler instance
     */
    ClientUI() {
        this.requestHandler = new RequestHandler(this);

    }

    /*
    start method of thread
     */
    @Override
    public void run() {
        prepareUI();
    }

    /*
    prepare all UI components and make clientFrame visible
     */
    private void prepareUI() {
        this.gbc = new GridBagConstraints();
        this.clientFrame = new JFrame();
        this.panelCards = new JPanel(new CardLayout());

        this.clientFrame.setSize(500, 400);
        this.clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.clientFrame.setVisible(true);

        this.loginTextField = new JTextField();
        this.loginTextField.setColumns(16);

        this.loginButton = new JButton("Login");

        this.loginPanel = new JPanel();
        this.loginPanel.setLayout(new GridBagLayout());

        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(10, 10, 10, 10);
        this.loginPanel.add(this.loginTextField, gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.loginPanel.add(this.loginButton, gbc);

        this.panelCards.add(this.loginPanel, "LOGIN");

        String[] lobbyStrings = {"2 Players", "3 Players", "4 Players", "6 Players"};
        this.lobbyList = new JComboBox<>(lobbyStrings);
        this.lobbyList.setEditable(false);

        this.joinButton = new JButton("Play Online");
        this.customLobbyButton = new JButton("Custom Game");

        this.lobbyPanel = new JPanel();
        this.lobbyPanel.setLayout(new GridBagLayout());

        this.gbc.fill = GridBagConstraints.BOTH;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 2;
        this.lobbyPanel.add(this.lobbyList, gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 1;
        this.lobbyPanel.add(this.joinButton, gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 5;
        this.gbc.insets = new Insets(50, 0, 0, 0);
        this.lobbyPanel.add(this.customLobbyButton, gbc);
        this.panelCards.add(this.lobbyPanel, "LOBBY");

        this.waitLabel = new JLabel("<html>Waiting for other players...<br>Players connected:</html>", SwingConstants.CENTER);

        this.lobbyUsers = new JTextArea();
        this.lobbyUsers.setRows(6);
        this.lobbyUsers.setColumns(15);
        this.lobbyUsers.setEditable(false);

        this.waitPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        this.waitPanel.add(this.waitLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.waitPanel.add(this.lobbyUsers, gbc);

        this.panelCards.add(this.waitPanel, "WAIT");

        this.gamePanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.gamePanel.add(new JLabel("Game in progress..."), gbc);

        this.panelCards.add(this.gamePanel, "GAME");

        this.cardLayout = (CardLayout) this.panelCards.getLayout();
        this.clientFrame.setLayout(this.cardLayout);
        this.clientFrame.add(this.panelCards);

        changePanel("LOGIN");
        addActionListeners();
    }

    /*
    add ActionListeners to buttons in clientFrame
     */
    private void addActionListeners() {
        this.loginButton.addActionListener(al -> {
            if (this.loginTextField.getText().isEmpty()) {
                addErrorLabel("Please Enter Username.");
            } else {
                this.requestHandler.requestLogin(this.loginTextField.getText());
            }
        });

       this.joinButton.addActionListener(p ->
           new Thread(() ->
               this.requestHandler.requestJoinLobby(this.lobbyList.getSelectedIndex())
           ).start()
       );

       this.customLobbyButton.addActionListener(r ->
       new Thread(() ->
            this.requestHandler.requestCustomLobbiesList())
               .start());
    }

    /*
    add error label to loginPanel if login failed
     */
    void addErrorLabel(String errorMsg) {
        JLabel errorLabel = new JLabel(errorMsg);

        if (this.loginPanel.getComponentCount() == 3) {
            this.loginPanel.remove(2);
        }

        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.loginPanel.add(errorLabel, this.gbc);
        this.panelCards.updateUI();
    }

    /*
    switch clientFrame panels, panelString is the name of JPanel, component of panelCards
     */
    void changePanel(String panelString) {
        this.cardLayout.show(panelCards, panelString);
        this.panelCards.updateUI();
    }

    private void addPanel(String panelName, JPanel panel) {
        this.panelCards.add(panel, panelName);
    }


    /*
    load waitPanel after successfully joining lobby
     */
    void setWaitPanel(ArrayList<String> names) {
        changePanel("WAIT");

        setLobbyUsers(names);

        this.panelCards.updateUI();
    }

    /*
    put usernames of players waiting in lobby into lobbyUsers JTextArea
     */
    private void setLobbyUsers(ArrayList<String> names) {
        this.lobbyUsers.setText("");

        for (String username : names) {
            this.lobbyUsers.append(username + "\n");
        }
    }

    /*
    prepare game frame, add ActionListeners to buttons from GraphicBoard
     */
    void createGameFrame(GraphicBoard graphicBoard) {
        this.graphicBoard = graphicBoard;
        addGraphicBoardListeners();

        this.gameFrame = new JFrame();

        this.gameFrame.add(this.graphicBoard);
        this.gameFrame.setSize(17*40,17*40);
        this.gameFrame.setVisible(true);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    add ActionListeners to buttons from GraphicBoard
     */
    private void addGraphicBoardListeners() {
        this.graphicBoard.endMoveButton.addActionListener(al -> {
                    new Thread(() ->
                            this.requestHandler.requestMoveChecker(this.graphicBoard.getMoves())).start();

                    this.graphicBoard.unmarkActive();
                    this.graphicBoard.cancelMoveButton.setEnabled(false);
                    this.graphicBoard.endMoveButton.setEnabled(false);
                    this.graphicBoard.setActiveTurn(false);
                });

        this.graphicBoard.cancelMoveButton.addActionListener(al ->
        this.graphicBoard.cancelMove());

    }

    /*
    update GraphicBoard with new moves
     */
    void updateGraphicBoard(ArrayList<MoveInfo> moves) {
        this.graphicBoard.moveChecker(moves);

        this.gameFrame.repaint();
    }

    /*
    allow player to move again
     */
    void startNewTurn() {

        this.graphicBoard.cancelMoveButton.setEnabled(true);
        this.graphicBoard.endMoveButton.setEnabled(true);
        this.graphicBoard.newTurn();

    }

    void endGame() {
        gameFrame.setVisible(false);
        changePanel("LOBBY");
    }

    void prepareCustomLobbyPanels(CustomLobby customLobby) {
        this.customLobby = customLobby;
        addPanel("LIST", customLobby.getListPanel());
        addPanel("CREATE", customLobby.getCreatePanel());
        addPanel("ROOM", customLobby.getRoomPanel());
        changePanel("LIST");

    }

}
