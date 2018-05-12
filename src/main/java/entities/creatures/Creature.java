package entities.creatures;

import entities.Entity;
import game.Handler;
import tiles.Tile;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final double DEFAULT_SPEED = 5.0;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected double speed;
    protected double xMove;
    protected double yMove;
    protected double currentSpeed;

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
