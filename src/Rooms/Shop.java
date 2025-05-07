package Rooms;

import ShopItems.*;
import Commands.BuyItem;
import Commands.Exit;
import Commands.SellItem;
import Commands.Command;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Shop extends JFrame {
    private ArrayList<Item> upgrades;
    private Player player;

    public Shop(Player player) {
        this.player = player;
        upgrades = new ArrayList<>();
        initializeItems();
        initializeGUI();
    }

    private void initializeItems() {
        upgrades.add(new BackpackUpgrade(300, "BackpackUpgrade"));
        upgrades.add(new Column(50, "Column"));
        upgrades.add(new Dynamite(100, "Dynamite"));
        upgrades.add(new Laddre(75, "Ladder"));
        upgrades.add(new PicxakeUpgrade(500, "PickaxeUpgrade"));
    }

    private void initializeGUI() {
        setTitle("SHOP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Welcome to the SHOP!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.NORTH);

        for (Item item : upgrades) {
            JButton button = new JButton(item.getPrize() + " gold");
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(e -> handlePurchase(item));
            panel.add(button);
        }

        JButton sellButton = new JButton("Sell All Ores");
        sellButton.setFont(new Font("Arial", Font.BOLD, 24));
        sellButton.addActionListener(e -> {
            Command sellCommand = new SellItem(player);
            sellCommand.execute();
        });
        panel.add(sellButton);

        JButton exitButton = new JButton("Exit Shop");
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.addActionListener(e -> {
            Command exitCommand = new Exit(this);
            exitCommand.execute();
        });
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handlePurchase(Item item) {
        int quantity = 1;
        //mozna jeste pridat LIGHTUPGRADE podle casu
        if (item instanceof PicxakeUpgrade || item instanceof BackpackUpgrade){
            player.PlusUpgrade(item);
        }

        if (item instanceof Column || item instanceof Dynamite || item instanceof Laddre) {
            String input = JOptionPane.showInputDialog(null,
                    "How many units would you like to buy? (default 1):",
                    "Purchase Quantity",
                    JOptionPane.QUESTION_MESSAGE);

            if (input == null || input.trim().isEmpty()) {
                input = "1";
            }

            try {
                quantity = Integer.parseInt(input.trim());
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid quantity! Must be a positive number.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input! Please enter a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Command buyCommand = new BuyItem(this, item, quantity, player);
        buyCommand.execute();
    }
}