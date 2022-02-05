package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.GUI.MazeGUI;
import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.Player;

public class MazeGame {
    private MazeGUI gui;
    private int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
        gui.setCounter(counter);
    }

    public void startGame(){
        Maze maze = new Maze(11,11);
        Player player = new Player(maze);
        setDefaultGUI(maze,player);
    }

    public void setDefaultGUI(Maze maze, Player player){
        gui = new MazeGUI(maze,player);
        gui.createWindow();
        gui.getRestartButton().addActionListener((actionEvent)->{ restartMaze();});
        restartMaze();
    }

    public void restartMaze(){
        generateRandomMaze();
        setCounter(0);
    }

    private void generateRandomMaze() {
        Maze maze = new Maze(20,20);
        Player player = new Player(maze);
        player.setOnPlayerFinished(()->onFinished());
        maze.randomize();
        gui.setMaze(maze);
        gui.setPlayer(player);
        gui.repaintWindow();
    }


    public void onFinished(){
        System.out.println("In finish");
        setCounter(counter+1);
        generateRandomMaze();

    }
}
