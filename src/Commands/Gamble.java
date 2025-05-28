package Commands;

import Player.Player;

import javax.swing.*;
import java.util.Random;

/**
 * Class to have a slim chance of winning a jackpot and doubeling players money
 * or loosing it all.
 */
public class Gamble implements Command{
    private Player player;
    private int ToGamble;
    private Random rn;
    private int num;

    public Gamble(Player player, int money){
        this.player = player;
        this.ToGamble = money;
        rn = new Random();

    }
    @Override
    public void execute() {
        num = rn.nextInt(13);

        if (num <= 9){
            JOptionPane.showMessageDialog(null,
                    "You have lost " + ToGamble + " money better luck next time!!");

        }else {
            ToGamble = ToGamble * 2;
            player.addBalance(ToGamble);
            JOptionPane.showMessageDialog(null,
                    "Congrats you have won " + ToGamble + " gold!!");
        }
    }


    @Override
    public void exit() {
    }
}



