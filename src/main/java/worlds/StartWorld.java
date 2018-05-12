package worlds;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.staticEntities.BlueGate;
import entities.staticEntities.BlueHome;
import game.Handler;
import gfx.Assets;
import gfx.ImageLoader;

import java.awt.*;

public class StartWorld extends World {

    private BlueHome home;
    private BlueGate gate;

    private boolean keyOnPlayer;
    private boolean dialogStart;
    private boolean dialogStartAfterLogic;

    public StartWorld(Handler handler) {
        super(handler);

        loadWorld("/textworlds/start.txt");

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        keyOnPlayer = false;

        entityManager.addEntity(home = new BlueHome(handler, 6 * 64, 192));
        entityManager.addEntity(gate = new BlueGate(handler, 10 * 64, 256));

    }

    @Override
    public void tick() {
        entityManager.tick();
        keyOnPlayer();

    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok && !getEntityManager().getPlayer().startGameDialogShowed) {
            g.drawImage(Assets.opening, 0, 0, 800, 600, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            getEntityManager().getPlayer().setSpeed(Creature.DEFAULT_SPEED);
            dialogStart = true;
            getEntityManager().getPlayer().startGameDialogShowed = true;
        }

    }

    private void keyOnPlayer() {

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() + player.getBoundLogicY() < home.getY() + home.getHeight() &&
                player.getY() - player.getBoundLogicY() > home.getY() &&
                player.getX() + player.getBoundLogicX() + 1 < home.getX() + home.getBoundWidth() &&
                player.getX() - player.getBoundLogicX() - 1 > home.getX()) {
            keyOnPlayer = true;
        }
    }

    public boolean isPlayerOnPosition() {

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() + player.getBoundLogicY(
        ) < gate.getY() + gate.getHeight() &&
                player.getY() > gate.getY() &&
                player.getX() + player.getBoundX() < gate.getX() + gate.getBoundWidth() &&
                player.getX() - player.getBoundX() > gate.getX()) {
            return true;
        }
        return false;
    }

    public boolean isKeyOnPlayer() {
        return keyOnPlayer;
    }
}
