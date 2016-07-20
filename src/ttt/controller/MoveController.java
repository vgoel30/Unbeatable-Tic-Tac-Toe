/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import java.util.ArrayList;
import ttt.data.Point;

/**
 *
 * @author varungoel
 */
public class MoveController {

    // 'X' will be considered as 1 -> the player
    // 'O' will be considered as 2 -> the AI
    

    void simulateMove(char[][] board, Point point, char player) {
        board[point.getRow()][point.getColumn()] = player;
    }

    public ArrayList<Point> getAvailablePoints(char[][] board) {
        int i, j;
        //the list of all the avaiable points on the grid
        ArrayList<Point> avaialablePoints = new ArrayList<>();
        //iterate over the 2D array to find all the empty squares
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    //i will be the row and j the column of the point to add
                    avaialablePoints.add(new Point(i, j));
                }
            }
        }
        return avaialablePoints;
    }

    /**
     * Function to check if the game is over
     *
     * @param board
     * @return if either one of the players has won
     */
    public boolean gameIsOver(char[][] board) {
        return playerHasWon(board, 'O') || playerHasWon(board, 'X');
    }

    /**
     * Checks to see if a player has won
     *
     * @param board is the current state of the tic-tac-toe board
     * @param player is the player for who went to check ('X' or 'O')
     * @return if a particular player has won the game
     */
    public boolean playerHasWon(char[][] board, char player) {

        //check for rows
        //check if the first row has equal elements
        boolean firstRowFull = (board[0][0] == player) && (board[0][0] == board[0][1] && board[0][1] == board[0][2]);
        if (firstRowFull) {
            return true;
        }
        //check if the second row has equal elements
        boolean secondRowFull = (board[1][0] == player) && (board[1][0] == board[1][1] && board[1][1] == board[1][2]);
        if (secondRowFull) {
            return true;
        }
        //check if the third row has equal elements
        boolean thirdRowFull = (board[2][0] == player) && (board[2][0] == board[2][1] && board[2][1] == board[2][2]);
        if (thirdRowFull) {
            return true;
        }

        //check for diagonals
        //check to see if the first diagonal is full
        boolean firstDiagonalFull = (board[0][0] == player) && (board[0][0] == board[1][1] && board[1][1] == board[2][2]);
        if (firstDiagonalFull) {
            return true;
        }
        //check to see if the second diagonal is full
        boolean secondDiagonalFull = (board[0][2] == player) && (board[0][2] == board[1][1] && board[1][1] == board[2][0]);
        if (secondDiagonalFull) {
            return true;
        }

        //check for columns now
        //check if the first column has equal elements
        boolean firstColumnFull = (board[0][0] == player) && (board[0][0] == board[1][0] && board[1][0] == board[2][0]);
        if (firstColumnFull) {
            return true;
        }
        //check if the second column has equal elements
        boolean secondColumnFull = (board[0][1] == player) && (board[0][1] == board[1][1] && board[1][1] == board[2][1]);
        if (secondColumnFull) {
            return true;
        }
        //check if the third column has equal elements
        boolean thirdColumnFull = (board[0][1] == player) && (board[0][2] == board[1][2] && board[1][2] == board[2][2]);
        if (thirdColumnFull) {
            return true;
        }

        //if by now, nothing has been returned, return false
        return false;
    }

}
