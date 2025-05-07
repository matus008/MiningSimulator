package Commands;

import Player.Player;

import javax.swing.*;

public class SellItem implements Command {
    private Player player;

    public SellItem(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if (player.getBackpack().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Your backpack is empty!");

        } else {
            player.sellAllOres();
            JOptionPane.showMessageDialog(null,
                    "All ores sold! Your new balance is: " +
                            player.getBalance() + " gold.");

        }
    }

    @Override
    public void exit() {

    }
}