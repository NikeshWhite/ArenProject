package entities;

import game.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected double x, y;
    protected int width, height;
    protected int health;
    protected Rectangle bounds;

    protected int deltaHealth;

    protected boolean alive = true;

    public static final int DEFAULT_HEALTH = 10;
    public static final int ENDLESS_HEALTH = -1124;

    public Entity (Handler handler, double x, double y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt (int attack) {
        health -= attack;

        if (health <= 0) {
            alive = false;
            die();
        }
    }

    public boolean checkEntityCollisions(double xOffset, double yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;

            if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(double xOffset, double yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getBoundX() {
        return bounds.x;
    }

    public int getBoundY() {
        return bounds.y;
    }

    public int getBoundWidth() {
        return bounds.width;
    }

    public int getBoundHeight() {
        return bounds.height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
