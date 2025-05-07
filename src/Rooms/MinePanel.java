package Rooms;

import BlockP.Block;
import BlockP.BlockGenerator;
import BlockP.BlockType;
import Player.Player;
import BlockP.Valuables.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinePanel extends JPanel implements KeyListener {

    private static final int BlockSize = 100;
    private static final int MAP_WIDTH = 100;
    private static final int MAP_HEIGHT = 100;

    private Block[][] map;
    private Player player;

    // Player position on the map
    private int playerX = 50;
    private int playerY = 50;

    public MinePanel(Player player) {
        this.player = player;
        this.map = new Block[MAP_WIDTH][MAP_HEIGHT];

        generateMap();

        setFocusable(true);
        addKeyListener(this);
    }

    private void generateMap() {
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                map[x][y] = BlockGenerator.generateRandomBlock();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cameraSize = 8; // 8x8 tiles = 800x800px
        int startX = playerX - cameraSize / 2;
        int startY = playerY - cameraSize / 2;

        for (int x = 0; x < cameraSize; x++) {
            for (int y = 0; y < cameraSize; y++) {
                int mapX = startX + x;
                int mapY = startY + y;

                if (mapX >= 0 && mapX < MAP_WIDTH && mapY >= 0 && mapY < MAP_HEIGHT) {
                    map[mapX][mapY].draw(g, x * BlockSize, y * BlockSize, BlockSize);
                }
            }
        }
        for (int i = 0; i < map.length; i++) {

        }

        // Player rectangle (center)
        g.setColor(Color.RED);
        g.fillRect((cameraSize / 2) * BlockSize, (cameraSize / 2) * BlockSize, BlockSize, BlockSize);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> move(0, -100);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> move(0, 100);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> move(-100, 0);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> move(100, 0);
            case KeyEvent.VK_SPACE -> mineBlock();
        }
        repaint();
    }

    public void move(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (newX >= 0 && newX < MAP_WIDTH && newY >= 0 && newY < MAP_HEIGHT) {
            playerX = newX;
            playerY = newY;
        }
    }

    private void mineBlock() {
        Block current = map[playerX][playerY];
        if (!current.isMined()) {
            current.mine();
            BlockType type = current.getType();
            if (type != BlockType.DIRT) {
                try {
                    Ores ore = switch (type) {
                        case COAL -> new Coal();
                        case SILVER -> new Silver();
                        case GOLD -> new Gold();
                        case DIAMOND -> new Diamond();
                        default -> null;
                    };
                    if (ore != null){
                        if (player.getBackPackSize() < player.getBackpack().size()){
                            player.addOre(ore);
                        }else {
                            JOptionPane.showMessageDialog(null,
                                    "Your back pack is full!");
                        }
                    }

                } catch (Exception ignored) {}
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}