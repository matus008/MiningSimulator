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

    private static final int BLOCK_SIZE = 100;
    private static final int MAP_WIDTH = 100;
    private static final int MAP_HEIGHT = 100;

    private Block[][] map;
    private Player player;

    // Pozice hráče na mapě
    private int playerX = 50;
    private int playerY = 50;

    // Kamera (počet bloků, ne pixelů)
    private int cameraWidthInBlocks;
    private int cameraHeightInBlocks;

    public MinePanel(Player player) {
        this.player = player;
        this.map = new Block[MAP_WIDTH][MAP_HEIGHT];

        generateMap();

        setFocusable(true);
        addKeyListener(this);

        // Dynamicky spočítat kolik bloků se vejde na obrazovku
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        cameraWidthInBlocks = screenSize.width / BLOCK_SIZE;
        cameraHeightInBlocks = screenSize.height / BLOCK_SIZE;
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

        int startX = playerX - cameraWidthInBlocks / 2;
        int startY = playerY - cameraHeightInBlocks / 2;

        for (int x = 0; x < cameraWidthInBlocks; x++) {
            for (int y = 0; y < cameraHeightInBlocks; y++) {
                int mapX = startX + x;
                int mapY = startY + y;

                if (mapX >= 0 && mapX < MAP_WIDTH && mapY >= 0 && mapY < MAP_HEIGHT) {
                    map[mapX][mapY].draw(g, x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        // Postava uprostřed obrazovky
        int playerScreenX = (cameraWidthInBlocks / 2) * BLOCK_SIZE;
        int playerScreenY = (cameraHeightInBlocks / 2) * BLOCK_SIZE;

        g.setColor(Color.RED);
        g.fillRect(playerScreenX, playerScreenY, BLOCK_SIZE, BLOCK_SIZE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0, dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> dy = -1;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> dy = 1;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> dx = -1;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> dx = 1;
            case KeyEvent.VK_SPACE -> mineBlock();
        }

        move(dx, dy);
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
            System.out.println("vytezeno" + player.getBackpack().size());
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
                    if (ore != null) {
                        if (player.getBackPackSize() < player.getBackpack().size()) {
                            player.addOre(ore);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Your backpack is full!");
                        }
                    }

                } catch (Exception ignored) {}
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}