package BlockP;


public class EmptyBlock extends Block {

    public EmptyBlock() {
        super(BlockType.EMPTY);
    }

    @Override
    public boolean isMined() {
        return true;
    }

    @Override
    public void mine() {

    }
}