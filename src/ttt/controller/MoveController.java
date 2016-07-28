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

    final static char MY_MOVE = 'O';
    final static char OPPONENT_MOVE = 'X';
    
    /**
     * Minimax algorithm will find the best move for the AI to make in order to never lose
     * @param board is the tic-tac-toe board
     * @param currentTurn is the player who has to go 
     * @param depth is the depth of the decision tree
     * @return an integer array with 3 elements: the best score, the optimal row, the optimal column
     */
    int[] minimax(char[][] board, char currentTurn, int depth) {
        //get all the points that are empty
        ArrayList<Point> availablePoints = getAvailablePoints(board);
        //we want to get the maximum value score for (MY_MOVE): the AI
        int bestScore = (currentTurn == MY_MOVE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        //the row and column that will lead to the best outcome
        int bestRow = -1;
        int bestColumn = -1;
        //if no more moves are possible, check for a draw or the winner
        if(availablePoints.isEmpty() || depth == 0){
            bestScore = evaluate(board);
        }
        else{
            for(Point currentPoint: availablePoints){
                int row = currentPoint.getRow();
                int column = currentPoint.getColumn();
                //play the move in the row-column with the current player
                board[row][column] = currentTurn;
                if(currentTurn == MY_MOVE){
                    //play from the player's side
                    currentScore = minimax(board, OPPONENT_MOVE, depth-1)[0];
                    if(currentScore > bestScore){
                        bestScore = currentScore;
                        bestRow = row;
                        bestColumn = column;
                    }
                }
                else if(currentTurn == OPPONENT_MOVE){
                    //play from the player's side
                    currentScore = minimax(board, OPPONENT_MOVE, depth-1)[0];
                    if(currentScore < bestScore){
                        bestScore = currentScore;
                        bestRow = row;
                        bestColumn = column;
                    }
                }
                //undo the move
                board[row][column] = '\0';
            }
        }
        
        return new int[]{bestScore, bestRow, bestColumn};
    }

    /**
     * Gets a list of all available points on the board
     * @param board is the tic tac toe board
     * @return a list of points currently available
     */
    private ArrayList<Point> getAvailablePoints(char[][] board) {
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
     * Evaluates the board for various configrations
     * @param board
     * @return the evaluated score
     */
    private int evaluate(char[][] board) {
        int score = 0;

        //evaluate scores for the three rows
        score += evaluateLine(0, 0, 0, 1, 0, 2, board);
        score += evaluateLine(1, 0, 1, 1, 1, 2, board);
        score += evaluateLine(2, 0, 2, 1, 2, 2, board);

        //evaluate scores for the two diagonals
        score += evaluateLine(0, 0, 1, 1, 2, 2, board);
        score += evaluateLine(0, 2, 1, 1, 2, 0, board);

        //evaluate scores for the three columns
        score += evaluateLine(0, 0, 1, 0, 2, 0, board);
        score += evaluateLine(0, 1, 1, 1, 2, 1, board);
        score += evaluateLine(0, 2, 1, 2, 2, 2, board);

        return score;
    }

    /**
     * Evaluates a line with given coordinates
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param row3
     * @param col3
     * @param board
     * @return 
     */
    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3, char[][] board) {
        int score = 0;

        //the first box
        score = (board[row1][col1] == MY_MOVE) ? 1 : -1;

        //second box
        if (board[row2][col2] == MY_MOVE) {
            //the previous box was mine
            if (score == 1) {
                score = 10;
            } //the opponent had the previous box
            else if (score == -1) {
                return 0;
            } else {
                score = -1;
            }
        } else if (board[row2][col2] == OPPONENT_MOVE) {
            if (score == -1) {
                score = -10;
            } else if (score == 1) {
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        //third box
        if (board[row3][col3] == MY_MOVE) {
            if (score > 0) {
                score *= 10;
            } else if (score < 0) {
                return 0;
            } else {
                score = 1;
            }
        } else if (board[row3][col3] == OPPONENT_MOVE) {
            if (score < 0) {
                score *= 10;
            } else if (score > 0) {
                return 0;
            } else {
                score = -1;
            }
        }

        return score;
    }

    /**
     * Checks to see if a player has won
     *
     * @param board is the current state of the tic-tac-toe board
     * @param player is the player for who went to check (OPPONENT_MOVE or
     * MY_MOVE)
     * @return if a particular player has won the game
     */
    boolean playerHasWon(char[][] board, char player) {

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

    /**
     * Helper method that sees if the board is full
     * @param board
     * @return 
     */
    private boolean boardIsFull(char[][] board){
        int i,j = 0;
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++){
                if(board[i][j] == '\0')
                    return false;
            }
        }
        return true;
    }
     
    /**
     * Method to check whether a game with the given board is over 
     * @param board
     * @return 
     */
    boolean gameIsOver(char[][] board){
        return playerHasWon(board, MY_MOVE) || boardIsFull(board)|| playerHasWon(board, OPPONENT_MOVE);
    }
    
}
