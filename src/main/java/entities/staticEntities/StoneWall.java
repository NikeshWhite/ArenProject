package entities.staticEntities;

import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class StoneWall extends StaticEntity {

    public StoneWall(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 64;
        bounds.width = 64;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.stoneBrick, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
