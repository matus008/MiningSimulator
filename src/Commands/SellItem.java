package Commands;

import Player.Player;

public class SellItem implements Command {
    private Player player;

    public SellItem(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if (player.getBackpack().isEmpty()) {
            System.out.println("Your backpack is empty!");
        } else {
            player.sellAllOres();
            System.out.println("All ores sold! Your new balance is: " + player.getBalance() + " gold.");
        }
    }

    @Override
    public void exit() {

    }
}