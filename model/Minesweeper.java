package model;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper {
    private int width;
    private int height;
    private int mines;
    private AbstractTile[][] world;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void startNewGame(Difficulty level) {
        if (level == Difficulty.EASY){
            width = 8;
            height = 8;
            mines = 10;
        }
        else if (level == Difficulty.MEDIUM){
            width = 16;
            height = 16;
            mines = 40;
        }
        else {
            width = 30;
            height = 16;
            mines = 99;
        }
        generateWorld(height, width, mines);
    }

    public void generateWorld(int height, int width, int mines){
        world = new Tile[width][height];
        Random random = new Random();
        int minesPlaced = 0;
        while(minesPlaced<mines) {
            for (int i=0 ; i<width;i++) {
                for (int j=0 ; j<height;j++) {
                    if (random.nextInt(3) == 1) {
                        if (world[i][j] == null && minesPlaced < mines) {
                            world[i][j]=generateExplosiveTile();
                            minesPlaced++;
                        }
                    }
                }
            }
        }
        for (AbstractTile[] i: world){
            for (AbstractTile j: i) {
                if(j==null){
                    j = generateEmptyTile();
                }
            }
        }
        int count = 0;
        for (AbstractTile[] i: world) {
            for (AbstractTile j: i) {
                if(j==null){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        width = col;
        height = row;
        mines = explosionCount;
    }

    @Override
    public void toggleFlag(int x, int y) {

    }

    @Override
    public AbstractTile getTile(int x, int y) {
        return null;
    }

    @Override
    public void setWorld(AbstractTile[][] world) {

    }

    @Override
    public void open(int x, int y) {

    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void unflag(int x, int y) {

    }

    @Override
    public void deactivateFirstTileRule() {

    }

    @Override
    public AbstractTile generateEmptyTile() {
        return new Tile(false);
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return new Tile(true);
    }
}
