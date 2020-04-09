package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.Mode;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Skill;
import com.avatarduel.model.Phase.Fase;
import com.avatarduel.model.Character;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public KartuUI(Card card, Fase fase){
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

        if (fase == Phase.Fase.MAIN) {
            this.imageView.setOnMouseClicked(el -> {
                this.summon.setOnMouseClicked(e -> {
                    // implementasi summon
                    GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                    Phase.arenaController.render();
                    // renderCardMain();
                });
                this.set.setOnMouseClicked(e -> {
                    // implementasi set
                    this.getCard().setMode(Mode.DEFENSE);
                    GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                    Phase.arenaController.render();
                    // renderCardMain();
                });
    
                this.activate.setOnMouseClicked(e ->{
                    GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                    System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                    Phase.arenaController.render();
                    // renderCardMain();
                    // tambahin kodingan efek dari kartunya disini..
    
                });
    
                if(this.isClicked){
                    this.HandDialog.getChildren().clear();
                    this.root.getChildren().clear();
                    this.root.getChildren().addAll(this.imageView);
                    this.isClicked = false;
                }
                else{
                    // tampilin menu tergantung state dari kartuUInya
                    if(getCard() instanceof Character) { // tampilin pilihan set kalau mmg dia karakter
                        this.HandDialog.getChildren().addAll(this.summon,this.set);
                    }
                    else { //bukan karakter
                        this.HandDialog.getChildren().addAll(this.activate);
                    }
                    this.root.getChildren().clear();
                    this.root.getChildren().addAll(this.HandDialog,this.imageView);
                    this.isClicked = true;
                }
            });
        } else if (fase == Phase.Fase.DRAW) {
            // this.setOnMouseClicked(el -> {
            //     // 3. meletakkan MAKS. 1 kartu land
            //     if (this.getCard() instanceof Land) {
            //         if (GameState.getInstance().getCurrentPlayer().getTakeLand()) {
            //             GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
            //             GameState.getInstance().getCurrentPlayer().setTakeLand(false);
            //         }
            //     } else if (((this.getCard() instanceof Character)
            //             && (GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size() < 8))
            //             || ((this.getCard() instanceof Skill)
            //                     && (GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size() < 8))) {
            //         GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
            //     }
            //     System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
            //     System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
            //    // Phase.arenaController.render();
            //     // renderCardMain();
            // });
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Card getCard() {
        return card;
    }


}


