package worlds;

import entities.creatures.Creature;
import entities.staticEntities.LogicBlockBlue;
import entities.staticEntities.StoneWall;
import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class LogicWorld extends World {

    private LogicBlockBlue blockA1, blockA2, blockA3, blockA4, blockA5, blockB1, blockB2, blockB3, blockB4, blockB5,
            blockC1, blockC2, blockC3, blockC4, blockC5, blockD1, blockD2, blockD3, blockD4, blockD5, blockE1, blockE2, blockE3, blockE4, blockE5;

    private StoneWall stone1, stone2, stone3;

    private boolean dialogStart;

    public LogicWorld(Handler handler) {
        super(handler);

        loadWorld("/textworlds/logic.txt");

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        entityManager.addEntity(blockA1 = new LogicBlockBlue(handler, 5 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockA2 = new LogicBlockBlue(handler, 6 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockA3 = new LogicBlockBlue(handler, 7 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockA4 = new LogicBlockBlue(handler, 8 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockA5 = new LogicBlockBlue(handler, 9 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockB1 = new LogicBlockBlue(handler, 5 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockB2 = new LogicBlockBlue(handler, 6 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockB3 = new LogicBlockBlue(handler, 7 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockB4 = new LogicBlockBlue(handler, 8 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockB5 = new LogicBlockBlue(handler, 9 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockC1 = new LogicBlockBlue(handler, 5 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockC2 = new LogicBlockBlue(handler, 6 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockC3 = new LogicBlockBlue(handler, 7 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockC4 = new LogicBlockBlue(handler, 8 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockC5 = new LogicBlockBlue(handler, 9 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockD1 = new LogicBlockBlue(handler, 5 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockD2 = new LogicBlockBlue(handler, 6 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockD3 = new LogicBlockBlue(handler, 7 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockD4 = new LogicBlockBlue(handler, 8 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockD5 = new LogicBlockBlue(handler, 9 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockE1 = new LogicBlockBlue(handler, 5 * Tile.TILE_WIDTH, 8 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockE2 = new LogicBlockBlue(handler, 6 * Tile.TILE_WIDTH, 8 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockE3 = new LogicBlockBlue(handler, 7 * Tile.TILE_WIDTH, 8 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockE4 = new LogicBlockBlue(handler, 8 * Tile.TILE_WIDTH, 8 * Tile.TILE_HEIGHT));
        entityManager.addEntity(blockE5 = new LogicBlockBlue(handler, 9 * Tile.TILE_WIDTH, 8 * Tile.TILE_HEIGHT));

        entityManager.addEntity(stone1 = new StoneWall(handler,6 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT));
        entityManager.addEntity(stone2 = new StoneWall(handler,7 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT));
        entityManager.addEntity(stone3 = new StoneWall(handler,8 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT));
    }

    @Override
    public void tick() {
        entityManager.tick();

        logicComplete();

    }

    @Override
    public void render(Graphics g) {
        worldRendering(g);

        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok) {
            g.drawImage(Assets.logic, 0, 0, 800, 600, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            getEntityManager().getPlayer().setSpeed(Creature.DEFAULT_SPEED);
            dialogStart = true;
        }
    }

    private void logicComplete() {

        if(blockA1.isActive() && blockA2.isActive() && blockA3.isActive() && blockA4.isActive() && blockA5.isActive() && blockB2.isActive() && blockB4.isActive() &&
                blockC2.isActive() && blockC4.isActive() && blockD2.isActive() && blockD4.isActive() && blockE1.isActive() && blockE2.isActive() && blockE4.isActive() &&
                !blockB1.isActive() && !blockB3.isActive() && !blockB5.isActive() && !blockC1.isActive() && !blockC3.isActive() && !blockC5.isActive() &&
                !blockD1.isActive() && !blockD3.isActive() && !blockD5.isActive() && !blockE3.isActive() && !blockE5.isActive()) {

            entityManager.removeEntity(stone1);
            entityManager.removeEntity(stone2);
            entityManager.removeEntity(stone3);

            blockA1.isFinal();
            blockA2.isFinal();
            blockA3.isFinal();
            blockA4.isFinal();
            blockA5.isFinal();
            blockB1.isFinal();
            blockB2.isFinal();
            blockB3.isFinal();
            blockB4.isFinal();
            blockB5.isFinal();
            blockC1.isFinal();
            blockC2.isFinal();
            blockC3.isFinal();
            blockC4.isFinal();
            blockC5.isFinal();
            blockD1.isFinal();
            blockD2.isFinal();
            blockD3.isFinal();
            blockD4.isFinal();
            blockD5.isFinal();
            blockE1.isFinal();
            blockE2.isFinal();
            blockE3.isFinal();
            blockE4.isFinal();
            blockE5.isFinal();
        }
    }
}

