package model;

public class Tile extends AbstractTile {

    private boolean opened;
    private boolean flagged;
    private boolean mined;

    public Tile(boolean explosive){
        opened = false;
        flagged = false;
        mined = explosive;
    }

    @Override
    public void open() {
        opened = true;
    }

    @Override
    public void flag() {
        flagged = true;
    }

    @Override
    public void unflag() {
        flagged = false;
    }

    @Override
    public void toggledFlag(){
        flagged= !flagged;
    }

    @Override
    public boolean isFlagged() {
        return flagged;
    }

    @Override
    public boolean isExplosive() {
        return mined;
    }

    @Override
    public boolean isOpened() {
        return opened;
    }
}
