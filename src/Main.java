import Player.Player;
import Rooms.MainLobby;

public class Main {
    public static void main(String[] args) {
        Player p = new Player();
        MainLobby lobby = new MainLobby(p);
    }
} 