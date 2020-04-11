package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Mode;
import com.avatarduel.model.Phase;
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
    public Button isAttacked;
    public Button activate;
    private static int powerAttack = 9999; // bernilai 9999 jika tidak ada monster di field yang mendeclare attack
    private static int powerAttacked = 9999; // bernilai 9999 jika monster yang diserang belum dipilih

    public KartuUI(Card card, Fase fase) {
        HandDialog = new VBox();
        HandDialog.setSpacing(5);
        summon = new Button("summon");
        set = new Button("set");
        attack = new Button("attack");
        changePosition = new Button("changePosition");
        activate = new Button("activate");
        isAttacked = new Button("attack this");
        HandDialog.getChildren().addAll();
        this.card = card;
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        imageView = new ImageView(img);
        root = new VBox(imageView);
        root.setSpacing(2);
        getChildren().addAll(root);
    }

    public static void setPowerAttack(int pwr) {
        powerAttack = pwr;
    }

    public static void setPowerAttacked(int pwr) {
        powerAttacked = pwr;
    }

    public static void setPowerAttack(KartuUI kartuUI) {
        Character temp = (Character) kartuUI.card;
        powerAttack = temp.getAttack();
    }

    public static void setPowerAttacked(KartuUI kartuUI) {
        Character temp = (Character) kartuUI.card;
        if (temp.getMode() == Mode.ATTACK) {
            powerAttacked = temp.getAttack();
        } else {
            powerAttacked = temp.getDefense();
        }
    }

    public static int getPowerAttack() {
        return powerAttack;
    }

    public static int getPowerAttacked() {
        return powerAttacked;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Card getCard() {
        return card;
    }

    public void emptyCardDialog() {
        // membuat kartu tidak menampilkan pilihan dialog apapun
        this.HandDialog.getChildren().clear();
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.imageView);
        this.isClicked = false;
    }

    public void setHandDialog() {
        this.summon.setOnMouseClicked(e -> {
            // implementasi summon
            GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
            Phase.arenaController.render();
        });
        this.set.setOnMouseClicked(e -> {
            // implementasi set
            this.getCard().setMode(Mode.DEFENSE);
            GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
            Phase.arenaController.render();
        });

        this.activate.setOnMouseClicked(e -> {
            GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(this.getCard());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
            Phase.arenaController.render();
            // tambahin kodingan efek dari kartunya disini..
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                // tampilin menu tergantung state dari kartuUInya
                if (getCard() instanceof Character) { // tampilin pilihan set kalau mmg dia karakter
                    this.HandDialog.getChildren().addAll(this.summon, this.set);
                } else { // bukan karakter
                    this.HandDialog.getChildren().addAll(this.activate);
                }
                this.root.getChildren().clear();
                this.root.getChildren().addAll(this.HandDialog, this.imageView);
                this.isClicked = true;
            }
        });
    }

    public void setCharacterDialogAttackedInField() {
        // kotak dialog ini adalah kotak dialog yang akan ditampilkan
        this.isAttacked.setOnMouseClicked(e -> {
            // implementasi kalau kartu ini diserang
            System.out.println("Aku diserang");
            setPowerAttacked(this);
            System.out.println("Power attack " + powerAttack);
            System.out.println("Power yang diattack " + powerAttacked);
            if (powerAttack > powerAttacked) {
                System.out.println("Serangan berhasil");
                GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(this.card);
                Phase.arenaController.setGameMessage("Serangan berhasil");
            } else {
                System.out.println("Serangan gagal");
                Phase.arenaController.setGameMessage("Serangan gagal");
            }
            powerAttack = 9999; // kembalikan ke default value
            powerAttacked = 9999; // kembalikan ke default value
            Phase.arenaController.render();

        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                this.HandDialog.getChildren().addAll(this.isAttacked);
                this.root.getChildren().clear();
                this.root.getChildren().addAll(this.HandDialog, this.imageView);
                this.isClicked = true;
            }
        });

    }

    public void setCharacterDialogInField() {
        this.attack.setOnMouseClicked(e -> {
            // implementasikan attack
            System.out.println("Aku nyerang"); // ini dummy aja
            setPowerAttack(this);
            System.out.println(powerAttack);
            Phase.arenaController.render();
            Phase.arenaController.setGameMessage("Klik target serangan");
            // this.attack.setVisible(false);

        });
        this.changePosition.setOnMouseClicked(e -> {
            // implementasi changePosition
            if (this.getCard().getMode() == Mode.DEFENSE) {
                this.imageView.setRotate(0);
                this.getCard().setMode(Mode.ATTACK); // set mode ke attack
            } else { // awalnya attack
                this.imageView.setRotate(90);
                this.getCard().setMode(Mode.DEFENSE); // set mode ke defense
            }
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                // cek mode dari kartu
                if (this.getCard().getMode() == Mode.ATTACK) {
                    this.HandDialog.getChildren().addAll(this.attack, this.changePosition);
                } else {
                    this.HandDialog.getChildren().addAll(this.changePosition);
                }
                this.root.getChildren().clear();
                this.root.getChildren().addAll(this.HandDialog, this.imageView);
                this.isClicked = true;
            }
        });
    }

}
