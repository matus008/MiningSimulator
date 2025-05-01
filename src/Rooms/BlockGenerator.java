package Rooms;

import java.util.Random;

public class BlockGenerator {
    private static final Random random = new Random();

    public static Block generateRandomBlock() {
        int chance = random.nextInt(100);
        if (chance < 60){
            return new Block(BlockType.DIRT);
        }else if (chance < 75){
            return new Block(BlockType.COAL);
        }else if (chance < 85){
            return new Block(BlockType.SILVER);
        }else  if (chance < 95){
            return new Block(BlockType.GOLD);
        }else {
            return new Block(BlockType.DIAMOND);
        }

    }
}