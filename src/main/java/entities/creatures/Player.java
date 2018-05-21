package entities.creatures;

import entities.Entity;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    private final double PLAYER_SPEED = 5.0;

    private boolean actionPlayer, respectPlayer, attackPlayer;

    private Rectangle boundsLogic, attackRectangle;

    public boolean startGameDialogShowed;
    public boolean logicIsDone;
    public boolean keyOnPlayer = false;

    private boolean playerIsAttack;

    private int playerNumAttack;

    public int playerView;

    private long lastAttackTimer, attackCooldown = 900, attackTimer = attackCooldown;

    private long lastSprintTimer, sprintCooldown = 900, sprintTimer = sprintCooldown;

    Rectangle attackRectanglePlayer;

    public Player(Handler handler, double x, double y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 20;
        bounds.width = 24;
        bounds.height = 44;

        health = 15;

        boundsLogic = new Rectangle(31, 31, 1, 1);
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        isAttackPlayer();

    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        sprintTimer += System.currentTimeMillis() - lastSprintTimer;
        lastSprintTimer = System.currentTimeMillis();

        actionPlayer = handler.getKeyManager().action;

        respectPlayer = handler.getKeyManager().respect;

        attackPlayer = handler.getKeyManager().attack;

        if (handler.getKeyManager().up) {
            yMove = -currentSpeed;
            playerView = 0;
        }
        if (handler.getKeyManager().down) {
            yMove = currentSpeed;
            playerView = 180;
        }
        if (handler.getKeyManager().left) {
            xMove = -currentSpeed;
            playerView = 270;
        }

        if (handler.getKeyManager().right) {
            xMove = currentSpeed;
            playerView = 90;
        }
        if (handler.getKeyManager().shift) {
            currentSpeed = 2;
        } else
            currentSpeed = this.speed;
    }

    /** In developing **/
    private void isAttackPlayer() {

        if(attackTimer < 350) {
            currentSpeed = 0;
        } else
            speed = PLAYER_SPEED;

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        attackRectanglePlayer = new Rectangle();

        if(attackPlayer && playerView == 0) {
            attackRectanglePlayer.width = 64;
            attackRectanglePlayer.height = 56;
            attackRectanglePlayer.x = (int) x;
            attackRectanglePlayer.y = (int) y - 64;
            playerNumAttack = 0;
        } else if (attackPlayer && playerView == 90) {
            attackRectanglePlayer.width = 56;
            attackRectanglePlayer.height = 44;
            attackRectanglePlayer.x = (int) x + bounds.x + bounds.width;
            attackRectanglePlayer.y = (int) y;
            playerNumAttack = 1;
        } else if (attackPlayer && playerView == 180) {
            attackRectanglePlayer.width = 64;
            attackRectanglePlayer.height = 56;
            attackRectanglePlayer.x = (int) x;
            attackRectanglePlayer.y = (int) y + 44;
            playerNumAttack = 2;
        } else if (attackPlayer && playerView == 270) {
            attackRectanglePlayer.width = 56;
            attackRectanglePlayer.height = 44;
            attackRectanglePlayer.x = (int) x + bounds.x - 64;
            attackRectanglePlayer.y = (int) y;
            playerNumAttack = 3;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(attackRectanglePlayer) && e.getHealth() != ENDLESS_HEALTH) {
                e.hurt(1);
                return;
            }
        }

    }

    @Override
    public void render(Graphics g) {

        if (playerView == 0 && attackTimer >= 500) {
            g.drawImage(Assets.playerUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerView == 90 && attackTimer >= 500) {
            g.drawImage(Assets.playerRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerView == 180 && attackTimer >= 500) {
            g.drawImage(Assets.playerDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerView == 270 && attackTimer >= 500) {
            g.drawImage(Assets.playerLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerNumAttack == 0 && attackTimer < 500) {
            g.drawImage(Assets.playerAttackUp, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerNumAttack == 1 && attackTimer < 500) {
            g.drawImage(Assets.playerAttackRight, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerNumAttack == 2 && attackTimer < 500) {
            g.drawImage(Assets.playerAttackDown, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if (playerNumAttack == 3 && attackTimer < 500) {
            g.drawImage(Assets.playerAttackLeft, (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }

        /*g.setColor(Color.BLACK);
        g.drawRect((int) (x - handler.getGameCamera().getxOffset() + 1),
                (int) (y - handler.getGameCamera().getyOffset() - 31), 61, 11);
        g.setColor(Color.GREEN);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 2),
                (int) (y - handler.getGameCamera().getyOffset() - 30), health * 6, 10);*/

        g.setColor(Color.GREEN);
        g.fillRect(10, 42, health * 20, 33);
        g.drawImage(Assets.healthBar, 10, 10, 300, 65, null);

        if(attackTimer < 900) {
            g.setColor(Color.ORANGE);
            g.fillRect(11, 75, (900 - (int)attackTimer) / 3, 10);
        }



        if (keyOnPlayer) {
            g.drawImage(Assets.blueKey, 10, 85, 64, 64, null);
        }



        //For Develop collisions
        /*g.setColor(Color.ORANGE);
        g.fillRect((int) (x - bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                32, 64);*/

        /*g.setColor(Color.RED);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);

        g.setColor(Color.PINK);
        g.fillRect((int) (attackRectanglePlayer.x - handler.getGameCamera().getxOffset()),
                (int) (attackRectanglePlayer.y + bounds.y - handler.getGameCamera().getyOffset()),
                attackRectanglePlayer.width, attackRectanglePlayer.height);*/


        /*
        g.setColor(Color.BLUE);
        g.fillRect((int) (x + boundsLogic.x - handler.getGameCamera().getxOffset()),
                (int) (y + boundsLogic.y - handler.getGameCamera().getyOffset()),
                boundsLogic.width, boundsLogic.height);*/


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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
