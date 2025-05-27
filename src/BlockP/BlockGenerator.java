package BlockP;

import BlockP.Other.StoneBlock;

import java.util.Random;
/**
 * This class is used to randomly generate blocks for the mine.
 * It chooses a block based on predefined chances.
 */
public class BlockGenerator {
    private static final Random random = new Random();

    /**
     * Generates a random block based on chance.
     *
     * @return a randomly chosen Block (like dirt, coal, silver, gold, diamond, or stone)
     */
    public static Block generateRandomBlock() {
        int chance = random.nextInt(101);
        if (chance < 75) {
            return new Block(BlockType.DIRT);           // 75%
        } else if (chance < 83) {
            return new Block(BlockType.COAL);           // 8%
        } else if (chance < 90) {
            return new Block(BlockType.STONE);          // 7%
        } else if (chance < 98) {
            return new Block(BlockType.SILVER);         // 8%
        } else if (chance < 100) {
            return new Block(BlockType.GOLD);           // 2%
        } else {
            return new StoneBlock(BlockType.DIAMOND);   // 1%
        }
    }
}