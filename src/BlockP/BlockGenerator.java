package BlockP;

import java.util.Random;

public class BlockGenerator {
    private static final Random random = new Random();

    public static Block generateRandomBlock() {
        int chance = random.nextInt(100);
        if (chance < 75){
            return new Block(BlockType.DIRT);
        }else if (chance < 83){
            return new Block(BlockType.COAL);
        }else if (chance < 90){
            return new Block(BlockType.SILVER);
        }else  if (chance < 98){
            return new Block(BlockType.GOLD);
        }else {
            return new Block(BlockType.DIAMOND);
        }

    }
}