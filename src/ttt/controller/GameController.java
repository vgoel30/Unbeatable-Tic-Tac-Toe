/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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

        //get the numerical values
        int row = boxID.charAt(0) - 48;
        int column = boxID.charAt(1) - 48;
        //set the clicked box to 'X
        board[row][column] = 'X';
        // printBoard(board);

        //now it is the computer's turn
        currentTurn = 'O';

        //proceed iff the game isn't over yet
        if (!moveController.gameIsOver(board)) {
            //call minimax to get the best move
            int[] result = moveController.minimax(board, 'O', 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            //now place the 'O' in the appropriate box
            //get the row and column of the optimal move
            int bestRow = result[1];
            int bestColumn = result[2];
            //the id of the box in which the move will be made
            String newBoxID = Integer.toString(bestRow) + Integer.toString(bestColumn);
            viewController.placeO(gameBoard, newBoxID);
            board[bestRow][bestColumn] = 'O';
            printBoard(board);
            //if X has won the game
            if (moveController.playerHasWon(board, 'X')) {
                System.out.println("X HAS WON THE GAME");
                resetGame();
            } else if (moveController.playerHasWon(board, 'O')) {
                //if the computer has won, ask for new game
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("You lost");
                alert.setHeaderText("The Computer has won");
                alert.setContentText("Do you want to play a new game?");

                Optional<ButtonType> confirmation = alert.showAndWait();
                if (confirmation.get() == ButtonType.OK) {
                    resetGame();
                } //exit if they don't want to play
                else {
                    System.exit(0);
                }
            } 
//            else if (moveController.boardIsFull(board)) {
//                Alert alert = new Alert(AlertType.CONFIRMATION);
//                alert.setTitle("Draw");
//                alert.setHeaderText("The game is a draw");
//                alert.setContentText("Do you want to play a new game?");
//
//                Optional<ButtonType> confirmation = alert.showAndWait();
//                if (confirmation.get() == ButtonType.OK) {
//                    resetGame();
//                } //exit if they don't want to play
//                else {
//                    System.exit(0);
//                }
//            }
        }
        //the game is a draw
        else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Draw");
                alert.setHeaderText("The game is a draw");
                alert.setContentText("Do you want to play a new game?");

                Optional<ButtonType> confirmation = alert.showAndWait();
                if (confirmation.get() == ButtonType.OK) {
                    resetGame();
                } 
                //exit if they don't want to play
                else {
                    System.exit(0);
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

    static void resetGame() {
        //empty the board
        int i, j = 0;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
        //reset the game board
        gameBoard.reset();
        //for some stupid reason, JavaFX dialog boxes change the background of the main scene to grey. This resets it wo white
        gameBoard.getMainScene().setStyle("-fx-background-color: white");
    }
}
