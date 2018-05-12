package states;

import entities.Entity;
import entities.creatures.Creature;
import entities.creatures.Player;
import game.Handler;
import gfx.Assets;
import gfx.ImageLoader;
import worlds.*;

import java.awt.*;

public class GameState extends State {

    private World world, world2, world3, world4;
    private Player player;

    private StartWorld startWorld;
    private LogicWorld logicWorld;
    private LabyrinthWorld labyrinthWorld;
    private StartWorldStage2 startWorldStage2;


    public GameState(Handler handler) {
        super(handler);

        startWorld = new StartWorld(handler);
        //logicWorld = new LogicWorld(handler);
        handler.setWorld(startWorld);


    }

    @Override
    public void tick() {
        handler.getWorld().tick();
        changeWorlds();
    }

    @Override
    public void render(Graphics g) {

        handler.getWorld().render(g);

    }

    private void changeWorlds() {

        if(handler.getWorld() == startWorld) {

            player = handler.getWorld().getEntityManager().getPlayer();

            if(startWorld.isKeyOnPlayer() && startWorld.isPlayerOnPosition()) {
                labyrinthWorld = new LabyrinthWorld(handler);
                handler.setWorld(labyrinthWorld);
            }
        }

        if(handler.getWorld() == labyrinthWorld) {

            player = handler.getWorld().getEntityManager().getPlayer();

            if(player.getY() > 0 && player.getY() < 64) {
                logicWorld = new LogicWorld(handler);
                handler.setWorld(logicWorld);
            }
        }

        if(handler.getWorld() == logicWorld) {

            player = handler.getWorld().getEntityManager().getPlayer();

            if(player.getY() > 0 && player.getY() < 10) {
                startWorldStage2 = new StartWorldStage2(handler);
                handler.setWorld(startWorldStage2);
                handler.getWorld().getEntityManager().getPlayer().logicIsDone = true;
            }
        }

    }

    public void fight() {

        var e = handler.getWorld().getEntityManager().getEntities();



        /*if(handler.getWorld().getEntityManager().getPlayer().getSword().checkEntityCollisions()) {

        }*/

    }

}
