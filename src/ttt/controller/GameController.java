/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ttt.gui.GameBoard;

/**
 *
 * @author varungoel
 */
public class GameController extends Application{
    
    static GameBoard gameBoard;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        getBoardReady();

        Scene scene = new Scene(gameBoard.getBoard(), 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);

        primaryStage.show();

    }
    
    public void getBoardReady(){
        gameBoard = new GameBoard();
        gameBoard.layoutGUI();
    }
    
}