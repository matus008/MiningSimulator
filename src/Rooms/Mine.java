package Rooms;

import Player.Player;
import javax.swing.*;

public class Mine extends JFrame {
    public Mine(Player player) {
        setTitle("Mine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        MinePanel minePanel = new MinePanel(player);
        add(minePanel);
        setVisible(true);
    }
}