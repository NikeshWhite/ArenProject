package worlds;

import entities.creatures.Creature;
import entities.creatures.Player;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class LabyrinthWorld extends World {

    private boolean dialogStart;

    private Player player;

    public LabyrinthWorld(Handler handler) {
        super(handler);

        if (Math.random() < 0.3) {
            loadWorld("/textworlds/lab.txt");
        } else if (Math.random() < 0.6 && Math.random() >= 0.3) {
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
        toLogic();

    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok) {
            Color color1 = new Color(0, 0, 0, 200);
            g.setColor(color1);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(Assets.lab, 100, 350, 600, 200, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            getEntityManager().getPlayer().setSpeed(Creature.DEFAULT_SPEED);
            dialogStart = true;
        }
    }

    private void toLogic() {
        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() > 0 && player.getY() < 64) {

            handler.setWorld(handler.getLogicWorld());
        }
    }
}
