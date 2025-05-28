package ShopItems;

/**
 * class for representing unique items with their name
 * and prize for each and every piece.
 */
public class Item {
    private int prize;
    private String name;

    public Item(int prize, String name) {
        this.prize = prize;
        this.name = name;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
