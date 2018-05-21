package entities.staticEntities;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class WoodenGate extends StaticEntity {

    public WoodenGate(Handler handler, double x, double y) {
        super(handler, x, y, 64, 64);

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

        if(handler.getWorld().getEntityManager().getPlayer().logicIsDone) {
            g.drawImage(Assets.woodenGateOpen, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), 128, 64, null);
        } else {
            g.drawImage(Assets.woodenGate, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), 128, 64, null);
        }

    }
}
