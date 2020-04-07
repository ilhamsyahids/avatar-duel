package com.avatarduel;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class KartuUI extends Parent {
    public int fieldCol;
    public int deckCol;
    public ImageView mv;

    public KartuUI(ImageView mv, int fieldCol, int deckCol){
        this.fieldCol = fieldCol;
        this.deckCol = deckCol;
        this.mv = mv;
        kartuIni.setOnMouseClicked(event -> {
            summonCard(kartuIni);

        });
        getChildren().addAll(this.mv);
    }


}


