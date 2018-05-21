package entities.creatures;

import game.Handler;
import gfx.Assets;
import java.awt.*;

public class ArenaEnemySt1 extends Creature {

    public ArenaEnemySt1(Handler handler, double x, double y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        health = 6;
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
        isAttack(1000, 700, 64, 16, 16, 44, 1);
        moveTo();
        move();
        checkPlayerInArea();
    }

    @Override
    public void render(Graphics g) {

        if (enemyView == 0 && attackTimer >= 700) {
            g.drawImage(Assets.arenaEnemySt1Up, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 90 && attackTimer >= 700) {
            g.drawImage(Assets.arenaEnemySt1Right, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 180 && attackTimer >= 700) {
            g.drawImage(Assets.arenaEnemySt1Down, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 270 && attackTimer >= 700) {
            g.drawImage(Assets.arenaEnemySt1Left, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 0 && attackTimer < 700) {
            g.drawImage(Assets.arenaEnemySt1AttackUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 1 && attackTimer < 700) {
            g.drawImage(Assets.arenaEnemySt1AttackRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 2 && attackTimer < 700) {
            g.drawImage(Assets.arenaEnemySt1AttackDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 3 && attackTimer < 700) {
            g.drawImage(Assets.arenaEnemySt1AttackLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        g.setColor(Color.BLACK);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset() + 1),
                (int) (y - handler.getGameCamera().getyOffset() - 31), 61, 11);
        g.setColor(Color.RED);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 2),
                (int) (y - handler.getGameCamera().getyOffset() - 30), health * 10, 10);

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
