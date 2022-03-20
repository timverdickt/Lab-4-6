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
            width = 16;
            height = 30;
            mines = 99;
        }
        generateWorld(height, width, mines);
    }

    public void generateWorld(int height, int width, int mines){
        world = new AbstractTile[width][height];
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
        for (int i=0; i<width;i++){
            for (int j=0;j< height;j++) {
                if(world[i][j]==null){
                    world[i][j] = generateEmptyTile();
                }
            }
        }
        setNumbers();
    }

    public void setNumbers(){
        for (int i=0 ; i<width;i++) {
            for (int j = 0; j < height; j++) {
                int count = 0;
                if(!world[i][j].isExplosive()){
                    try {
                        for (int k = i - 1; k <= i + 1; k++) {
                            for (int l = j - 1; l <= j + 1; l++) {
                                if (world[k][l].isExplosive()) {
                                    count++;
                                }
                            }
                        }
                    }
                    catch(Exception e){
                    }
                }
                world[i][j].setExplosiveCount(count);
            }
        }
    }

    @Override
    public void startNewGame(int row, int col, int explosionCount) {
        width = col;
        height = row;
        mines = explosionCount;
        generateWorld(height,width,mines);
    }

    @Override
    public void toggleFlag(int x, int y) {
        world[x][y].toggledFlag();
    }

    @Override
    public AbstractTile getTile(int x, int y) {

        return world[x][y];
    }

    @Override
    public void setWorld(AbstractTile[][] world) {
        this.world=world;

    }

    @Override
    public void open(int x, int y) {
        world[x][y].open();
    }

    @Override
    public void flag(int x, int y) {
        world[x][y].flag();
    }

    @Override
    public void unflag(int x, int y) {
        world[x][y].unflag();
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
