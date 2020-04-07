package com.avatarduel;

import com.avatarduel.model.Card;
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

import com.avatarduel.ArenaController;

public class KartuUI extends Parent {
    public int fieldCol;
    public int deckCol;
    public Card card;

    public KartuUI(Card card){
        this.fieldCol = fieldCol;
        this.deckCol = deckCol;
        this.card = card;
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        ImageView imgv = new ImageView(img);
        getChildren().addAll(imgv);
    }

    public Card getCard() {
        return card;
    }


}


