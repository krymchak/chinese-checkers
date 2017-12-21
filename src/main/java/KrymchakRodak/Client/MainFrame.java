package KrymchakRodak.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame{
    public MainFrame(String title) {
        super(title);

        setLayout(new GridBagLayout());
        JTextField loginTextField = new JTextField();
        loginTextField.setColumns(16);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(al -> {
            String username = loginTextField.getText();

            if(Client.connection.login(username));
        });

        GridBagConstraints gbc = new GridBagConstraints();
        Container container = getContentPane();

        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(loginTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(loginButton, gbc);
    }

    void drawNewFrame() {

    }
}
