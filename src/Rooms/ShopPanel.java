package Rooms;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    private final Image background;

    public ShopPanel(ImageIcon backgroundImage) {
        this.background = backgroundImage.getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}