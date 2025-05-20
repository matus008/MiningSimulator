package Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Miner {
    private int x, y;
    private int speed = 5;
    private BufferedImage texture;

    public Miner(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        try {
            texture = ImageIO.read(getClass().getResource("/Textures/miner.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Miner texture failed: " + e.getMessage());
        }
    }

    public void draw(Graphics g, int drawX, int drawY) {
        if (texture != null) {
            g.drawImage(texture, drawX, drawY, 100, 100, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(drawX, drawY, 100, 100);
        }
    }

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
