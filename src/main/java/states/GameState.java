package states;

import entities.creatures.Player;
import game.Handler;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Handler handler) {
        super(handler);

        handler.setWorld(handler.getStartWorld());
    }

    @Override
    public void tick() {
        handler.getWorld().tick();
    }

    @Override
    public void render(Graphics g) {
        handler.getWorld().render(g);
    }

}
