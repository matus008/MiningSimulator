package Commands;

import Player.Player;
import ShopItems.BackpackUpgrade;
import ShopItems.Item;
import Rooms.Shop;
import ShopItems.Ladder;
import ShopItems.PicxakeUpgrade;

import javax.swing.*;

public class BuyItem implements Command {
    private Shop shop;
    private Item item;
    private int quantity;
    private Player player;

    public BuyItem(Shop shop, Item item, int quantity, Player player) {
        this.shop = shop;
        this.item = item;
        this.quantity = quantity;
        this.player = player;
    }

    @Override
    public void execute() {
        int totalPrice = item.getPrize() * quantity;
        if (player.getBalance() >= totalPrice) {
            player.reduceBalance(totalPrice);

            //  Přidání položek podle typu
            if (item instanceof Ladder) {
                player.addLadders(quantity);
                System.out.println("DEBUG: Přidáno " + quantity + " žebříků");
            } else if (item instanceof PicxakeUpgrade || item instanceof BackpackUpgrade) {
                player.PlusUpgrade(item);
            }

            JOptionPane.showMessageDialog(null,
                    "You bought " + quantity + "x " + item.getName() + " for " + totalPrice + " gold!\nRemaining balance: " + player.getBalance());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Not enough money! You need " + totalPrice + " gold, but you have only " + player.getBalance());
        }
    }

    @Override
    public void exit() {
    }
}