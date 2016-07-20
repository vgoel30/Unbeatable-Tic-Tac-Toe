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
    public int minimax(char[][] board, int depth, char currentTurn) {
        Point moveToMake = new Point();
        //if the computer has won
        if (playerHasWon(board, 'O')) {
            return 1;
        }
        //if the opponent has won
        if (playerHasWon(board, 'X')) {
            return -1;
        }
        //get all the available points
        ArrayList<Point> avaialablePoints = getAvailablePoints(board);
        //if no points are available, no further recursive calls
        if(avaialablePoints.isEmpty())
            return 0;
        //these two values will be used for pruning the decision tree
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        int counter = 0;
        
        //iterate over all the available points
        for(Point point: avaialablePoints){
            int row = point.getRow();
            int column = point.getColumn();
            counter++;
            //if it's the computer's turn
            if(currentTurn == 'O'){
                //simulate a move 
                simulateMove(board, point,'O');
                //call minimax to get a new score and the opponent's next move
                int currentScore = minimax(board, depth + 1, 'X');
                //update the max score; if required
                maxScore = Math.max(maxScore, currentScore);
                //if we have reached an optimal decision
                if(currentScore >= 0 && depth == 0)
                    moveToMake = point;
                if(currentScore == 1){
                    //reset the point after move simulation
                    board[row][column] = '\0';
                    //exit the loop, no need for further searching
                    break;
                }
                if(counter == avaialablePoints.size() && depth == 0 && maxScore < 0){
                    moveToMake = point;
                }
            }
            //if it's the opponent's turn
            else{
                //simulate a move from the player's perspective
                simulateMove(board, point, 'X');
                //call minimax to update the current score with the AI's next move
                int currentScore = minimax(board, depth, 'O');
                //update the minimum score; if required
                minScore = Math.min(currentScore,minScore);
                //if we have reahced an optimal decision, no need to go further
                if(minScore == -1){
                    //reset the point 
                    board[row][column] = '\0';
                }
            }
            //reset the point
            board[row][column] = '\0';
        }
        System.out.println("MINIMAX:  " + moveToMake);
        return currentTurn == 'O' ? maxScore : minScore;
    }
    
    private void simulateMove(char[][] board, Point point, char player){
        board[point.getRow()][point.getColumn()] = player;
    }
    
    public ArrayList<Point> getAvailablePoints(char[][] board){
        int i,j;
        //the list of all the avaiable points on the grid
        ArrayList<Point> avaialablePoints = new ArrayList<>();
        //iterate over the 2D array to find all the empty squares
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++){
                if(board[i][j] == '\0'){
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
