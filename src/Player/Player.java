package Player;

import ShopItems.Item;
import BlockP.Valuables.Ores;

import javax.swing.*;
import java.util.ArrayList;

public class Player {
    private final Miner miner;
    private ArrayList<Ores> backpack;
    private int balance;
    private int PUpgradeCounter;
    private int backPackSize = 10;
    private int ladderCount = 0;

    public Player() {
        this.backpack = new ArrayList<>();
        this.balance = 500;
        this.PUpgradeCounter = 0;

        // start pozice miner (50, 50)
        this.miner = new Miner(50, 50);
    }

    public Miner getMiner() {
        return miner;
    }

    public void addOre(Ores ore) {
        backpack.add(ore);
    }

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

    public void addLadders(int amount) {
        ladderCount += amount;
    }

    public void useLadder() {
        if (ladderCount > 0) {
            ladderCount--;
        }
    }
}