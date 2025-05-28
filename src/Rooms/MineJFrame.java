package Rooms;

import Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Class for fullscreen window and creates the MinePanel
 */
public class MineJFrame extends JFrame {
    public MineJFrame(Player player) {
        setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MinePanel panel = new MinePanel(player,this);
        panel.setPreferredSize(screenSize);
        setContentPane(panel);
        pack();

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);

        setVisible(true);
        panel.requestFocusInWindow();

}
}