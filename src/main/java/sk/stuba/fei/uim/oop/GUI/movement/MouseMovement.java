package sk.stuba.fei.uim.oop.GUI.movement;

import sk.stuba.fei.uim.oop.GUI.MazeGUI;
import sk.stuba.fei.uim.oop.maze.MazeField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseMovement extends Movement implements MouseListener, MouseMotionListener {

    public MouseMovement(MazeGUI gui) {
        super(gui);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       MazeField clickedField = getGui().getMazeCanvas().getFieldFromPixels(e.getX(),e.getY());
       if(clickedField == getGui().getPlayer().getPosition()){
           getGui().getMazeCanvas().setShowingPossibleMovement(!getGui().getMazeCanvas().getShowingPossibleMovement());
       }
        if(getGui().getPlayer().rookMovementFields().contains(clickedField)){
            getGui().getPlayer().setPosition(clickedField);
        }
       getGui().repaintWindow();
//       System.out.println(field.getRow() + " " + field.getColumn());
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        getGui().getMazeCanvas().setHighlightedField(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MazeField enteredField = getGui().getMazeCanvas().getFieldFromPixels(e.getX(),e.getY());
        if(getGui().getPlayer().rookMovementFields().contains(enteredField)) {
            getGui().getMazeCanvas().highlightPossibleField(enteredField);
        }
        else{
            getGui().getMazeCanvas().setHighlightedField(null);
        }
        getGui().repaintWindow();
    }
}
