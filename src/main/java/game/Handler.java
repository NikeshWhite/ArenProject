package game;

import gfx.GameCamera;
import input.KeyManager;
import worlds.*;

public class Handler {

    private Game game;
    private World world;

    private StartWorld startWorld = new StartWorld(this);
    private LogicWorld logicWorld = new LogicWorld(this);
    private LabyrinthWorld labyrinthWorld = new LabyrinthWorld(this);
    private StartWorldStage2 startWorldStage2 = new StartWorldStage2(this);
    private StartWorldStage3 startWorldStage3 = new StartWorldStage3(this);
    private ArenaWorld arenaWorld = new ArenaWorld(this);
    private ArenaWorldStage2 arenaWorldStage2 = new ArenaWorldStage2(this);
    private ArenaWorldStage3 arenaWorldStage3 = new ArenaWorldStage3(this);
    private HomeStartWorld homeStartWorld = new HomeStartWorld(this);

    public Handler(Game game) {
        this.game = game;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        if (world != startWorld)
            world.getEntityManager().getPlayer().setHealth(this.world.getEntityManager().getPlayer().getHealth());
        this.world = world;
    }

    public StartWorld getStartWorld() {
        return startWorld;
    }

    public LogicWorld getLogicWorld() {
        return logicWorld;
    }

    public LabyrinthWorld getLabyrinthWorld() {
        return labyrinthWorld;
    }

    public StartWorldStage3 getStartWorldStage3() {
        return startWorldStage3;
    }

    public StartWorldStage2 getStartWorldStage2() {
        return startWorldStage2;
    }

    public ArenaWorld getArenaWorld() {
        return arenaWorld;
    }

    public ArenaWorldStage2 getArenaWorldStage2() {
        return arenaWorldStage2;
    }

    public ArenaWorldStage3 getArenaWorldStage3() {
        return arenaWorldStage3;
    }

    public HomeStartWorld getHomeStartWorld() {
        return homeStartWorld;
    }
}
