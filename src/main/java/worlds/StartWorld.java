package worlds;

import entities.creatures.ArenaBoss;
import entities.creatures.ArenaEnemySt2;
import entities.creatures.Player;
import entities.staticEntities.BlueGate;
import entities.staticEntities.BlueHome;
import entities.staticEntities.WoodenGate;
import game.Handler;
import gfx.Assets;

import java.awt.*;

public class StartWorld extends World {

    public BlueHome home;
    private BlueGate gate;
    private WoodenGate woodenGate;

    private ArenaBoss arenaBoss;

    private ArenaEnemySt2 arenaEnemy;

    private Player player;

    private boolean dialogStart;
    private boolean dialogStartAfterLogic;

    public StartWorld(Handler handler) {
        super(handler);

        loadWorld("/textworlds/start.txt");

        entityManager.getPlayer().setX(spawnX + 32);
        entityManager.getPlayer().setY(spawnY - 32);

        entityManager.addEntity(home = new BlueHome(handler, 8 * 64, 5 * 64));
        entityManager.addEntity(gate = new BlueGate(handler, 11 * 64, 6 * 64));
        entityManager.addEntity(woodenGate = new WoodenGate(handler, 5 * 64, 6 * 64));

        /*entityManager.addEntity(arenaBoss = new ArenaBoss(handler, 6 * 64, 10 * 64));
        entityManager.addEntity(arenaEnemy = new ArenaEnemySt2(handler, 3 * 64, 10 * 64));*/
    }

    @Override
    public void tick() {
        entityManager.tick();
        toHome();
        toLabyrinth();
    }

    @Override
    public void render(Graphics g) {

        worldRendering(g);
        entityManager.render(g);

        if (!dialogStart && !handler.getKeyManager().ok && !getEntityManager().getPlayer().startGameDialogShowed) {
            Color color1 = new Color(0, 0, 0, 200);
            g.setColor(color1);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(Assets.opening, 100, 350, 600, 200, null);
            getEntityManager().getPlayer().setSpeed(0);
        } else {
            dialogStart = true;
            getEntityManager().getPlayer().startGameDialogShowed = true;
        }
    }

    public boolean isPlayerOnPosition() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() < gate.getY() + gate.getHeight() &&
                player.getY() > gate.getY() &&
                player.getX() < gate.getX() + gate.getBoundWidth() &&
                player.getX() > gate.getX()) {
            return true;
        }
        return false;
    }

    private void toHome() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.getY() < home.getY() + home.getBoundHeight() &&
                player.getY() > home.getY() &&
                player.getX() < home.getX() + home.getBoundWidth() &&
                player.getX() > home.getX()) {

            handler.setWorld(handler.getHomeStartWorld());
        }
    }

    private void toLabyrinth() {

        player = handler.getWorld().getEntityManager().getPlayer();

        if (player.keyOnPlayer && isPlayerOnPosition()) {
            handler.setWorld(handler.getLabyrinthWorld());
        }
    }
}



