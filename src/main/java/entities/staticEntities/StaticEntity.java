package entities.staticEntities;

import entities.Entity;
import game.Handler;

import java.awt.*;

public class StaticEntity extends Entity {

    public StaticEntity(Handler handler, double x, double y, int width, int height) {
        super(handler, x, y, width, height);

        health = ENDLESS_HEALTH;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {

    }
}
