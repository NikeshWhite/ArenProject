package entities.staticEntities;

import entities.creatures.Player;
import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class LogicBlockBlue extends StaticEntity {

    protected boolean currentLogic = false;
    protected boolean finalLogic = false;

    public LogicBlockBlue(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 0;
        bounds.width = 0;
    }

    @Override
    public void tick() {
        checkActive();

    }

    @Override
    public void render(Graphics g) {

        if (currentLogic) {
            g.drawImage(Assets.logicBlockBlueActive, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (!currentLogic) {
            g.drawImage(Assets.logicBlockBlueNotActive, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        if (finalLogic) {
            g.drawImage(Assets.logicBlockBlueActive, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }


    }

    public boolean isActive() {
        return currentLogic;
    }

    public void isFinal() {
        finalLogic = true;
    }

    private void checkActive() {

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (player.isRespectPlayer() &&
                player.getX() + player.getBoundLogicX() < x + 64 &&
                player.getX() - player.getBoundLogicX() > x - 64 &&
                player.getY() + player.getBoundLogicY() < y + 64 &&
                player.getY() - player.getBoundLogicY() > y - 64) {
                currentLogic = !currentLogic;
        }
    }
}
