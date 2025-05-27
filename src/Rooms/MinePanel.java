package Rooms;

import BlockP.*;
import BlockP.Other.ColumnBlock;
import BlockP.Other.EmptyBlock;
import BlockP.Other.StartingBlock;
import BlockP.Other.StoneBlock;
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


    private int playerX = 50;
    private int playerY = 50;

    private int mineX = 0;
    private int mineY = -1;


    private int cameraWidthInBlocks;
    private int cameraHeightInBlocks;

    private boolean canMine;

    private JFrame parentFrame;

    // veci souvisejici s timto neni ode mne ale z internetu
    private String statusMessage = "";
    private long messageTimestamp = 0;
    private static final int MESSAGE_DURATION_MS = 3000;
    private void showMessage(String msg) {
        statusMessage = msg;
        messageTimestamp = System.currentTimeMillis();
        repaint();
    }

    public MinePanel(Player player, JFrame parentFrame) {
        this.player = player;
        this.map = new Block[MAP_WIDTH][MAP_HEIGHT];

        System.out.println("hrac ma --> " + player.getLadderCount() + "ZEBRIKU");
        generateMap();

        setFocusable(true);
        addKeyListener(this);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        cameraWidthInBlocks = screenSize.width / BLOCK_SIZE;
        cameraHeightInBlocks = screenSize.height / BLOCK_SIZE;

        this.parentFrame = parentFrame;
    }

    private void generateMap() {
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                map[x][y] = BlockGenerator.generateRandomBlock();
            }
        }
        int startX = MAP_WIDTH / 2;
        int startY = MAP_HEIGHT / 2;
        map[startX][startY] = new StartingBlock();
        playerX = startX;
        playerY = startY;

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


        int playerScreenX = (cameraWidthInBlocks / 2) * BLOCK_SIZE;
        int playerScreenY = (cameraHeightInBlocks / 2) * BLOCK_SIZE;
        player.getMiner().draw(g, playerScreenX, playerScreenY);
        // toto mi vymislel hodny chlapec ChatGpt
        if (!statusMessage.isEmpty() &&
                System.currentTimeMillis() - messageTimestamp < MESSAGE_DURATION_MS) {

            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, getHeight() - 40, getWidth(), 40);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(statusMessage, 20, getHeight() - 15);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int lx = 0;
        int ly = 0;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> { ly = -1; mineX = 0; mineY = -1; }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {ly = 1; mineX = 0; mineY = 1;}
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> { lx = -1; mineX = -1; mineY = 0; }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> { lx = 1; mineX = 1; mineY = 0; }
            case KeyEvent.VK_SPACE -> mineBlock();
            case KeyEvent.VK_E -> placeLadder();
            case KeyEvent.VK_R -> checkReturnToLobby();
            case KeyEvent.VK_C -> placeColumn();
        }
        move(lx, ly);
        repaint();
    }

    public void move(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        Block steppedOn = map[playerX][playerY];
        if (steppedOn.getType() == BlockType.START) {
            showMessage("You are on the start block. Press 'R' to return to the lobby.");
        }

        if (newX >= 0 && newX < MAP_WIDTH && newY >= 0 && newY < MAP_HEIGHT) {
            Block targetBlock = map[newX][newY];
            BlockType targetType = targetBlock.getType();
            BlockType currentType = map[playerX][playerY].getType();

            boolean canMove = false;

            // Volný pohyb na START
            if (targetType == BlockType.START) {
                canMove = true;
            }
            // Na EMPTY muze jen dolu, doleva, doprava
            if (targetType == BlockType.EMPTY && (dy >= 0 || dx != 0)) {
                canMove = true;
            }
            // pohyb nahoru/dolu
            if (dy != 0) {
                if (((currentType == BlockType.LADDER ) &&
                        (targetType == BlockType.LADDER || targetType == BlockType.EMPTY)) || ((currentType == BlockType.EMPTY ) &&
                        (targetType == BlockType.LADDER)) ) {
                    canMove = true;
                }
            }

            // pohyb do stran
            if (dx != 0) {
                if (targetType == BlockType.EMPTY || targetType == BlockType.LADDER || targetType == BlockType.COLUMN) {
                    canMove = true;
                }
            }
            //  true pokud uzes  do strany nebo dolů:
            if (canMove) {
                playerX = newX;
                playerY = newY;
                repaint();

                if (dx != 0 && dy == 0) {
                    fallIfNoGround();
                }
            }
        }
    }

    // metoda pomoci AI
    private void fallIfNoGround() {
        Timer fallTimer = new Timer(50, null);
        fallTimer.addActionListener(e -> {
            int belowY = playerY + 1;

            if (belowY >= MAP_HEIGHT) {
                ((Timer) e.getSource()).stop();
                return;
            }

            Block current = map[playerX][playerY];
            Block below = map[playerX][belowY];
            BlockType belowType = below.getType();
            BlockType currentType = current.getType();

            // nespadne pokud je na zebriku nebo je pod nim zebrik
            if (currentType == BlockType.LADDER || belowType == BlockType.LADDER) {
                ((Timer) e.getSource()).stop();
                return;
            }

            if (belowType == BlockType.EMPTY) {
                playerY++;
                repaint();
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        fallTimer.start();
    }

    // DeepL metoda
    private void stoneFallingCheck(int minedX, int minedY) {
        Timer delay = new Timer(2000, null); // 2 sekundy
        delay.addActionListener(e -> {
            int aboveY = minedY - 1;
            if (aboveY < 0) {
                return;
            }
            if (map[minedX][minedY].getType() == BlockType.COLUMN) {
                ((Timer) e.getSource()).stop();
                System.out.println("Funguje zastaveni");
            }

            Block above = map[minedX][aboveY];
            if (above.getType() == BlockType.STONE) {
                Block below = map[minedX][minedY];
                if (below.getType() == BlockType.EMPTY) {
                    System.out.println("nezastavilo");
                    map[minedX][aboveY] = new EmptyBlock();
                    map[minedX][minedY] = new StoneBlock(BlockType.STONE);
                    repaint();
                }
            }
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void placeColumn(){
        int targetX = playerX;
        int targetY = playerY;
        Block targetBlock = map[targetX][targetY];
        if (targetBlock.getType() != BlockType.EMPTY) {
            showMessage("You can't place Ladder there.");
            return;
        }
        if (player.getColumnCount() <= 0){
            showMessage("You can't place column there king !!.");
        }else {
            map[targetX][targetY] = new ColumnBlock(BlockType.COLUMN);
            player.useColumn();
            repaint();
        }

    }
    private void placeLadder() {
        int targetX = playerX;
        int targetY = playerY;

        if (targetX < 0 || targetX >= MAP_WIDTH || targetY < 0 || targetY >= MAP_HEIGHT) {
            return; // mimo mapu
        }

        Block target = map[targetX][targetY];

        if (target.getType() != BlockType.EMPTY) {
            showMessage("You can't place Ladder there.");
            return;
        }

        if (player.getLadderCount() <= 0) {
            showMessage("You dont have any ladders left king!");
        }else  {
            map[targetX][targetY] = new Block(BlockType.LADDER);
        player.useLadder();
        repaint();
    }
    }
    private void checkReturnToLobby() {
        Block currentBlock = map[playerX][playerY];
        if (currentBlock.getType() == BlockType.START) {
            ReturnToLobby();
        } else {
            showMessage("You must stand on the start block to return.");
        }
    }
    public void ReturnToLobby() {

        new MainLobby(player);
        parentFrame.dispose();


    }


    private void mineBlock() {
        canMine = false;
        int targetX = playerX + mineX;
        int targetY = playerY + mineY;

        if (targetX >= 0 && targetX < MAP_WIDTH && targetY >= 0 && targetY < MAP_HEIGHT) {
            Block current = map[targetX][targetY];
            if (!current.isMined()) {
                BlockType type = current.getType();
                int requiredUpgrades = switch (type) {
                    case SILVER -> 1;
                    case GOLD -> 3;
                    case DIAMOND -> 6;
                    default -> 0; // Coal, Dirt, etc.
                };

                if (player.getPUpgradeCounter() < requiredUpgrades) {
                    showMessage("You need " + requiredUpgrades + " Pickaxe Upgrades to mine " + type + "!");
                    canMine = false;
                }else if (current.getType() == BlockType.STONE) {
                    showMessage("You cannot mine this block!!");
                    canMine = false;
                }else {
                    canMine = true;
                }
                if (canMine) {
                    current.mine();
                    map[targetX][targetY] = new EmptyBlock();
                    System.out.println("vytezeno " + player.getBackpack().size());
                    stoneFallingCheck(targetX, targetY);
                    if (type != BlockType.DIRT  ) {

                        try {
                            Ores ore = switch (type) {
                                case COAL -> new Coal();
                                case SILVER -> new Silver();
                                case GOLD -> new Gold();
                                case DIAMOND -> new Diamond();
                                default -> null;
                            };
                            if (ore != null) {
                                if (player.getBackPackSize() > player.getBackpack().size()) {
                                    player.addOre(ore);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Your backpack is full!");
                                }
                            }
                        } catch (Exception ignored) {
                        }

                    }
                }

            }
        }

        repaint();
    }

    public Block[][] getMap() {
        return map;
    }
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
    public int getMineX() {
        return mineX;
    }
    public int getMineY() {
        return mineY;
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}