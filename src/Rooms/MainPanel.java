package Rooms;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final ImageIcon backgroundImage;

    public MainPanel(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage;
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}