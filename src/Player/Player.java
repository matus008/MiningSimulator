package Player;

import ShopItems.Item;
import BlockP.Valuables.Ores;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Represents the player in the game.
 * Handles player inventory, balance, upgrades, and tools.
 */
public class Player {
    private final Miner miner;
    private ArrayList<Ores> backpack;
    private int balance;
    private int PUpgradeCounter;
    private int backPackSize = 10;
    private int ladderCount;
    private int columnCount;
    private int dynamiteCount;
    /**
     * Creates a new player with default stats.
     * Starts with 500 gold and a miner at position (middle now  x 50 y 50 ).
     */
    public Player() {
        this.backpack = new ArrayList<>();
        this.balance = 500;
        this.PUpgradeCounter = 0;

        // start pozice miner
        this.miner = new Miner(50, 50);
    }

    public Miner getMiner() {
        return miner;
    }

    public void addOre(Ores ore) {
        backpack.add(ore);
    }
    /**
     * Sells all ores in the backpack and adds their value to the player's balance.
     */
    public void sellAllOres() {
        int totalValue = 0;
        for (Ores ore : backpack) {
            totalValue += ore.getValue();
        }
        balance += totalValue;
        backpack.clear();
        JOptionPane.showMessageDialog(null,
                "You sold all ores for " + totalValue + " gold!");
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    /**
     * Reduces player's balance by a specific amount.
     *
     * @param amount the amount to subtract
     */
    public void reduceBalance(int amount) {
        balance -= amount;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public int getBackPackSize() {
        return backPackSize;
    }

    public int getPUpgradeCounter() {
        return PUpgradeCounter;
    }

    public ArrayList<Ores> getBackpack() {
        return backpack;
    }
    /**
     * Applies an upgrade item to the player.
     * Either increases backpack capacity or pickaxe tier.
     *
     * @param item the item to be upgraded
     */
    public void PlusUpgrade(Item item) {
        if (item.getName().equalsIgnoreCase("BackPackUpgrade")) {
            backPackSize += 5;
        } else if (item.getName().equalsIgnoreCase("PickAxeUpgrade")) {
            PUpgradeCounter += 1;
        }
    }

    public int getLadderCount() {
        return ladderCount;
    }
    /**
     * Adds ladders to the player's inventory.
     *
     * @param amount number of ladders to add
     */
    public void addLadders(int amount) {
        System.out.println("zebrik byl pridan o tolikhle zebriku" + amount);
        ladderCount = ladderCount + amount;
    }

    public void useLadder() {
        if (ladderCount > 0) {
            ladderCount--;
        }
    }

    public int getColumnCount() {
        return columnCount;
    }
    public void addColumns(int amount) {
        columnCount = columnCount + amount;
    }
    /**
     * Uses one column from inventory, if available.
     */
    public void useColumn() {
        if (columnCount > 0) {
            columnCount--;
        }
    }
    public int getDynamiteCount() {
        return dynamiteCount; }

    public void addDynamite(int amount) {
        this.dynamiteCount += amount;
    }

    public void useDynamite() {
        if (dynamiteCount > 0) dynamiteCount--;
    }

}