package Test;

import Commands.BuyItem;
import Commands.Command;
import Rooms.Shop;
import ShopItems.Item;
import Player.Player;
import ShopItems.Ladder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {

    private Shop shop;
    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player();
        player.setBalance(1000);

        shop = new Shop(player) {
            @Override
            public void handlePurchase(Item item) {

                int quantity = 1;
                Command buyCommand = new BuyItem(this, item, quantity, player);
                buyCommand.execute();
            }
        };
    }

    @Test
    public void testHandlePurchaseReducesPlayerBalance() {

        Item item = new Ladder(25, "Ladder");
        int initialBalance = player.getBalance();
        shop.handlePurchase(item);

        assertTrue(player.getBalance() <= initialBalance - item.getPrice());
        assertEquals(1, player.getLadderCount());

    }
}