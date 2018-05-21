package entities.creatures;

import entities.Entity;
import game.Handler;
import tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final double DEFAULT_SPEED = 5.0;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected double speed;
    protected double xMove;
    protected double yMove;
    protected double currentSpeed;

    protected long lastAttackTimer, attackCooldown = 900, attackTimer = attackCooldown,
            animationCooldown = 500, animationTimer, lastAnimationTimer;

    protected int enemyView;

    protected boolean playerInArea;

    protected Rectangle attackRectangle;

    protected int numAttack;

    public Creature(Handler handler, double x, double y, int width, int height) {
        super(handler, x , y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {

        if(!checkEntityCollisions(xMove, 0))
            moveX();
        if(!checkEntityCollisions(0, yMove))
            moveY();
    }

    public void moveX() {

        if(xMove > 0){//rght

            int tx = (int) (x + xMove + bounds.x + bounds.width) /Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else {
               x = tx * Tile.TILE_WIDTH - bounds.width - bounds.x - 1;
            }

        }else if (xMove < 0){//lft

            int tx = (int) (x + xMove + bounds.x) /Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH + bounds.width + bounds.x;
            }
        }

    }

    public void moveY() {

        if(yMove > 0){//dwn

            int ty = (int) (y + yMove + bounds.y + bounds.height) /Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT - bounds.height - bounds.y - 1;
            }

        }else if (yMove < 0){//up

            int ty = (int) (y + yMove + bounds.y) /Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }

    }

    protected void isAttack(long attackCooldown, int stopCooldown, int widthUpDown, int heightUpDown,
                            int widthLeftRight, int heightLeftRight, int attack) {

        if(attackTimer < stopCooldown)
            currentSpeed = 0;
        else
            currentSpeed = speed;

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        attackRectangle = new Rectangle();

        Player player = handler.getWorld().getEntityManager().getPlayer();
        //Up Attack
        if(player.getX() + player.getBoundX() + player.getBoundWidth() > x &&
                player.getX() + player.getBoundX() < x + width &&
                player.getY() + player.getHeight() > y - (heightUpDown - bounds.y) &&
                player.getY() + player.getBoundY() < y + bounds.y) {
            attackRectangle.width = widthUpDown;
            attackRectangle.height = heightUpDown;
            attackRectangle.x = (int) x;
            attackRectangle.y = (int) y - heightUpDown;
            numAttack = 0;
        //Right Attack
        } else if (player.getX() > x + bounds.x &&
                player.getX() + player.getBoundX() < x + bounds.x + bounds.width + widthLeftRight &&
                player.getY() + player.getHeight() > y + bounds.y &&
                player.getY() < y + height) {
            attackRectangle.width = widthLeftRight;
            attackRectangle.height = heightLeftRight;
            attackRectangle.x = (int) this.x + this.bounds.x + bounds.width;
            attackRectangle.y = (int) y;
            numAttack = 1;
        //Down Attack
        } else if (player.getX() + player.getBoundX() + player.getBoundWidth() > x &&
                player.getX() + player.getBoundX() < x + width &&
                player.getY() > y + bounds.y &&
                player.getY() + player.getBoundY() < y + height + heightUpDown) {
            attackRectangle.width = widthUpDown;
            attackRectangle.height = heightUpDown;
            attackRectangle.x = (int) x;
            attackRectangle.y = (int) y + bounds.height;
            numAttack = 2;
        //Left Attack
        } else if (player.getX() + player.getBoundX() + player.getBoundWidth() > x + bounds.x - widthLeftRight &&
                player.getX() + player.getBoundX() + player.getBoundWidth() < x + bounds.x + bounds.width &&
                player.getY() + player.getHeight() > y + bounds.y &&
                player.getY() < y + height) {
            attackRectangle.width = widthLeftRight;
            attackRectangle.height = heightLeftRight;
            attackRectangle.x = (int) this.x + this.bounds.x - widthLeftRight;
            attackRectangle.y = (int) y;
            numAttack = 3;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(attackRectangle) && e.getHealth() != ENDLESS_HEALTH) {
                e.hurt(attack);
                return;
            }
        }

    }

    protected void moveTo() {

        xMove = 0;
        yMove = 0;

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (playerInArea) {
            if (player.getX() > x) {
                xMove = currentSpeed;
                enemyView = 90;
            }
            if (player.getX() < x) {
                xMove = -currentSpeed;
                enemyView = 270;
            }
            if (player.getY() > y) {
                yMove = currentSpeed;
                enemyView = 180;
            }
            if (player.getY() < y) {
                yMove = -currentSpeed;
                enemyView = 0;
            }
        }
    }

    protected void checkPlayerInArea() {

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getX() < x + width + 256 || player.getX() > x - 256 || player.getY() < y + height + 256 || player.getY() > y - 256)
            playerInArea = true;
        if (player.getX() > x + width + 512 || player.getX() < x - 512 || player.getY() > y + height + 512 || player.getY() < y - 512)
            playerInArea = false;
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //Getters Setters

    public double getxMove() {
        return xMove;
    }

    public void setxMove(double xMove) {
        this.xMove = xMove;
    }

    public double getyMove() {
        return yMove;
    }

    public void setyMove(double yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


}
