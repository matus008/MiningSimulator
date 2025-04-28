package Player;

import Valuables.Ores;
import java.util.ArrayList;

public class Player {
    private ArrayList<Ores> backpack;
    private int balance;

    public Player() {
        this.backpack = new ArrayList<>();
        this.balance = 500;
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
        System.out.println("You sold all ores for " + totalValue + " gold!");
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

    public ArrayList<Ores> getBackpack() {
        return backpack;
    }
}