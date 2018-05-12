package worlds;

import entities.EntityManager;
import entities.creatures.Player;
import game.Handler;
import tiles.Tile;
import utils.Utils;

import java.awt.*;

public abstract class World {

    protected Handler handler;

    protected int width, height;
    public int spawnX, spawnY;
    protected int[][] tiles;

    protected EntityManager entityManager;

    public World(Handler handler) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {

        worldRendering(g);

        entityManager.render(g);
    }

    public void worldRendering (Graphics g) {

        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }

        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.dirtTile;

        Tile t = Tile.tiles[tiles[x][y]];

        if (t == null)
            return Tile.dirtTile;
        return t;
    }

    public void loadWorld(String path) {

        String file = Utils.loadFileAsString(path);

        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]) * Tile.TILE_WIDTH;
        spawnY = Utils.parseInt(tokens[3]) * Tile.TILE_HEIGHT;

        tiles = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
