package com.avatarduel;

import com.avatarduel.model.Card;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class KartuUI extends Parent {
    public int fieldCol;
    public int deckCol;
    public Card card;

    public KartuUI(Card card){
        this.card = card;
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        ImageView imgv = new ImageView(img);
        getChildren().addAll(imgv);
    }

    public Card getCard() {
        return card;
    }


}


