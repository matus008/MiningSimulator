package BlockP.Other;

import BlockP.Block;
import BlockP.BlockType;



public class StoneBlock extends Block {
    public StoneBlock(BlockType type) {
        super(BlockType.STONE);
    }
    @Override
    public boolean isMined(){
        return false;
    }
}
