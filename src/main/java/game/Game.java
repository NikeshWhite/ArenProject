package game;

        import gfx.Assets;
        import gfx.GameCamera;
        import input.KeyManager;
        import states.GameState;
        import states.State;
        import window.Window;

        import java.awt.*;
        import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    //Initialization Window
    private Window window;
    public String title;
    private int width, height;

    //run
    private boolean running = false;
    private Thread thread;

    //Graphics
    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;


    public Game(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() {
        window = new Window(width, height, title);
        window.getFrame().addKeyListener(keyManager);

        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        State.setState(gameState);
    }


    private void tick() {

        keyManager.tick();

        if (State.getState() != null)
            State.getState().tick();
    }

    private void render() {

        bs = window.getCanvas().getBufferStrategy();
        if (bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear
        g.clearRect(0, 0, width, height);

        //Draw
        if (State.getState() != null)
            State.getState().render(g);

        //Close
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public State getGameState() {
        return gameState;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


