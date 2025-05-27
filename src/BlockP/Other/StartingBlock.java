package BlockP.Other;

import BlockP.Block;
import BlockP.BlockType;

public class StartingBlock extends Block {
    public StartingBlock() {
        super(BlockType.START);
    }
    @Override
    public boolean isMined(){
        return true;
    }
}
