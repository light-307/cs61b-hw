package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final long SEED = 287323;
    private static final Random RANDOM = new Random(SEED);


    public static void addHexagons(TETile[][] world, int s) {
        int[][] pts = calculate_Hexagon_position(s);

        for (int i = 0; i < 19; i++) {
            addHexagon(world, s, pts[i][0], pts[i][1], randomTile());
        }
    }


    /**
     * adds a hexagon of side length s to a given position in the world.
     * @param world the TETile world
     * @param s side length of the hexagon
     * @param x x coordinate of the up-left element in the hexagon
     * @param y y coordinate of the up-left element in the hexagon
     * @param tile use tile to make up hexagon
     */
    public static void addHexagon(TETile[][] world, int s, int x, int y, TETile tile) {
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s + 2*i; j++) {
                world[x-i + j][y + i] = TETile.colorVariant(tile, 50, 50, 50, RANDOM);;
            }
        }
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < 3*s - 2 - 2*i; j++) {
                world[x-s+1+i + j][y+s + i] = TETile.colorVariant(tile, 50, 50, 50, RANDOM);;
            }
        }
    }


    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }


    /**
     * calculate WIDTH and HEIGHT according to side length of the hexagon.
     * @param s side length of the hexagon
     * @return int[] WIDTH and HEIGHT
     */
    private static int[] calculate_wh(int s) {
        int[] wh = new int[2];
        wh[0] = 11 * s -6;
        wh[1] = 10 * s;
        return wh;
    }


    /**
     * calculate Hexagon_position of all hexagons according to side length.
     * @param s side length of the hexagon
     * @return int[][] Hexagon_position
     */
    private static int[][] calculate_Hexagon_position(int s) {
        int[][] pts = new int[19][2];

        int[] cont = {1,2,3,2,3,2,3,2,1};

        int index = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < cont[y]; x++) {
                pts[index][0] = cont_position(s, cont[y]) + (4*s-2)*x;
                pts[index][1] = s * y;
                index++;
            }
        }

        return pts;
    }
    private static int cont_position(int s, int cont) {
        switch (cont) {
            case 1: return 5*s - 3;
            case 2: return 3*s - 2;
            case 3: return s - 1;
            default: return 0;
        }
    }


    public static void fillwithnothing(TETile[][] world, int WIDTH, int HEIGHT) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }


    public static void main(String[] args) {
        int s = 4;
        int[] wh = calculate_wh(s);
        int WIDTH = wh[0];
        int HEIGHT = wh[1];

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillwithnothing(world, WIDTH, HEIGHT);

        addHexagons(world, s);

        ter.renderFrame(world);
    }
}
