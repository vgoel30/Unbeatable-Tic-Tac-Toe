/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author varungoel
 */
public class GameBoard {

    VBox boardContainer;

    ArrayList<HBox> rows;

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
        boardContainer = new VBox();

        firstRow = new HBox();
        secondRow = new HBox();
        thirdRow = new HBox();

        //all the rows in the tic tac toe grid
        rows = new ArrayList<>(Arrays.asList(firstRow, secondRow, thirdRow));

        for (HBox row : rows) {
            boardContainer.getChildren().add(row);
        }

        firstRow.setStyle("-fx-background-color: orange");
        secondRow.setStyle("-fx-background-color: white");
        thirdRow.setStyle("-fx-background-color: green");

        initStyle();
    }

    public void initStyle() {
        for (HBox row : rows) {
            row.setMinSize(600, 200);
            row.setMaxSize(600, 200);
        }
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
