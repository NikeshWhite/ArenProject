package worlds;

import entities.creatures.Creature;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class LabyrinthWorld extends World {

    private boolean dialogStart;

    public LabyrinthWorld(Handler handler) {
        super(handler);

        if (Math.random() < 0.3) {
            loadWorld("/textworlds/lab.txt");
        } else if (Math.random() < 0.6 && Math.random() >= 0.3){
            loadWorld("/textworlds/lab0.txt");
        } else {
            loadWorld("/textworlds/lab1.txt");
        }

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    @Override
    public void tick() {
        entityManager.tick();

    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok) {
            g.drawImage(Assets.lab, 0, 0, 800, 600, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            getEntityManager().getPlayer().setSpeed(Creature.DEFAULT_SPEED);
            dialogStart = true;
        }
    }
}
