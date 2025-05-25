package Rooms;

import ShopItems.*;
import Commands.BuyItem;
import Commands.Exit;
import Commands.SellItem;
import Commands.Command;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Shop extends JFrame {
    private Player player;
    private ArrayList<Item> upgrades;

    public Shop(Player player) {
        this.upgrades = new ArrayList<>();
        this.player = player;
        setTitle("SHOP");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon bgImage = new ImageIcon(getClass().getResource("/Textures/shop.png"));
        ShopPanel backgroundPanel = new ShopPanel(bgImage);
        setContentPane(backgroundPanel);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screen.width;
        int h = screen.height;

        initializeItems();




        JButton buyPickaxe = new JButton("");
        buyPickaxe.setFont(new Font("Arial", Font.BOLD, 32));
        buyPickaxe.setSize(400,50);
        buyPickaxe.setLocation(758, 843);
        buyPickaxe.setContentAreaFilled(false);
        buyPickaxe.setBorderPainted(false);
        buyPickaxe.setFocusPainted(false);
        buyPickaxe.setOpaque(false);
        buyPickaxe.addActionListener(e -> {

            handlePurchase(upgrades.get(4));
        });

        JButton buyLadder= new JButton("");
        buyLadder.setFont(new Font("Arial", Font.BOLD, 32));
        buyLadder.setSize(400,50);
        buyLadder.setLocation(165, 843);
        buyLadder.setContentAreaFilled(false);
        buyLadder.setBorderPainted(false);
        buyLadder.setFocusPainted(false);
        buyLadder.setOpaque(false);
        buyLadder.addActionListener(e -> {

            handlePurchase(upgrades.get(3));
        });

        JButton buyBackPAck = new JButton("");
        buyBackPAck.setFont(new Font("Arial", Font.BOLD, 32));
        buyBackPAck.setSize(400,50);
        buyBackPAck.setLocation(165, 488);
        buyBackPAck.setContentAreaFilled(false);
        buyBackPAck.setBorderPainted(false);
        buyBackPAck.setFocusPainted(false);
        buyBackPAck.setOpaque(false);
        buyBackPAck.addActionListener(e -> {

            handlePurchase(upgrades.get(0));
        });

        JButton buyDynamite = new JButton("");
        buyDynamite.setFont(new Font("Arial", Font.BOLD, 32));
        buyDynamite.setSize(400,50);
        buyDynamite.setLocation(1355, 488);
        buyDynamite.setContentAreaFilled(false);
        buyDynamite.setBorderPainted(false);
        buyDynamite.setFocusPainted(false);
        buyDynamite.setOpaque(false);
        buyDynamite.addActionListener(e -> {

            handlePurchase(upgrades.get(2));
        });

        JButton buyColum = new JButton("");
        buyColum.setFont(new Font("Arial", Font.BOLD, 32));
        buyColum.setSize(400,50);
        buyColum.setLocation(758, 488);
        buyColum.setContentAreaFilled(false);
        buyColum.setBorderPainted(false);
        buyColum.setFocusPainted(false);
        buyColum.setOpaque(false);
        buyColum.addActionListener(e -> {
            handlePurchase(upgrades.get(1));
        });


        JButton sellButton = new JButton("");
        sellButton.setFont(new Font("", Font.BOLD, 32));
        sellButton.setSize(740,90);
        sellButton.setLocation(180, 950);
        sellButton.setContentAreaFilled(false);
        sellButton.setBorderPainted(false);
        sellButton.setFocusPainted(false);
        sellButton.setOpaque(false);
        sellButton.addActionListener(e -> {
            Command sell = new SellItem(player);
            sell.execute();
        });

        JButton exitButton = new JButton("");
        exitButton.setFont(new Font("", Font.BOLD, 32));
        exitButton.setSize(740,90);
        exitButton.setLocation(1000, 950);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.addActionListener(e -> {
            Command exit = new Exit(this, player);
            exit.execute();
        });
        backgroundPanel.add(buyPickaxe);
        backgroundPanel.add(buyLadder);
        backgroundPanel.add(buyColum);
        backgroundPanel.add(buyBackPAck);
        backgroundPanel.add(buyDynamite);
        backgroundPanel.add(sellButton);
        backgroundPanel.add(exitButton);

        setVisible(true);
    }

    private void initializeItems() {
        upgrades = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src\\shopFile"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());

                    Item item = null;

                    if (name.equals("BackpackUpgrade")) {
                        item = new BackpackUpgrade(price, name);
                    } else if (name.equals("Column")) {
                        item = new Column(price, name);
                    } else if (name.equals("Dynamite")) {
                        item = new Dynamite(price, name);
                    } else if (name.equals("Ladder")) {
                        item = new Ladder(price, name);
                    } else if (name.equals("PickaxeUpgrade")) {
                        item = new PicxakeUpgrade(price, name);
                    }

                    if (item != null) {
                        upgrades.add(item);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Chyba při načítání položek shopu: " + e.getMessage());
        }
    }

    public ArrayList<Item> getUpgrades() {
        return upgrades;
    }

    private void handlePurchase(Item item) {
        int quantity = 0;

        if (item instanceof Column || item instanceof Dynamite || item instanceof Ladder) {
            String input = JOptionPane.showInputDialog(null,
                    "How many units would you like to buy? (default 0):",
                    "Purchase Quantity",
                    JOptionPane.QUESTION_MESSAGE);

            if (input == null || input.trim().isEmpty()) {
                input = "0";
            }

            try {
                quantity = Integer.parseInt(input.trim());
                if (quantity < 0) {
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
        }else {
            quantity = 1;
        }

        Command buyCommand = new BuyItem(this, item, quantity, player);
        buyCommand.execute();
    }
}