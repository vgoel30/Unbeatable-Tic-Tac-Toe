/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ttt.controller.ViewController;

/**
 *
 * @author varungoel
 */
public class GameBoard {
    ViewController viewController;
    
    VBox mainScene;

    VBox boardContainer;

    ArrayList<HBox> rows;
    ArrayList<Pane> boxes;

    HBox firstRow;
    HBox secondRow;
    HBox thirdRow;
    //the first row
    Pane boxOne;
    Pane boxTwo;
    Pane boxThree;
    //the second row
    Pane boxFour;
    Pane boxFive;
    Pane boxSix;
    //the third row
    Pane boxSeven;
    Pane boxEight;
    Pane boxNine;

    public void layoutGUI() {
        viewController = new ViewController();
        
        mainScene = new VBox(0);
        boardContainer = new VBox();

        firstRow = new HBox();
        secondRow = new HBox();
        thirdRow = new HBox();

        //all the rows in the tic tac toe grid
        rows = new ArrayList<>(Arrays.asList(firstRow, secondRow, thirdRow));
        //add all the rows into the board
        for (HBox row : rows) {
            boardContainer.getChildren().add(row);
        }

        //all the boxes (9) on the grid
        boxes = new ArrayList<>();

        //set up all the rows
        makeFirstRow();
        makeSecondRow();
        makeThirdRow();

        for (HBox row : rows) {
            row.setMinSize(450, 150);
            row.setMaxSize(450, 150);
        }
        
        mainScene.getChildren().add(boardContainer);
        mainScene.setPadding(new Insets(80,20,20,100));
        
        viewController.attachEventHandlers(boxes);
    }

    public void makeFirstRow() {
        boxOne = new AnchorPane();
        boxTwo = new AnchorPane();
        boxThree = new AnchorPane();
        //set IDs for the boxes corresponding to their 2D array index
        boxOne.setId("00");
        boxTwo.setId("01");
        boxThree.setId("02");

        ArrayList<Pane> firstRowBoxes = new ArrayList<>(Arrays.asList(boxOne, boxTwo, boxThree));

        for (Pane rowBox : firstRowBoxes) {
            firstRow.getChildren().add(rowBox);
            boxes.add(rowBox); //add the boxes of the first row to the list of boxes
            rowBox.setMinSize(150, 150);
            rowBox.setMaxSize(150, 150);
            rowBox.setStyle("-fx-background-color: white");
        }
        String cssString = "-fx-border-color: black;\n"
                + "-fx-border-width: 0 3 3 0;\n"
                + "-fx-border-style: solid;\n";

        boxOne.setStyle(cssString);
        boxTwo.setStyle(cssString);

        cssString = "-fx-border-color: black;\n"
                + "-fx-border-width: 0 0 3 0;\n"
                + "-fx-border-style: solid;\n";
        boxThree.setStyle(cssString);

    }

    public void makeSecondRow() {
        boxFour = new AnchorPane();
        boxFive = new AnchorPane();
        boxSix = new AnchorPane();
        //set IDs for the boxes corresponding to their 2D array index
        boxFour.setId("10");
        boxFive.setId("11");
        boxSix.setId("12");

        ArrayList<Pane> secondRowBoxes = new ArrayList<>(Arrays.asList(boxFour, boxFive, boxSix));

        for (Pane rowBox : secondRowBoxes) {
            secondRow.getChildren().add(rowBox);
            boxes.add(rowBox);
            rowBox.setMinSize(150, 150);
            rowBox.setMaxSize(150, 150);
            rowBox.setStyle("-fx-background-color: white");

            String cssString = "-fx-border-color: black;\n"
                    + "-fx-border-width: 0 3 3 0;\n"
                    + "-fx-border-style: solid;\n";

            boxFour.setStyle(cssString);
            boxFive.setStyle(cssString);

            cssString = "-fx-border-color: black;\n"
                    + "-fx-border-width: 0 0 3 0;\n"
                    + "-fx-border-style: solid;\n";
            boxSix.setStyle(cssString);
        }
    }

    public void makeThirdRow() {
        boxSeven = new AnchorPane();
        boxEight = new AnchorPane();
        boxNine = new AnchorPane();
        //set IDs for the boxes corresponding to their 2D array index
        boxSeven.setId("20");
        boxEight.setId("21");
        boxNine.setId("22");

        ArrayList<Pane> thirdRowBoxes = new ArrayList<>(Arrays.asList(boxSeven, boxEight, boxNine));

        for (Pane rowBox : thirdRowBoxes) {
            thirdRow.getChildren().add(rowBox);
            boxes.add(rowBox);
            rowBox.setMinSize(150, 150);
            rowBox.setMaxSize(150, 150);

            String cssString = "-fx-border-color: black;\n"
                    + "-fx-border-width: 0 3 0 0;\n"
                    + "-fx-border-style: solid;\n";

            boxSeven.setStyle(cssString);
            boxEight.setStyle(cssString);

            cssString = "-fx-border-color: black;\n"
                    + "-fx-border-width: 0 0 0 0;\n"
                    + "-fx-border-style: solid;\n";
            boxNine.setStyle(cssString);
        }
    }
    
    public VBox getMainScene(){
        return mainScene;
    }

    public VBox getBoard() {
        return boardContainer;
    }

    public HBox getFirstRow() {
        return firstRow;
    }

    public HBox getSecondRow() {
        return secondRow;
    }

    public HBox getThirdRow() {
        return thirdRow;
    }

    public Pane getBoxOne() {
        return boxOne;
    }

    public Pane getBoxTwo() {
        return boxTwo;
    }

    public Pane getBoxThree() {
        return boxThree;
    }

    public Pane getBoxFour() {
        return boxFour;
    }

    public Pane getBoxFive() {
        return boxFive;
    }

    public Pane getBoxSix() {
        return boxSix;
    }

    public Pane getBoxSeven() {
        return boxSeven;
    }

    public Pane getBoxEight() {
        return boxEight;
    }

    public Pane getBoxNine() {
        return boxNine;
    }

}
