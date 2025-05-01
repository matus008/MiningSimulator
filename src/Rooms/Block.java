package Rooms;

import java.awt.*;
import javax.swing.*;

public class Block {
    private BlockType type;
    private boolean mined;

    private static final Image dirtImg = new ImageIcon("").getImage();
    private static final Image coalImg = new ImageIcon("").getImage();
    private static final Image goldImg = new ImageIcon("").getImage();
    private static final Image diamondImg = new ImageIcon("").getImage();
    private static final Image emptyImg = new ImageIcon("").getImage();
    private static final Image silverImg = new ImageIcon("").getImage();

    public Block(BlockType type) {
        this.type = type;
        this.mined = false;
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