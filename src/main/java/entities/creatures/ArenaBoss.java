package entities.creatures;

import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class ArenaBoss extends Creature {

    public ArenaBoss(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        health = 20;
        speed = 1;
        xMove = 0;
        yMove = 0;

        bounds.x = 15;
        bounds.y = 20;
        bounds.width = 34;
        bounds.height = 44;
    }

    @Override
    public void tick() {
        isAttack(1000, 600, 64, 28, 28, 44, 2);
        moveTo();
        move();
        checkPlayerInArea();
    }

    @Override
    public void render(Graphics g) {

        if (enemyView == 0 && attackTimer >= 600) {
            g.drawImage(Assets.arenaBossUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 90 && attackTimer >= 600) {
            g.drawImage(Assets.arenaBossRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 180 && attackTimer >= 600) {
            g.drawImage(Assets.arenaBossDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 270 && attackTimer >= 600) {
            g.drawImage(Assets.arenaBossLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 0 && attackTimer < 600) {
            g.drawImage(Assets.arenaBossAttackUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 1 && attackTimer < 600) {
            g.drawImage(Assets.arenaBossAttackRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 2 && attackTimer < 600) {
            g.drawImage(Assets.arenaBossAttackDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 3 && attackTimer < 600) {
            g.drawImage(Assets.arenaBossAttackLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        g.setColor(Color.BLACK);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset() + 1),
                (int) (y - handler.getGameCamera().getyOffset() - 31), 61, 11);
        g.setColor(Color.RED);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 2),
                (int) (y - handler.getGameCamera().getyOffset() - 30), health * 3, 10);

        /*g.setColor(Color.MAGENTA);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);

        g.setColor(Color.CYAN);
        g.fillRect((int) (attackRectangle.x - handler.getGameCamera().getxOffset()),
                (int) (attackRectangle.y + bounds.y - handler.getGameCamera().getyOffset()),
                attackRectangle.width, attackRectangle.height);*/
    }

    @Override
    public void die() {
    }
}