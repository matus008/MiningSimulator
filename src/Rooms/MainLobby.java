package Rooms;

import Player.Player;

import javax.swing.*;
import java.awt.*;
/**
 * This class creates the Main Lobby window.
 * From here, the player can enter the mine or go to the shop.
 */
public class MainLobby extends JFrame {
    Player player;
    /**
     * Builds the main lobby window with background and two buttons:
     * one for entering the mine and one for entering the shop.
     *
     * @param player the current player using the game
     */
    public MainLobby(Player player) {
        this.player = player;
        setTitle("Main Lobby");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screenSize.width;
        int h = screenSize.height;
        // Set background image using custom panel
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Textures/MiningSimulator - MainLobby.jpg"));
        MainPanel contentPanel = new MainPanel(backgroundImage);
        setContentPane(contentPanel);

        // Title label
        JLabel title = new JLabel("Main Lobby", SwingConstants.CENTER);
        title.setFont(new Font("", Font.BOLD, 36));
        title.setBounds(screenSize.width / 2 - 200, 50, 400, 60);
        add(title);
        // Mine button
        JButton mineButton = new JButton("MINE");
        mineButton.setFont(new Font("", Font.BOLD, 100));
        mineButton.setForeground(new Color(89, 45, 0));
        mineButton.setContentAreaFilled(false);
        mineButton.setBorderPainted(false);
        mineButton.setFocusPainted(false);
        mineButton.setOpaque(false);

        mineButton.setSize(400, 120);
        mineButton.setLocation((int)(w * 0.12), (int)(h * 0.26));

        mineButton.addActionListener(e -> {
            new MineJFrame(player);
            dispose();
        });
        add(mineButton);
        // Shop button
        JButton shopButton = new JButton("SHOP");
        shopButton.setFont(new Font("", Font.BOLD, 80));
        shopButton.setForeground(new Color(89, 45, 0));
        shopButton.setContentAreaFilled(false);
        shopButton.setBorderPainted(false);
        shopButton.setFocusPainted(false);
        shopButton.setOpaque(false);

        shopButton.setSize(500, 120);
        shopButton.setLocation((int)(w * 0.55), (int)(h * 0.378));

        shopButton.addActionListener(e -> {
            new Shop(player);
            dispose();
        });
        add(shopButton);

        setVisible(true);
    }

}
