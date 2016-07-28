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

    static MoveController moveController;
    static ViewController viewController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        getBoardReady();

        Scene scene = new Scene(gameBoard.getMainScene(), 600, 650);
        primaryStage.setScene(scene);
        //fixed dimensions for the board (450 * 450)
        primaryStage.setMaxHeight(650);
        primaryStage.setMaxWidth(650);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(650);

        primaryStage.show();

    }

    static void getBoardReady() {
        //inititalize the 2D array
        board = new char[3][3];
        moveController = new MoveController();
        viewController = new ViewController();
        gameBoard = new GameBoard();
        gameBoard.layoutGUI();
    }

    static void handleBoxClick(String boxID) {
        //proceed iff the game isn't over yet
        if (!moveController.gameIsOver(board)) {
            //get the numerical values
            int row = boxID.charAt(0) - 48;
            int column = boxID.charAt(1) - 48;
            //set the clicked box to 'X
            board[row][column] = 'X';
            // printBoard(board);

            //now it is the computer's turn
            currentTurn = 'O';
            //call minimax to get the best move
            int[] result = moveController.minimax(board, 'O', 2);
            //now place the 'O' in the appropriate box
            //get the row and column of the optimal move
            int bestRow = result[1];
            int bestColumn = result[2];
            //the id of the box in which the move will be made
            String newBoxID = Integer.toString(bestRow) + Integer.toString(bestColumn);
            viewController.placeO(gameBoard,newBoxID);
            board[bestRow][bestColumn] = 'O';
            printBoard(board);
            if (moveController.playerHasWon(board, 'X')) {
                System.out.println("X HAS WON THE GAME");
            } else if (moveController.playerHasWon(board, 'O')) {
                System.out.println("O HAS WON THE GAME");
            }
        }
    }

    static void printBoard(char[][] board) {
        int i = 0, j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
