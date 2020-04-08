package com.avatarduel;

import com.avatarduel.model.Card;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import java.io.File;

public class KartuUI extends Parent {
    public int fieldCol;
    public int deckCol;
    public Card card;
    public ImageView imageView;
    public boolean isClicked = false;
    public VBox HandDialog;
    public VBox root;
    public Button summon;
    public Button set;
    public Button attack;
    public Button changePosition;
    public Button activate;

    public KartuUI(Card card){
        HandDialog = new VBox();
        HandDialog.setSpacing(5);
        summon = new Button("summon");
        set = new Button("set");
        attack = new Button("attack");
        changePosition = new Button("changePosition");
        activate = new Button("activate");
        HandDialog.getChildren().addAll();
        this.card = card;
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        imageView = new ImageView(img);
        root = new VBox(imageView);
        root.setSpacing(2);
        getChildren().addAll(root);

    }

    public ImageView getImageView() {
        return imageView;
    }

    public Card getCard() {
        return card;
    }


}


