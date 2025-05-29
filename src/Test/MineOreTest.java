package Test;

import BlockP.Block;
import BlockP.BlockType;
import BlockP.Other.EmptyBlock;
import Player.Player;
import Rooms.MinePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class MineOreTest {

    private Player player;
    private MinePanel minePanel;

    @BeforeEach
    public void setup() {
        player = new Player();
        JFrame testFrame = new JFrame();
        minePanel = new MinePanel(player, testFrame);
    }

    @Test
    public void testMineBlockFailsWithoutPickaxeUpgrade() {
        Block[][] map = minePanel.getMap();

        // cords infront of player
        int targetX = minePanel.getPlayerX();
        int targetY = minePanel.getPlayerY() - 1;

        // sets type to GOLD req.3upgrades
        map[targetX][targetY] = new Block(BlockType.GOLD);

        // direction of nining UP
        map[minePanel.getPlayerX()][minePanel.getPlayerY()] = new Block(BlockType.EMPTY); //must stand on smth


        try {
            minePanel.getClass().getDeclaredMethod("mineBlock").setAccessible(true);
            minePanel.getClass().getDeclaredMethod("mineBlock").invoke(minePanel);
        } catch (Exception e) {
            fail("mineBlock creation failed: " + e.getMessage());
        }

        // assert  GOLD block wasnt mined (is still in the map[][]
        assertEquals(BlockType.GOLD, map[targetX][targetY].getType(), "Block should not be mineable without required upgrade");
    }
    @Test
    public void testMineBlockSucceedsWithEnoughPickaxeUpgrade() {
        Block[][] map = minePanel.getMap();

        // sets upgrades
        player.PlusUpgrade(new ShopItems.PicxakeUpgrade(500, "PickaxeUpgrade")); // +1
        player.PlusUpgrade(new ShopItems.PicxakeUpgrade(500, "PickaxeUpgrade")); // +2
        player.PlusUpgrade(new ShopItems.PicxakeUpgrade(500, "PickaxeUpgrade")); // +3


        int targetX = minePanel.getPlayerX();
        int targetY = minePanel.getPlayerY() - 1;

        //  infront of player is again GOLD block
        map[targetX][targetY] = new Block(BlockType.GOLD);

        // standing + direction of mining
        map[minePanel.getPlayerX()][minePanel.getPlayerY()] = new Block(BlockType.EMPTY);


        try {
            minePanel.getClass().getDeclaredMethod("mineBlock").setAccessible(true);
            minePanel.getClass().getDeclaredMethod("mineBlock").invoke(minePanel);
        } catch (Exception e) {
            fail("mineBlock invocation failed: " + e.getMessage());
        }

        // assert checks if the GOLD block was mined and replaced
        assertTrue(map[targetX][targetY] instanceof EmptyBlock, "Block should be mined and replaced with EmptyBlock");
    }
}