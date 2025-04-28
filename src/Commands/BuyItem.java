package Commands;

import Player.Player;
import ShopItems.Item;
import Rooms.Shop;

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
            // Hráč úspěšně koupil item
            JOptionPane.showMessageDialog(null,
                    "You bought " + quantity + "x " + item.getName() + " for " + totalPrice + " gold!\nRemaining balance: " + player.getBalance());
        } else {
            // Nedostatek peněz
            JOptionPane.showMessageDialog(null,
                    "Not enough money! You need " + totalPrice + " gold, but you have only " + player.getBalance());
        }
    }

    @Override
    public void exit() {
    }
}