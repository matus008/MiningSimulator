package Rooms;

import Player.Player;

import javax.swing.*;
import java.awt.*;

public class MainLobby extends JFrame {
    Player player;

    public MainLobby(Player player) {
        this.player = player;
        setTitle("Main Lobby");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Textures/MiningSimulator - MainLobby.jpg"));
        MainPanel contentPanel = new MainPanel(backgroundImage);
        setContentPane(contentPanel);

        //Vytvori napis
        JLabel title = new JLabel("Main Lobby", SwingConstants.CENTER);
        title.setFont(new Font("", Font.BOLD, 36));
        title.setBounds(screenSize.width / 2 - 200, 50, 400, 60);
        add(title);
        //vytvoreni Mine buttonu pro presun do jine mistosti
        JButton mineButton = new JButton("MINE");
        mineButton.setFont(new Font("", Font.BOLD, 140));
        mineButton.setForeground(new Color(89, 45, 0));
        mineButton.setContentAreaFilled(false);
        mineButton.setBorderPainted(false);
        mineButton.setFocusPainted(false);
        mineButton.setOpaque(false);

        mineButton.setSize(400, 120);
        mineButton.setLocation(228, 291);

        mineButton.addActionListener(e -> {
            new MineJFrame(player);
            dispose();
        });
        add(mineButton);
        //vytvoreni Shop buttonu pro presun do jine mistosti
        JButton shopButton = new JButton("SHOP");
        shopButton.setFont(new Font("", Font.BOLD, 130));
        shopButton.setForeground(new Color(89, 45, 0));
        shopButton.setContentAreaFilled(false);
        shopButton.setBorderPainted(false);
        shopButton.setFocusPainted(false);
        shopButton.setOpaque(false);

        shopButton.setSize(500, 120);
        shopButton.setLocation(1131, 403);

        shopButton.addActionListener(e -> {
            new Shop(player);
            dispose();
        });
        add(shopButton);

        setVisible(true);
    }

}
