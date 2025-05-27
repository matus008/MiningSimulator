package Commands;

import Player.Player;

import javax.swing.*;
/**
 * This command handles selling all ores from the players backpack.
 * It checks if the backpack is empty and if not, it sells everything and updates the balance.
 */
public class SellItem implements Command {
    private Player player;

    public SellItem(Player player) {
        this.player = player;
    }
    /**
     * Executes the sell action.
     * Sells all ores if backpack isnt empty and shows the new balance.
     */
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