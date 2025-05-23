
package Commands;

import Player.Player;
import Rooms.Shop;
import Rooms.MainLobby;

public class Exit implements Command {
    private Shop shop;
    private Player player;

    public Exit(Shop shop, Player player) {
        this.shop = shop;
        this.player = player;
    }

    @Override
    public void execute() {
        // Zavře shop a otevře Main Lobby
        shop.dispose();
        new MainLobby(player);
    }

    @Override
    public void exit() {
        // Zavře shop a otevře Main Lobby
        shop.dispose();
        new MainLobby(player);
    }
}