package com.avatarduel.view;

import com.avatarduel.model.card.*;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;

import com.avatarduel.model.card.Character;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

import java.io.File;

public class CardUI extends Parent {
    public int fieldCol;
    public int deckCol;
    public Card card;
    public ImageView imageView;
    public boolean isClicked = false;
    public VBox HandDialog;
    public VBox root;
    public Button summon;
    public Button set;
    public Button destroy;
    public Button destroyInHand;
    public Button attack;
    public Button changePosition;
    public Button isAttacked;
    public Button activate;
    public Button activateOnThis;
    private static Skill skillOnAction; // ini buat nyimpen kartu yg lagi ngegunain skillnya saat ini
    private static boolean isSkillActive = false; // bernilai True jika ada kartu skill yang sedang aktif, akan bernilai
                                                  // False jika gk ada kartu skill yg sedang aktif
    private static int powerAttack = 9999; // bernilai 9999 jika tidak ada monster di field yang mendeclare attack
    private static int powerAttacked = 9999; // bernilai 9999 jika monster yang diserang belum dipilih
    private static Character cardAttack;

    public CardUI(Card card) {
        HandDialog = new VBox();
        HandDialog.setSpacing(5);
        summon = new Button("summon");
        set = new Button("set");
        attack = new Button("attack");
        activateOnThis = new Button("activate on this");
        changePosition = new Button("changePosition");
        activate = new Button("activate");
        isAttacked = new Button("attack this");
        destroy = new Button("destroy");
        destroyInHand = new Button("destroy");
        HandDialog.getChildren().addAll();
        this.card = card;
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        imageView = new ImageView(img);
        root = new VBox(imageView);
        root.setSpacing(2);
        getChildren().addAll(root);
    }

    public static Character getCardAttack() {
        return cardAttack;
    }

    public static void setPowerAttack(int pwr) {
        powerAttack = pwr;
    }

    public static void setPowerAttacked(int pwr) {
        powerAttacked = pwr;
    }

    public static void setPowerAttack(CardUI cardUI) {
        Character temp = (Character) cardUI.card;
        powerAttack = temp.getAttack();
        cardAttack = temp;
    }

    public static void setPowerAttacked(CardUI cardUI) {
        Character temp = (Character) cardUI.card;
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
        Player myPlayer = GameState.getInstance().getCurrentPlayer();

        this.destroyInHand.setOnMouseClicked(e -> {
            myPlayer.getDeck().getHandCards().remove(getCard());
            Phase.arenaController.render();
        });
        this.summon.setOnMouseClicked(e -> {
            // implementasi summon
            // Powerable powerCard = (Powerable) this.getCard(); // Comment this for game
            // test
            // if (powerCard.isCanSummon()) { // Comment this for game test
            try {
                myPlayer.getDeck().moveToArea(this.getCard());
            } catch (Exception e1) {
                Phase.arenaController.setGameMessage(e1.getMessage());
            }
            // myPlayer.reducePower(getCard().getElement(), powerCard.getPower()); //
            // Comment this for game test
            // } else { // Comment this for game test
            // Phase.arenaController.setGameMessage("Power tidak cukup!"); // Comment this
            // for game test
            // } // Comment this for game test
            Phase.arenaController.render();
        });
        this.set.setOnMouseClicked(e -> {
            // implementasi set
            ((Character) this.getCard()).setMode(Mode.DEFENSE);
            // Powerable powerCard = (Powerable) this.getCard(); // Comment this for game
            // test
            // if (powerCard.isCanSummon()) { // Comment this for game test
            try {
                myPlayer.getDeck().moveToArea(this.getCard());
            } catch (Exception e1) {
                Phase.arenaController.setGameMessage(e1.getMessage());
            }
            // myPlayer.reducePower(getCard().getElement(), powerCard.getPower()); //
            // Comment this for game test
            // } else { // Comment this for game test
            // Phase.arenaController.setGameMessage("Power tidak cukup!"); // Comment this
            // for game test
            // } // Comment this for game test
            Phase.arenaController.render();
        });

        this.activate.setOnMouseClicked(e -> {
            if (!(getCard() instanceof Land)) {
                // Powerable powerCard = (Powerable) this.getCard(); // Comment this for game
                // test
                // if (powerCard.isCanSummon()) { // Comment this for game test
                if ((GameState.getInstance().getOtherPlayer().getDeck().getCharacters().size() != 0)
                        || (GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size() != 0)) {
                    Phase.arenaController.setGameMessage("Klik Target Kartu");
                    isSkillActive = true;
                    skillOnAction = (Skill) this.card;
                } else {
                    Phase.arenaController.setGameMessage("Tidak ada pilihan kartu");
                }
                // myPlayer.reducePower(getCard().getElement(), powerCard.getPower()); //
                // Comment this for game test
                // } else { // Comment this for game test
                // Phase.arenaController.setGameMessage("Power tidak cukup!"); // Comment this
                // for game test
                // } // Comment this for game test
            } else {
                if (myPlayer.isCanTakeLand()) {
                    try {
                        myPlayer.getDeck().moveToArea(this.getCard());
                        myPlayer.setTakeLand(false);
                    } catch (Exception e1) {
                        Phase.arenaController.setGameMessage(e1.getMessage());
                    }
                } else {
                    Phase.arenaController.setGameMessage("Sekali ajaaa");
                }

            }
            Phase.arenaController.render();
            // tambahin kodingan efek dari kartunya disini..
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                // tampilin menu tergantung state dari cardUInya
                if (getCard() instanceof Character) { // tampilin pilihan set kalau mmg dia karakter
                    this.HandDialog.getChildren().addAll(this.summon, this.set, this.destroyInHand);
                } else { // bukan karakter
                    this.HandDialog.getChildren().addAll(this.activate, this.destroyInHand);
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
            // Set nilai powerAttacked berdasarkan mode dari kartu yang diserang
            cardAttack.action((Character) this.card);
            powerAttack = 9999; // kembalikan ke default value
            powerAttacked = 9999; // kembalikan ke default value
            Phase.arenaController.getDirectAttack().setVisible(false);
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

    public void setCharacterDialogInFieldMainPhase(boolean isSelf) {
        this.destroy.setOnMouseClicked(e -> {
            if (getCard() instanceof Character) {
                // remove skill
                ((Character) getCard()).removeSkills();
                // remove character
                GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().remove(getCard());
            } else if (getCard() instanceof Skill) {
                for (Character character : GameState.getInstance().getCurrentPlayer().getDeck().getCharacters()) {
                    // ngecek field kita
                    if (character.getCharSkills().remove((Skill) getCard())) {
                        // remove skill
                        break;
                    }
                }
                for (Character character : GameState.getInstance().getOtherPlayer().getDeck().getCharacters()) {
                    // ngecek field lawan
                    if (character.getCharSkills().remove((Skill) getCard())) {
                        // remove skill
                        break;
                    }
                }
                GameState.getInstance().getCurrentPlayer().getDeck().getSkills().remove(getCard());
            }

            Phase.arenaController.render();
        });

        this.activateOnThis.setOnMouseClicked(e -> {
            // masukin fungsi" skill disini
            try {
                if (skillOnAction instanceof SkillDestroy) {
                    // remove skill
                    ((Character) getCard()).getCharSkills().forEach(item -> {
                        if (!GameState.getInstance().getCurrentPlayer().getDeck().getSkills().remove(item)) {
                            GameState.getInstance().getOtherPlayer().getDeck().getSkills().remove(item);
                        }
                    });
                    // remove character
                    if (!GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().remove(getCard())) {
                        GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(getCard());
                    }
                    GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().remove(skillOnAction);
                } else {
                    Character cardChar = (Character) getCard();
                    skillOnAction.action(cardChar);
                    GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(skillOnAction);
                }
            } catch (Exception e1) {
                Phase.arenaController.setGameMessage(e1.getMessage());
            }
            isSkillActive = false;
            Phase.arenaController.render();
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                if (this.card instanceof Skill) {
                    if (isSelf) {
                        this.HandDialog.getChildren().addAll(this.destroy);
                    } else {
                        this.emptyCardDialog();
                    }
                } else {
                    if (!isSkillActive) {
                        if (isSelf) {
                            this.HandDialog.getChildren().addAll(this.destroy);
                        } else {
                            this.emptyCardDialog();
                        }
                    } else {
                        if (isSelf) {
                            this.HandDialog.getChildren().addAll(this.destroy, this.activateOnThis);
                        } else {
                            this.HandDialog.getChildren().addAll(this.activateOnThis);
                        }
                    }
                }

                this.root.getChildren().clear();
                this.root.getChildren().addAll(this.HandDialog, this.imageView);
                this.isClicked = true;
            }
        });
    }

    public void setCharacterDialogInFieldBattlePhase() {
        this.attack.setOnMouseClicked(e -> {
            // implementasikan attack
            setPowerAttack(this);
            cardAttack = (Character) this.card;
            Phase.arenaController.render();
            // bikin button directAttack visible jika enemyPlayer tidak punya karakter di
            // field
            if (GameState.getInstance().getOtherPlayer().getDeck().getCharacters().size() == 0) {
                Phase.arenaController.getDirectAttack().setVisible(true);
            }
            Phase.arenaController.setGameMessage("Klik target serangan");
        });
        this.changePosition.setOnMouseClicked(e -> {
            // implementasi changePosition
            Character cardTemp = (Character) this.getCard();
            if (cardTemp.getMode() == Mode.DEFENSE) {
                this.imageView.setRotate(0);
                cardTemp.setMode(Mode.ATTACK); // set mode ke attack
                if (!cardTemp.isAttackThisTurn())
                    this.HandDialog.getChildren().add(this.attack);
            } else { // awalnya attack
                this.imageView.setRotate(90);
                cardTemp.setMode(Mode.DEFENSE); // set mode ke defense
                this.HandDialog.getChildren().remove(this.attack);
            }
            this.root.getChildren().clear();
            this.root.getChildren().addAll(this.HandDialog, this.imageView);
            this.isClicked = true;
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                // cek mode dari kartu
                Character karakter = (Character) this.getCard();
                if (karakter.getMode() == Mode.ATTACK && karakter.isAttackThisTurn() == false) {
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
