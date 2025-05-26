package BlockP.Other;

import BlockP.Block;
import BlockP.BlockType;

public class ColumnBlock extends Block {
    public ColumnBlock(BlockType type) {
        super(type);
    }
    @Override
    public boolean isMined(){
        return true;
    }
}
