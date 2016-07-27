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
    int[] minimax(char[][] board, char currentTurn, int depth) {
        //get all the points that are empty
        ArrayList<Point> availablePoints = getAvailablePoints(board);
        //we want to get the maximum value score for ('O'): the AI
        int bestScore = (currentTurn == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        //the row and column that will lead to the best outcome
        int bestRow = -1;
        int bestColumn = -1;
        //if no more moves are possible, check for a draw or the winner
        if (availablePoints.isEmpty() || depth == 0) {
            if (playerHasWon(board, 'X')) {
                bestScore = -1;
            } else if (playerHasWon(board, 'O')) {
                bestScore = 1;
            } else {
                bestScore = 0;
            }
        } else {
            //iterate over all the avaiable points for the next move
            for (Point point : availablePoints) {
                // System.out.println("POINT : " + point);
                int pointRow = point.getRow();
                int pointColumn = point.getColumn();
                //try this move for the person who is to go next
                board[pointRow][pointColumn] = currentTurn;
                //if it is the AI's turn, we want to maximize the score
                if (currentTurn == 'O') {
                    //now the opponent plays the next move
                    currentScore = minimax(board, 'X', depth - 1)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = pointRow;
                        bestColumn = pointColumn;
                    }
                } //if it is the player's turn, we want to get the minimum value (best outcome for player)
                else {
                    //now we play the next move
                    currentScore = minimax(board, 'O', depth - 1)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = pointRow;
                        bestColumn = pointColumn;
                    }
                }
                //undo the move and empty the box
                board[pointRow][pointColumn] = '\0';
            }
        }
        //System.out.println("RESULT: " + bestRow + bestColumn);
        return new int[]{bestScore, bestRow, bestColumn};
    }

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
        boolean firstRowFull = (board[0][0] == player) && (board[0][1] == player) && (board[0][2] == player);
        if (firstRowFull) {
            return true;
        }
        //check if the second row has equal elements
        boolean secondRowFull = (board[1][0] == player) && (board[1][1] == player) && (board[1][2] == player);
        if (secondRowFull) {
            return true;
        }
        //check if the third row has equal elements
        boolean thirdRowFull = (board[2][0] == player) && (board[2][1] == player) && (board[2][2] == player);
        if (thirdRowFull) {
            return true;
        }

        //check for diagonals
        //check to see if the first diagonal is full
        boolean firstDiagonalFull = (board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player);
        if (firstDiagonalFull) {
            return true;
        }
        //check to see if the second diagonal is full
        boolean secondDiagonalFull = (board[0][2] == player) && (board[1][1] == player) && (board[2][0] == player);
        if (secondDiagonalFull) {
            return true;
        }

        //check for columns now
        //check if the first column has equal elements
        boolean firstColumnFull = (board[0][0] == player) && (board[1][0] == player) && (board[2][0] == player);
        if (firstColumnFull) {
            return true;
        }
        //check if the second column has equal elements
        boolean secondColumnFull = (board[0][1] == player) && (board[1][1] == player) && (board[2][1] == player);
        if (secondColumnFull) {
            return true;
        }
        //check if the third column has equal elements
        boolean thirdColumnFull = (board[0][2] == player) && (board[1][2] == player) && (board[2][2] == player);
        //if by now, nothing has been returned, return false
        return thirdColumnFull;
    }

}
