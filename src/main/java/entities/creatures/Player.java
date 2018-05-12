package entities.creatures;

import entities.Entity;
import game.Handler;
import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;

public class Player extends Creature {

    private boolean actionPlayer, respectPlayer, attackPlayer;

    private Rectangle boundsLogic;

    public boolean startGameDialogShowed;
    public boolean logicIsDone;



    public Player(Handler handler, double x, double y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 38;
        bounds.width = 24;
        bounds.height = 24;

        speed = 5;

        boundsLogic = new Rectangle(31, 31, 1, 1);
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        isAttack();
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        actionPlayer = handler.getKeyManager().action;

        respectPlayer = handler.getKeyManager().respect;

        attackPlayer = handler.getKeyManager().attack;

        if(handler.getKeyManager().up)
            yMove = -currentSpeed;
        if(handler.getKeyManager().down)
            yMove = currentSpeed;
        if(handler.getKeyManager().left)
            xMove = -currentSpeed;
        if(handler.getKeyManager().right)
            xMove = currentSpeed;
        if(handler.getKeyManager().shift)
            currentSpeed = speed/3;
        else currentSpeed = speed;
    }

    private void isAttack() {

        Rectangle attackRectangle = new Rectangle();

        int attackRectangleSize = 32;
        attackRectangle.width = attackRectangleSize * 2;
        attackRectangle.height = attackRectangleSize;

        Rectangle checkBox = getCollisionBounds(0, 0);

        if(attackPlayer) {
            attackRectangle.x = checkBox.x + checkBox.width / 2 - attackRectangleSize / 2;
            attackRectangle.y = checkBox.y - attackRectangleSize;
        } else {
            return;
        }

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(attackRectangle) && e.getHealth() != ENDLESS_HEALTH) {
                e.hurt(1);
                return;
            }
        }

    }

    @Override
    public void render(Graphics g) {

        if (actionPlayer) {
            g.drawImage(Assets.player1, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        //For Develop collisions
        /*g.setColor(Color.RED);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);*/
    }

    @Override
    public void die() {
        System.out.println("You died");
    }

    public boolean isActionPlayer() {
        return actionPlayer;
    }

    public boolean isRespectPlayer() {
        return respectPlayer;
    }

    public int getBoundLogicX() {
        return boundsLogic.x;
    }
    public int getBoundLogicY() {
        return boundsLogic.y;
    }
    public int getBoundLogicWidth() {
        return boundsLogic.width;
    }
    public int getBoundLogicHeight() {
        return boundsLogic.height;
    }


}
