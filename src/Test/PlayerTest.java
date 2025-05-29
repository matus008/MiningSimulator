package Test;

import Player.Player;
import BlockP.Valuables.Coal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testSellAllOres() {
        Player player = new Player();
        player.addOre(new Coal()); // 10 gold
        player.addOre(new Coal()); // 10 gold
        int startBalance = player.getBalance();

        player.sellAllOres();

        assertEquals(startBalance + 20, player.getBalance());
        assertEquals(0, player.getBackpack().size());
    }
    @Test
    void testBackpackUpgrade() {
        Player player = new Player();
        int oldSize = player.getBackPackSize();

        player.PlusUpgrade(new ShopItems.BackpackUpgrade(300, "BackPackUpgrade"));

        assertEquals(oldSize + 5, player.getBackPackSize());
    }
}