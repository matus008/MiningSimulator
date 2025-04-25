package Rooms;

import javax.swing.*;
import java.awt.*;

public class Shop extends JFrame {
    public Shop() {
        setTitle("SHOP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to the SHOP!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        add(label);

        setVisible(true);
    }
}
