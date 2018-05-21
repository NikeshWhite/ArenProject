package worlds;

import entities.creatures.ArenaEnemySt1;
import entities.creatures.Player;
import entities.staticEntities.StoneWall;
import game.Handler;
import tiles.Tile;

import java.awt.*;

public class ArenaWorld extends World {

    private StoneWall stone1, stone2;

    private ArenaEnemySt1 arenaEnemySt1;

    private Player player;

    public ArenaWorld(Handler handler) {
        super(handler);

        loadWorld("/textworlds/arena.txt");

        entityManager.getPlayer().setX(spawnX + 32);
        entityManager.getPlayer().setY(spawnY + 32);

        entityManager.addEntity(stone1 = new StoneWall(handler, 9 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(stone2 = new StoneWall(handler, 10 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));

        entityManager.addEntity(arenaEnemySt1 = new ArenaEnemySt1(handler, 9 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
    }

    @Override
    public void tick() {
        entityManager.tick();

        arenaIsDone();
        toArenaStage2();
    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

    }

    private void arenaIsDone() {

        if (!arenaEnemySt1.isAlive()) {
            entityManager.removeEntity(stone1);
            entityManager.removeEntity(stone2);
        }
    }

    private void toArenaStage2() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() < 10) {

            handler.setWorld(handler.getArenaWorldStage2());
        }
    }

}
