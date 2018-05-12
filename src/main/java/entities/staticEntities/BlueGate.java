package entities.staticEntities;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class BlueGate extends StaticEntity {

    public BlueGate(Handler handler, double x, double y) {
        super(handler, x, y, 128, 128);

        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 64;
        bounds.width = 128;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.gate, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), 128, 64, null);
    }
}
