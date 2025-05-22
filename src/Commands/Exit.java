
package Commands;

import Rooms.Shop;
import Rooms.MainLobby;

public class Exit implements Command {
    private Shop shop;

    public Exit(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void execute() {
        // Zavře shop a otevře Main Lobby
        shop.dispose();
        new MainLobby();
    }

    @Override
    public void exit() {
        // Zavře shop a otevře Main Lobby
        shop.dispose();
        new MainLobby();
    }
}