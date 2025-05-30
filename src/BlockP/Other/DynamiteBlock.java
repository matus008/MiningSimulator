package BlockP.Other;

import BlockP.Block;
import BlockP.BlockType;

public class DynamiteBlock extends Block {
    public DynamiteBlock(BlockType type) {
        super(BlockType.DYNAMITE);
    }
    @Override
    public boolean isMined(){
        return true;
    }
}