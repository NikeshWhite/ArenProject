package worlds;

import entities.creatures.Player;
import entities.staticEntities.BlueKey;
import game.Handler;
import tiles.Tile;
import java.awt.*;

public class HomeStartWorld extends World {

    private Player player;

    private BlueKey blueKey = new BlueKey(handler, 10 * Tile.TILE_WIDTH, 6 * Tile.TILE_HEIGHT);

    public HomeStartWorld(Handler handler) {
        super(handler);

        loadWorld("/textworlds/bluehome.txt");

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        entityManager.addEntity(blueKey);
    }

    @Override
    public void tick() {
        entityManager.tick();
        isKey();
        toStart();
    }

    @Override
    public void render(Graphics g) {
        worldRendering(g);
        entityManager.render(g);
    }


    public boolean isKey() {
        if (blueKey.isKeyOn()) {
            handler.getWorld().getEntityManager().getPlayer().keyOnPlayer = true;
            handler.getStartWorld().getEntityManager().getPlayer().keyOnPlayer = true;
            return blueKey.isKeyOn();
        }
        return false;
    }

    private void toStart() {
        player = handler.getWorld().getEntityManager().getPlayer();

        if(player.getY() > 700) {

            handler.setWorld(handler.getStartWorld());
            handler.getStartWorld().getEntityManager().getPlayer().setX(550);
            handler.getStartWorld().getEntityManager().getPlayer().setY(450);
        }
    }
}
