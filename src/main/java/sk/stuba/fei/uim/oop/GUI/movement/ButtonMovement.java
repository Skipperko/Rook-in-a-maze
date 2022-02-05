package sk.stuba.fei.uim.oop.GUI.movement;

import sk.stuba.fei.uim.oop.GUI.MazeGUI;
import javax.swing.*;

public class ButtonMovement extends Movement {

    public ButtonMovement(MazeGUI gui, JButton buttonUp, JButton buttonDown, JButton buttonLeft, JButton buttonRight) {
        super(gui);
        buttonDown.addActionListener((actionEvent)->goDown());
        buttonUp.addActionListener((actionEvent)->goUp());
        buttonLeft.addActionListener((actionEvent)->goLeft());
        buttonRight.addActionListener((actionEvent)->goRight());

    }

}
