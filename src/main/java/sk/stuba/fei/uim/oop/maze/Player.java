package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Player {
    private MazeField position;
    private Maze maze;
    private Runnable onPlayerFinished;

    public Player(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartingField();
    }

    public void setPosition(MazeField position) {
        this.position = position;
        checkIfInFinish();

    }

    public void goDown() {
        if (!position.getBottomWall()) {
            this.position = maze.getMazeField(position.getRow()+1, position.getColumn());
            checkIfInFinish();
        }
    }

    public void goUp() {
        if (!position.getTopWall()) {
            this.position = maze.getMazeField(position.getRow()-1, position.getColumn());
            checkIfInFinish();
        }
    }

    public void goLeft() {
        if (!position.getLeftWall()) {
            this.position = maze.getMazeField(position.getRow(), position.getColumn()-1);
            checkIfInFinish();
        }
    }

    public void goRight() {
        if (!position.getRightWall()) {
            this.position = maze.getMazeField(position.getRow() , position.getColumn()+1);
            checkIfInFinish();
        }
    }

    public void checkIfInFinish(){
        if(this.getPosition() == maze.getFinishField() && onPlayerFinished != null){
            onPlayerFinished.run();
        }
    }

    public List<MazeField> rookMovementFields() {
        List<MazeField> allMovements = new ArrayList<>();
        Player virtualPlayer = new Player(maze);
        virtualPlayer.setPosition(this.getPosition());

        virtualRightWallCheck(allMovements, virtualPlayer);
        virtualLeftWallCheck(allMovements, virtualPlayer);
        virtualTopWallCheck(allMovements, virtualPlayer);
        virtualBottomWallCheck(allMovements, virtualPlayer);

        return allMovements;
    }

    private void virtualBottomWallCheck(List<MazeField> allMovements, Player virtualPlayer) {
        while (!virtualPlayer.getPosition().getBottomWall()) {
            virtualPlayer.goDown();
            allMovements.add(virtualPlayer.getPosition());
        }
        virtualPlayer.setPosition(this.getPosition());
    }

    private void virtualTopWallCheck(List<MazeField> allMovements, Player virtualPlayer) {
        while (!virtualPlayer.getPosition().getTopWall()) {
            virtualPlayer.goUp();
            allMovements.add(virtualPlayer.getPosition());
        }
        virtualPlayer.setPosition(this.getPosition());
    }

    private void virtualLeftWallCheck(List<MazeField> allMovements, Player virtualPlayer) {
        while (!virtualPlayer.getPosition().getLeftWall()) {
            virtualPlayer.goLeft();
            allMovements.add(virtualPlayer.getPosition());
        }
        virtualPlayer.setPosition(this.getPosition());
    }

    private void virtualRightWallCheck(List<MazeField> allMovements, Player virtualPlayer) {
        while (!virtualPlayer.getPosition().getRightWall()) {
            virtualPlayer.goRight();
            allMovements.add(virtualPlayer.getPosition());
        }
        virtualPlayer.setPosition(this.getPosition());
    }


}