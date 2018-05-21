package entities.staticEntities;

import entities.creatures.Player;
import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class BlueKey extends StaticEntity {

    private boolean isKeyOn;

    private Player player;

    public BlueKey(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 0;
        bounds.width = 0;
    }

    @Override
    public void tick() {
        isKeyWasPicked();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.blueKey, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private void isKeyWasPicked() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getX() + 32 < x + width && player.getX() + 32 > x &&
                player.getY() > y && player.getY() < y + height) {
            handler.getWorld().getEntityManager().removeEntity(this);
            isKeyOn = true;
        }
    }

    public boolean isKeyOn() {
        return isKeyOn;
    }
}
