package Rooms;

import javax.swing.*;
import java.awt.*;

public class Mine extends JFrame {
    public Mine() {
        setTitle("MINE");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to the MINE!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        add(label);

        setVisible(true);
    }
}
