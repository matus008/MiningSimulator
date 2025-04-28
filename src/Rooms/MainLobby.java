package Rooms;

import Player.Player;

import javax.swing.*;
import java.awt.*;

public class MainLobby extends JFrame {

    public MainLobby() {
        setTitle("Main Lobby");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // nacteni obrazku do pozadi
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Rooms/MiningSimulator - MainLobby.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // vykresli pozadi do panelu
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setLayout(null);
        setContentPane(contentPanel);

        //Vytvori napis
        JLabel title = new JLabel("Main Lobby", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(screenSize.width / 2 - 200, 50, 400, 60);
        add(title);
        //vytvoreni Mine buttonu pro presun do jine mistosti
        JButton mineButton = new JButton("MINE");
        mineButton.setFont(new Font("Arial", Font.BOLD, 24));
        mineButton.setBounds(screenSize.width / 4 - 75, screenSize.height / 2 - 50, 150, 100);
        mineButton.addActionListener(e -> {
            new Mine();
            dispose();
        });
        add(mineButton);
        //vytvoreni Shop buttonu pro presun do jine mistosti
        JButton shopButton = new JButton("SHOP");
        shopButton.setFont(new Font("Arial", Font.BOLD, 24));
        shopButton.setBounds(screenSize.width * 3 / 4 - 75, screenSize.height / 2 - 50, 150, 100);
        shopButton.addActionListener(e -> {
            new Shop(new Player());
            dispose();
        });
        add(shopButton);

        setVisible(true);
    }

}
