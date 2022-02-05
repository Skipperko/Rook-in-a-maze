package sk.stuba.fei.uim.oop.GUI.movement;

import lombok.NonNull;
import sk.stuba.fei.uim.oop.GUI.MazeGUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class KeyMovement extends Movement implements KeyListener{

    public KeyMovement(@NonNull MazeGUI gui) {
        super(gui);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);
        Map<Integer, Runnable> movement = Map.of(
                KeyEvent.VK_DOWN, () -> goDown(),
                KeyEvent.VK_UP, () -> goUp(),
                KeyEvent.VK_LEFT, () -> goLeft(),
                KeyEvent.VK_RIGHT, () -> goRight());
        movement.get(key).run();


    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
