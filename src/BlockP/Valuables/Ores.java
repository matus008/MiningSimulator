package BlockP.Valuables;

public class Ores {
    private int value;
    /**
     * Creates a new ore with a given value.
     *
     * @param value how much gold this ore is worth
     */
    public Ores(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
