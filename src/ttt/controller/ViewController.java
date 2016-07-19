/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author varungoel
 */
public class ViewController {

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
