/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ttt.data.Point;
import ttt.gui.GameBoard;

/**
 *
 * @author varungoel
 */
public class GameController extends Application {
    static GameBoard gameBoard;

    static char currentTurn;

    static char[][] board;

    static ArrayList<Point> availablePoints;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        getBoardReady();

        Scene scene = new Scene(gameBoard.getBoard(), 450, 450);
        primaryStage.setScene(scene);
        //fixed dimensions for the board (450 * 450)
        primaryStage.setMaxHeight(450);
        primaryStage.setMaxWidth(450);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(450);

        primaryStage.show();

    }

    static void getBoardReady() {
        board = new char[3][3];
        gameBoard = new GameBoard();
        gameBoard.layoutGUI();
    }

    static void handleBoxClick(String boxID) {
        //get the numerical values
        int row = boxID.charAt(0) - 48;
        int column = boxID.charAt(1) - 48;
        //set the clicked box to 'X
        board[row][column] = 'X';
        printBoard(board);
        //now it is the computer's turn
        currentTurn = 'O';
    }

    static void printBoard(char[][] board) {
        int i = 0, j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
