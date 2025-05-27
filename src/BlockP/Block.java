package BlockP;

import java.awt.*;
import javax.swing.*;
/**
 * Represents a single block in the mine.
 * Different types of blocks can have different textures and behaviors.
 */
public class Block {
    private BlockType type;
    private boolean mined;

    private static final Image dirtImg = new ImageIcon("src/Textures/dirt.png").getImage();
    private static final Image coalImg = new ImageIcon("src/Textures/coal.png").getImage();
    private static final Image goldImg = new ImageIcon("src/Textures/gold.png").getImage();
    private static final Image diamondImg = new ImageIcon("src/Textures/diamond.png").getImage();
    private static final Image emptyImg = new ImageIcon("src/Textures/empty.png").getImage();
    private static final Image silverImg = new ImageIcon("src/Textures/silver.png").getImage();
    private static final Image ladderImg = new ImageIcon("src/Textures/ladder.png").getImage();
    private static final Image startImg = new ImageIcon("src/Textures/start.png").getImage();
    private static final Image stoneImg = new ImageIcon("src/Textures/stone.png").getImage();
    private static final Image columnImg = new ImageIcon("src/Textures/column.png").getImage();
    /**
     * Creates a block of a certain type.
     *
     * @param type the type of the block (like DIRT, COAL....
     */
    public Block(BlockType type) {
        this.type = type;
        this.mined = false;
    }
    /**
     * Draws the block using the correct image.
     *
     * @param g the Graphics object used to draw
     * @param x the x position on screen
     * @param y the y position on screen
     * @param blockSize the size of the block
     */
    public void draw(Graphics g, int x, int y, int blockSize) {
        Image img = switch (type) {
            case DIRT -> dirtImg;
            case COAL -> coalImg;
            case GOLD -> goldImg;
            case DIAMOND -> diamondImg;
            case SILVER -> silverImg;
            case LADDER -> ladderImg;
            case START -> startImg;
            case STONE -> stoneImg;
            case COLUMN -> columnImg;
            default -> emptyImg;
        };
        g.drawImage(img, x, y, blockSize, blockSize, null);
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