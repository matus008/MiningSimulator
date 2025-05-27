package Commands;

import Player.Player;
import ShopItems.*;
import Rooms.Shop;

import javax.swing.*;
/**
 * This command handles buying items from the shop.
 * It checks if the player has enough money and adds the items if the purchase is successful.
 */
public class BuyItem implements Command {
    private Shop shop;
    private Item item;
    private int quantity;
    private Player player;
    /**
     * Creates a new BuyItem command.
     *
     * @param shop the shop where the item is being bought
     * @param item the item to buy
     * @param quantity how many pieces the player wants to buy
     * @param player the player buying the item
     */
    public BuyItem(Shop shop, Item item, int quantity, Player player) {
        this.shop = shop;
        this.item = item;
        this.quantity = quantity;
        this.player = player;
    }
    /**
     * Executes the purchase.
     * Checks if the player has enough gold, subtracts it, and adds the item.
     */
    @Override
    public void execute() {
        int totalPrice = item.getPrize() * quantity;
        if (player.getBalance() >= totalPrice) {
            player.reduceBalance(totalPrice);

            //  pridani polozek podel typu
            if (item instanceof Ladder) {
                player.addLadders(quantity);
            } else if (item instanceof PicxakeUpgrade || item instanceof BackpackUpgrade) {
                player.PlusUpgrade(item);
            }else if (item instanceof Column) {
                player.addColumns(quantity);
            }

            JOptionPane.showMessageDialog(null,
                    "You bought " + quantity + "x " + item.getName() + " for " + totalPrice + " gold.; Remaining balance: " + player.getBalance());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Not enough money! You need " + totalPrice + " gold, but you have only " + player.getBalance());
        }
    }

    @Override
    public void exit() {
    }
}