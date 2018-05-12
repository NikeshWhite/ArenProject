package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    private boolean[] actions;
    private boolean[] enter;
    private boolean[] justPressed;
    private boolean[] cantPress;
    public boolean up, down, left, right, shift, action, respect, ok, attack;

    public KeyManager() {
        keys = new boolean[256];
        actions = new boolean[256];
        enter = new boolean[256];

        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {

        for (int i = 0; i < keys.length; i++) {
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if (justPressed[i]) {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
            
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        shift = keys[KeyEvent.VK_SHIFT];

        action = actions[KeyEvent.VK_E];
        respect = justPressed[KeyEvent.VK_F];

        attack = justPressed[KeyEvent.VK_SPACE];

        ok = justPressed[KeyEvent.VK_ENTER];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        actions[e.getKeyCode()] = !actions[e.getKeyCode()];
        enter[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
