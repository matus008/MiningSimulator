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
        int chance = random.nextInt(100); // 0–99


        if (chance < 70) {

            return new Block(BlockType.DIRT);            // 70%
        } else if (chance < 80) {
            return new Block(BlockType.COAL);            // +10% (70–79)
        } else if (chance < 87) {
            return new Block(BlockType.STONE);           // +7% (80–86)
        } else if (chance < 92) {
            return new Block(BlockType.SILVER);          // +5% (87–91)
        } else if (chance < 96) {
            return new Block(BlockType.GOLD);            // +4% (92–95)
        } else {

            return new StoneBlock(BlockType.DIAMOND);    // +4% (96–99)
        }
    }
}