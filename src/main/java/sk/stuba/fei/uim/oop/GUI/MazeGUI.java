package sk.stuba.fei.uim.oop.GUI;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.GUI.movement.ButtonMovement;
import sk.stuba.fei.uim.oop.GUI.movement.KeyMovement;
import sk.stuba.fei.uim.oop.GUI.movement.MouseMovement;
import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.Player;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@RequiredArgsConstructor
public class MazeGUI{
    @NonNull private Maze maze;
    @NonNull private Player player;
    private KeyMovement keyMovement;
    private ButtonMovement buttonMovement;
    private JFrame window;
    private JButton restartButton;
    private MazeCanvas mazeCanvas;
    private JLabel gamesWon;
    private MouseMovement mouseMovement;


    public void setMaze(Maze maze){
        this.maze = maze;
        mazeCanvas.setMaze(maze);
    }

    public void setPlayer(Player player){
        this.player = player;
        mazeCanvas.setPlayer(player);
    }


    public void createWindow(){
        window = new JFrame();

        window.setSize(920,1000);

        GridBagConstraints c = new GridBagConstraints();


        var windowLayout = new GridBagLayout();
        window.setLayout(windowLayout);

        var panel = new JPanel();
        c.gridy = 0;
        //c.anchor = GridBagConstraints.CENTER;

        var panelLayout = new GridLayout(2,3);

        panel.setLayout(panelLayout);
        window.add(panel,c);

        restartButton = new JButton("Restart");
        panel.add(restartButton);
        restartButton.setFocusable(false);
        var buttonUp = new JButton("Up");
        panel.add(buttonUp);
        buttonUp.setFocusable(false);
        gamesWon = new JLabel("Games won : " + gamesWon);
        panel.add(gamesWon);
        var buttonLeft = new JButton("Left");
        panel.add(buttonLeft);
        buttonLeft.setFocusable(false);
        var buttonDown = new JButton("Down");
        panel.add(buttonDown);
        buttonDown.setFocusable(false);
        var buttonRight = new JButton("Right");
        panel.add(buttonRight);
        buttonRight.setFocusable(false);

        mazeCanvas = generateCanvas(maze,player);

        buttonMovement = new ButtonMovement(this,buttonUp,buttonDown,buttonLeft,buttonRight);


        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1;
        c.weighty = 0.9;
        window.add(mazeCanvas, c);
        mazeCanvas.setMinimumSize(new Dimension(600,600));
        window.setFocusable(true);
        window.setVisible(true);
        keyMovement = new KeyMovement(this);
        window.addKeyListener(keyMovement);

        mouseMovement = new MouseMovement(this);
        mazeCanvas.addMouseListener(mouseMovement);
        mazeCanvas.addMouseMotionListener(mouseMovement);

    }
    public void repaintWindow() {
        this.window.revalidate();
        this.window.repaint();
    }

    public MazeCanvas generateCanvas(Maze maze, Player player){
        return new MazeCanvas(maze, player);
    }

    public void setCounter(int gamesWonCount){
        gamesWon.setText("Games won : " + gamesWonCount);
    }
}
