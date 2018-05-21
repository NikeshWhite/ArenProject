package worlds;

import entities.creatures.ArenaEnemySt2;
import entities.creatures.Player;
import entities.staticEntities.StoneWall;
import game.Handler;
import tiles.Tile;

import java.awt.*;

public class ArenaWorldStage2 extends World {

    private StoneWall stone1, stone2;

    private ArenaEnemySt2 arenaEnemySt2;

    private Player player;

    public ArenaWorldStage2(Handler handler) {
        super(handler);

        loadWorld("/textworlds/arena.txt");

        entityManager.getPlayer().setX(spawnX + 32);
        entityManager.getPlayer().setY(spawnY + 32);

        entityManager.addEntity(stone1 = new StoneWall(handler, 9 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(stone2 = new StoneWall(handler, 10 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));

        entityManager.addEntity(arenaEnemySt2 = new ArenaEnemySt2(handler, 9 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
    }

    @Override
    public void tick() {
        entityManager.tick();

        arenaIsDone();
        toArenaWorldStage3();
    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

    }

    private void arenaIsDone() {

        if (!arenaEnemySt2.isAlive()) {
            entityManager.removeEntity(stone1);
            entityManager.removeEntity(stone2);
        }
    }

    private void toArenaWorldStage3() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() < 10) {

            handler.setWorld(handler.getArenaWorldStage3());
        }
    }

}
