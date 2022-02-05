package sk.stuba.fei.uim.oop.GUI.movement;

import lombok.*;
import sk.stuba.fei.uim.oop.GUI.MazeGUI;


@Getter
@AllArgsConstructor
public class Movement {
    private MazeGUI gui;

    public void goDown(){
        getGui().getPlayer().goDown();
        getGui().repaintWindow();
    }

    public void goUp(){
        getGui().getPlayer().goUp();
        getGui().repaintWindow();
    }

    public void goLeft(){
        getGui().getPlayer().goLeft();
        getGui().repaintWindow();
    }

    public void goRight(){
        getGui().getPlayer().goRight();
        getGui().repaintWindow();
    }

}
