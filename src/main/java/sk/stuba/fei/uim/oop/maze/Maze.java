package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class Maze {
    private List<List<MazeField>> mazeFields;
    private int rows;
    private int columns;
    private Random random;

    public Maze(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.random = new Random();
        //this.random.setSeed(1);

        fillMazeFields();
    }

    private void fillMazeFields() {
        mazeFields = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            ArrayList<MazeField> fields = new ArrayList<>();
            mazeFields.add(fields);
            for(int j = 0; j < columns; j++){
                MazeField field = new MazeField(i,j);
                fields.add(field);
            }
        }
    }

    private void connectFields(MazeField firstField, MazeField secondField){
        int firstFieldRow = firstField.getRow();
        int firstFieldColumn = firstField.getColumn();

        int secondFieldRow = secondField.getRow();
        int secondFieldColumn = secondField.getColumn();

        if(firstFieldRow != secondFieldRow && firstFieldColumn != secondFieldColumn){
            throw new MazeFieldsUnconnectableException();
        }

        if((Math.abs(firstFieldRow - secondFieldRow) > 1) || (Math.abs(firstFieldColumn - secondFieldColumn) > 1)){
            throw new MazeFieldsUnconnectableException();
        }

        if(firstFieldRow == secondFieldRow){
            if(firstFieldColumn > secondFieldColumn){
                firstField.setLeftWall(false);
                secondField.setRightWall(false);
            }
            else {
                firstField.setRightWall(false);
                secondField.setLeftWall(false);
            }
        }

        if(firstFieldColumn == secondFieldColumn){
            if(firstFieldRow < secondFieldRow){
                firstField.setBottomWall(false);
                secondField.setTopWall(false);
            }
            else{
                firstField.setTopWall(false);
                secondField.setBottomWall(false);
            }
        }
    }

    public void randomize(){
        MazeField startingField = getStartingField();
        randomizedDFS(startingField);

    }

    public MazeField getStartingField() {

        return getMazeField(0, 0);
    }

    public MazeField getFinishField(){
        return getMazeField(rows-1,columns-1);
    }

    public MazeField getMazeField(int row, int column) {

        return mazeFields.get(row).get(column);
    }

    private void randomizedDFS(MazeField field){
        field.setVisited(true);
        MazeField nextNeighbour = getUnvisitedNeighbour(field);
        while(nextNeighbour != null){
            connectFields(field,nextNeighbour);
            randomizedDFS(nextNeighbour);
            nextNeighbour = getUnvisitedNeighbour(field);
        }
    }

    private MazeField getUnvisitedNeighbour(MazeField startingField){
        ArrayList<MazeCoordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new MazeCoordinates(startingField.getRow()-1, startingField.getColumn()));
        coordinatesList.add(new MazeCoordinates(startingField.getRow(), startingField.getColumn()-1));
        coordinatesList.add(new MazeCoordinates(startingField.getRow(), startingField.getColumn()+1));
        coordinatesList.add(new MazeCoordinates(startingField.getRow()+1, startingField.getColumn()));

        Collections.shuffle(coordinatesList,this.random);
        for (MazeCoordinates x: coordinatesList) {
            if(areCoordinatesValid(x)){
                return getMazeField(x.getRow(), x.getColumn());
            }
        }
        return null;
    }

    private boolean areCoordinatesValid(MazeCoordinates fieldCoordinates){
        if(fieldCoordinates.getColumn() >= this.columns || fieldCoordinates.getColumn() < 0){
            return false;
        }
        if(fieldCoordinates.getRow() >= this.rows || fieldCoordinates.getRow() < 0){
            return false;
        }
        return !getMazeField(fieldCoordinates.getRow(), fieldCoordinates.getColumn()).getVisited();
    }

}
