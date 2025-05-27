package Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Represents the player's miner character.
 * The miner has a position and a texture that gets drawn in the game.
 */
public class Miner {
    private int x;
    private int y;
    private int speed = 5;
    private BufferedImage texture;
    /**
     * Creates a miner with selected  starting position
     *
     * @param startX starting x position
     * @param startY starting y position
     */
    public Miner(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        try {
            texture = ImageIO.read(getClass().getResource("/Textures/miner.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Miner texture failed: " + e.getMessage());
        }
    }
    /**
     * Draws the miner on screen. If the texture fails to load, draws a bue square instead.
     *
     * @param g the Graphics object
     * @param drawX x position to draw
     * @param drawY y position to draw
     */
    public void draw(Graphics g, int drawX, int drawY) {
        if (texture != null) {
            g.drawImage(texture, drawX, drawY, 100, 100, null);
        } else {
            g.setColor(Color.BLUE);
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
