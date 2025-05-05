package Rooms;

import java.awt.*;
import javax.swing.*;

public class Block {
    private BlockType type;
    private boolean mined;

    private static final Image dirtImg = new ImageIcon("src/Rooms/Textures/dirt.png").getImage();
    private static final Image coalImg = new ImageIcon("src/Rooms/Textures/cole.png").getImage();
    private static final Image goldImg = new ImageIcon("src/Rooms/Textures/gold.png").getImage();
    private static final Image diamondImg = new ImageIcon("src/Rooms/Textures/diamond.png").getImage();
    private static final Image emptyImg = new ImageIcon("src/Rooms/Textures/dirt.png").getImage();
    private static final Image silverImg = new ImageIcon("src/Rooms/Textures/silver.png").getImage();

    public Block(BlockType type) {
        this.type = type;
        this.mined = false;
    }

    public void draw(Graphics g, int x, int y, int tileSize) {
        Image img = switch (type) {
            case DIRT ->  dirtImg;
            case COAL -> coalImg;
            case GOLD -> goldImg;
            case DIAMOND -> diamondImg;
            case SILVER -> silverImg;
            default -> emptyImg;
        };
        g.drawImage(img, x, y, tileSize, tileSize, null);
    }

    public BlockType getType() {
        return type;
    }

    public void mine() {
        this.mined = true;
    }

    public boolean isMined() {
        return mined;
    }
}