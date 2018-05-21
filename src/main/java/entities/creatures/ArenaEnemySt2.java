package entities.creatures;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class ArenaEnemySt2 extends Creature {

    public ArenaEnemySt2(Handler handler, double x, double y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        health = 10;
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
        isAttack(900, 500, 64, 24, 24, 44, 1);
        moveTo();
        move();
        checkPlayerInArea();
    }

    @Override
    public void render(Graphics g) {

        if (enemyView == 0 && attackTimer >= 500) {
            g.drawImage(Assets.arenaEnemySt2Up, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 90 && attackTimer >= 500) {
            g.drawImage(Assets.arenaEnemySt2Right, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 180 && attackTimer >= 500) {
            g.drawImage(Assets.arenaEnemySt2Down, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (enemyView == 270 && attackTimer >= 500) {
            g.drawImage(Assets.arenaEnemySt2Left, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 0 && attackTimer < 500) {
            g.drawImage(Assets.arenaEnemySt2AttackUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 1 && attackTimer < 500) {
            g.drawImage(Assets.arenaEnemySt2AttackRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 2 && attackTimer < 500) {
            g.drawImage(Assets.arenaEnemySt2AttackDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (numAttack == 3 && attackTimer < 500) {
            g.drawImage(Assets.arenaEnemySt2AttackLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        g.setColor(Color.BLACK);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset() + 1),
                (int) (y - handler.getGameCamera().getyOffset() - 31), 61, 11);
        g.setColor(Color.RED);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 2),
                (int) (y - handler.getGameCamera().getyOffset() - 30), health * 6, 10);

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
