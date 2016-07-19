/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author varungoel
 */
public class ViewController {
    
    public void attachEventHandlers(ArrayList<Pane> boxes) {
        for (Pane rowBox : boxes) {
            String cssString = rowBox.getStyle();
            //mouse enter event
            rowBox.setOnMouseEntered(e -> {
                if (rowBox.getChildren().isEmpty()) {
                    rowBox.setStyle(cssString + "-fx-background-color: #19B5FE;");
                    //Color.
                }
            });
            //mouse exit event
            rowBox.setOnMouseExited(e -> {
                rowBox.setStyle(cssString);
            });
            //here the call to minimax will take place
            rowBox.setOnMouseClicked(e -> {
                //the box must be empty
                if (rowBox.getChildren().isEmpty()) {
                    System.out.println(rowBox.getId());
                    placeX(rowBox, cssString);
                }
            });
        }
    }

    /**
     * Places the 'X' in the box clicked by the user
     * @param rowBox is the box clicked by the user
     * @param cssString is the default CSS string being used for the boxes of the grid
     */
    public void placeX(Pane rowBox, String cssString) {
        ImageView imageView = new ImageView("X.png");
        imageView.setTranslateX(20);
        imageView.setTranslateY(20);
        rowBox.getChildren().add(imageView);
        rowBox.setStyle(cssString);
    }

}
