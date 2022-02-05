package sk.stuba.fei.uim.oop.GUI;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.MazeField;
import sk.stuba.fei.uim.oop.maze.Player;

import javax.swing.*;
import java.awt.*;
@RequiredArgsConstructor
@Getter
@Setter
public class MazeCanvas extends JPanel {
    @NonNull private Maze maze;
    @NonNull private Player player;
    private int canvasWidth;
    private int canvasHeight;
    private int fieldWidth;
    private int fieldHeight;
    private Boolean showingPossibleMovement = false;
    private MazeField highlightedField;


    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        this.setBackground(Color.WHITE);

        canvasWidth = this.getWidth();
        canvasHeight = this.getHeight();


        fieldWidth = canvasWidth/maze.getRows();
        fieldHeight = canvasHeight/maze.getColumns();

        drawSquares(maze.getRows(), maze.getColumns(),fieldWidth,fieldHeight, g);

        drawFinish(fieldWidth,fieldHeight,g);

        if(showingPossibleMovement){
            drawPossibleMovement(fieldWidth,fieldHeight,g);
        }
        if(highlightedField != null) {
            drawHighlightedPossibleField(fieldWidth, fieldHeight, g);
        }
        drawPlayer(fieldWidth,fieldHeight,g);
    }

    public void drawSquares(int rows, int columns,int fieldWidth, int fieldHeight,Graphics g){

        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                drawSquare(column*fieldWidth,row*fieldHeight,fieldWidth,fieldHeight, g,maze.getMazeFields().get(row).get(column));
            }
        }
    }

    public void drawFinish(int fieldWidth, int fieldHeight, Graphics g){
        MazeField finishPosition = maze.getFinishField();
        int finishRow = finishPosition.getRow();
        int finishColumn = finishPosition.getColumn();

        g.setColor(Color.BLUE);
        g.fillOval(15+finishColumn*fieldWidth,finishRow*fieldHeight+15,fieldWidth-30,fieldHeight-30);
    }

    public void drawPlayer(int fieldWidth, int fieldHeight, Graphics g){
        MazeField playerPosition = getPlayer().getPosition();
        int playerRow = playerPosition.getRow();
        int playerColumn = playerPosition.getColumn();

        g.setColor(Color.RED);
        g.fillRect(30+playerColumn*fieldWidth,playerRow*fieldHeight+30,fieldWidth-60,fieldHeight-60);
    }

    public void drawSquare(int x, int y, int width, int height, Graphics g, MazeField field){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(20,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));

        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);

        //Just Right Wall
        if(field.getRightWall()) {
            g.drawLine(x + width, y + height+10, x + width, y);
                    }
        //Just Bottom Wall
        if(field.getBottomWall()) {
            g.drawLine(x+width, y + height, x, y + height);
        }
        //Just Top Wall
        if(field.getTopWall()) {
            g.drawLine(x+width, y, x , y);
        }
        //Just Left Wall
        if(field.getLeftWall()) {
            g.drawLine(x, y + height, x, y);
        }
        //g.drawString("r= " + field.getRow() + "c= " + field.getColumn(),x+20,y+20);
    }

    public MazeField getFieldFromPixels(int x, int y){
        int fieldX = x/fieldWidth;
        int fieldY = y/fieldHeight;

        if(fieldX >= maze.getRows()){
            fieldX = maze.getRows() -1;
        }
        if(fieldY >= maze.getColumns()){
            fieldY = maze.getColumns() -1;
        }
        MazeField mazeField = maze.getMazeField(fieldY, fieldX);
        return mazeField;
    }

    public void drawPossibleMovement(int fieldWidth, int fieldHeight, Graphics g){
        var possibleMovements = player.rookMovementFields();
        for(int i = 0; i < possibleMovements.size(); i++){
            MazeField playerPosition = possibleMovements.get(i);
            int fieldRow = playerPosition.getRow();
            int fieldColumn = playerPosition.getColumn();

            g.setColor(Color.GREEN);
            g.fillRect(30+fieldColumn*fieldWidth,fieldRow*fieldHeight+30,fieldWidth-60,fieldHeight-60);
        }

    }

    public void highlightPossibleField(MazeField field){
        highlightedField = field;
    }

    public void drawHighlightedPossibleField(int fieldWidth, int fieldHeight, Graphics g){
        int fieldRow = highlightedField.getRow();
        int fieldColumn = highlightedField.getColumn();

        g.setColor(Color.ORANGE);
        g.fillRect(30+fieldColumn*fieldWidth,fieldRow*fieldHeight+30,fieldWidth-60,fieldHeight-60);
    }

}
