package BlockP.Other;


import BlockP.Block;
import BlockP.BlockType;

public class LadderBlock extends Block {

    public LadderBlock() {
        super(BlockType.LADDER);
    }

    @Override
    public boolean isMined() {
        return true;
    }
}