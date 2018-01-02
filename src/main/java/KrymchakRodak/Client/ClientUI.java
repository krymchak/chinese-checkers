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
    private JTextField loginTextField = null;
    private JTextArea lobbyUsers = null;
    private Label waitLabel = null;
    private JButton loginButton = null;
    private JButton joinButton = null;
    private JButton botButton = null;
    private JComboBox<String> lobbyList = null;
    private CardLayout cardLayout = null;
    private ArrayList<Bot> bots = null;

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
        this.loginPanel.add(this.loginTextField, gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.loginPanel.add(this.loginButton, gbc);

        this.panelCards.add(this.loginPanel, "LOGIN");

        String[] lobbyStrings = {"2 Players", "3 Players", "4 Players", "6 Players"};
        this.lobbyList = new JComboBox<>(lobbyStrings);

        this.joinButton = new JButton("Play Online");
        this.botButton = new JButton("Play vs. AI");

        this.lobbyPanel = new JPanel();
        this.lobbyPanel.setLayout(new GridBagLayout());

        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.lobbyPanel.add(this.lobbyList, gbc);

        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.lobbyPanel.add(this.joinButton, gbc);

        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.lobbyPanel.add(this.botButton, gbc);
        this.panelCards.add(this.lobbyPanel, "LOBBY");

        this.waitLabel = new Label("Waiting for other players...");

        this.lobbyUsers = new JTextArea();
        this.lobbyUsers.setRows(6);

        this.waitPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.waitPanel.add(this.waitLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.waitPanel.add(this.lobbyUsers, gbc);

        this.panelCards.add(this.waitPanel, "WAIT");

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

       this.botButton.addActionListener(t ->
       new Thread(() ->
        this.requestHandler.startBotGame(this.lobbyList.getSelectedIndex())
           ).start());
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
    }

    /*
    add new String with username to lobbyUsers while waiting for game to start
     */
    void updatePlayerList(String username) {
        this.lobbyUsers.append(username + "\n");

        this.panelCards.updateUI();
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

                    this.graphicBoard.cancelMoveButton.setEnabled(false);
                    this.graphicBoard.endMoveButton.setEnabled(false);
                    this.graphicBoard.unmarkActive();
                });

        this.graphicBoard.cancelMoveButton.addActionListener(al ->
        this.graphicBoard.cancelMove());

    }

    private void addBotBoardListeners() {
        this.graphicBoard.endMoveButton.addActionListener(al -> {
            System.out.println(((Board) this.graphicBoard.getBoard()).write());
            new Thread(() -> {
                this.graphicBoard.cancelMoveButton.setEnabled(false);
                this.graphicBoard.endMoveButton.setEnabled(false);
                this.graphicBoard.unmarkActive();

                botsMove();
            }).start();


        });

        this.graphicBoard.cancelMoveButton.addActionListener(al ->
                this.graphicBoard.cancelMove());
    }

    private void botsMove() {
        for (Bot bot : this.bots) {
            startNewTurn();
            bot.moveBot();
            System.out.println(((Board) this.graphicBoard.getBoard()).write());
        }
        startNewTurn();
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
        this.graphicBoard.newTurn();
        this.graphicBoard.cancelMoveButton.setEnabled(true);
        this.graphicBoard.endMoveButton.setEnabled(true);
    }

    void createBotGame(int gameSize, ArrayList<String> checkerColor) {


        Field field;
        Color color;

        bots = new ArrayList<>();

        try {
            field = Color.class.getField(checkerColor.get(0));
            color = (Color) field.get(null);
            this.graphicBoard = new GraphicBoard(new CreatorBoard().createBoard(gameSize), color);

            for (int i = 1; i < gameSize; i++) {
                field = Color.class.getField(checkerColor.get(i));
                color = (Color) field.get(null);

                bots.add(new Bot( this.graphicBoard.getBoard(), color));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addBotBoardListeners();


        this.gameFrame = new JFrame();

        this.gameFrame.add(this.graphicBoard);
        this.gameFrame.setSize(17*40,17*40);
        this.gameFrame.setVisible(true);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startNewTurn();
    }

}
