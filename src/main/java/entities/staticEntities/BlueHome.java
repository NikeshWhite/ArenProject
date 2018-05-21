package entities.staticEntities;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class BlueHome extends StaticEntity {

    public BlueHome(Handler handler, double x, double y) {
        super(handler, x, y, 128, 128);

        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 128;
        bounds.width = 128;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.home, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), 128, 128, null);

    }
}
