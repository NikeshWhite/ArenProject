package worlds;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.staticEntities.BlueGate;
import entities.staticEntities.BlueHome;
import entities.staticEntities.WoodenGate;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class StartWorldStage3 extends World {

    private BlueHome home;
    private BlueGate gate;
    private WoodenGate woodenGate;

    private Player player;

    private boolean keyOnPlayer;
    private boolean dialogStart;

    public StartWorldStage3(Handler handler) {
        super(handler);

        loadWorld("/textworlds/start.txt");

        entityManager.getPlayer().setX(spawnX + 32);
        entityManager.getPlayer().setY(spawnY);

        keyOnPlayer = false;

        entityManager.addEntity(home = new BlueHome(handler, 8 * 64, 5 * 64));
        entityManager.addEntity(gate = new BlueGate(handler, 11 * 64, 6 * 64));
        entityManager.addEntity(woodenGate = new WoodenGate(handler, 5 * 64, 6 * 64));

    }

    @Override
    public void tick() {
        entityManager.tick();

    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok && !getEntityManager().getPlayer().startGameDialogShowed) {
            Color color1 = new Color(0, 0, 0, 200);
            g.setColor(color1);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(Assets.end, 100, 350, 600, 200, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            getEntityManager().getPlayer().setSpeed(Creature.DEFAULT_SPEED);
            dialogStart = true;
            getEntityManager().getPlayer().startGameDialogShowed = true;
        }

    }

    public WoodenGate getWoodenGate() {
        return woodenGate;
    }

}
