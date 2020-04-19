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
    private Card card;
    private ImageView imageView;
    private boolean isClicked = false;
    private VBox HandDialog;
    private VBox root;
    private Button summon;
    private Button set;
    private Button destroy;
    private Button destroyInHand;
    private Button attack;
    private Button changePosition;
    private Button isAttacked;
    private Button activate;
    private Button activateOnThis;
    private static Skill skillOnAction; // ini buat nyimpen kartu yg lagi ngegunain skillnya saat ini
    private static boolean isSkillActive = false; // bernilai True jika ada kartu skill yang sedang aktif, akan bernilai
                                                  // False jika gk ada kartu skill yg sedang aktif
    private static boolean thereIsCardAttack = false; // bernilai true jika ada kartu yang sedang menyerang
    private static Character cardAttack;

    /**
     * Constructor
     * 
     * @param card card that is inserted in this cardUI other atributes are set to
     *             their default value
     */
    CardUI(Card card) {
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

    /**
     *
     * @return cardAttack (cardAttack stores last card that declares attack)
     */
    static Character getCardAttack() {
        return cardAttack;
    }

    /**
     *
     * @return thereIsCardAttack
     */
    static boolean getThereIsCardAttack() {
        return thereIsCardAttack;
    }

    /**
     * Set boolean thereIsCardAttack which means if its value is true indicate that
     * there a card that declares attack
     * 
     * @param val value
     */
    public static void setThereIsCardAttack(boolean val) {
        thereIsCardAttack = val;
    }

    /**
     *
     * @return imageView (images of the card)
     */
    ImageView getImageView() {
        return imageView;
    }

    /**
     *
     * @return card (card that is contained in this CardUI)
     */
    public Card getCard() {
        return card;
    }

    /**
     * Reset static to default value
     */
    public static void resetCardUI() {
        isSkillActive = false;
        thereIsCardAttack = false;
    }

    /**
     * Empty the dialog of card which means no action is available for this card
     */
    void emptyCardDialog() {
        // membuat kartu tidak menampilkan pilihan dialog apapun
        this.HandDialog.getChildren().clear();
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.imageView);
        this.isClicked = false;
    }

    /**
     * Set the dialog of card that is in Hand with available action which is
     * destory, summon, set if the card is Character and activate if the card is
     * Land or Skill
     */
    void setHandDialog() {
        Player myPlayer = GameState.getInstance().getCurrentPlayer();
        Player enemyPlayer = GameState.getInstance().getOtherPlayer();

        this.destroyInHand.setOnMouseClicked(e -> {
            myPlayer.getDeck().getHandCards().remove(getCard());
            Phase.arenaController.render();
        });
        this.summon.setOnMouseClicked(e -> {
            // implementasi summon
            summonCard((Powerable) this.getCard(), myPlayer);
        });
        this.set.setOnMouseClicked(e -> {
            // implementasi set
            ((Character) this.getCard()).setMode(Mode.DEFENSE);
            summonCard((Powerable) this.getCard(), myPlayer);
        });

        this.activate.setOnMouseClicked(e -> {
            if (!(getCard() instanceof Land)) {
                Powerable powerCard = (Powerable) this.getCard();
                if (powerCard.isCanSummon()) {
                    if ((enemyPlayer.getDeck().getCharacters().size() != 0)
                            || (myPlayer.getDeck().getCharacters().size() != 0)) {
                        Phase.arenaController.setGameMessage("Klik Target Kartu");
                        isSkillActive = true;
                        skillOnAction = (Skill) this.card;
                    } else {
                        Phase.arenaController.setGameMessage("Tidak ada pilihan kartu");
                    }
                } else {
                    Phase.arenaController.setGameMessage("Power tidak cukup!");
                }
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

    /**
     * Summon Card to player
     * 
     * @param powerCard the card
     * @param myPlayer  the player
     */
    private void summonCard(Powerable powerCard, Player myPlayer) {
        if (powerCard.isCanSummon()) {
            try {
                myPlayer.getDeck().moveToArea(this.getCard());
            } catch (Exception e1) {
                Phase.arenaController.setGameMessage(e1.getMessage());
            }
            myPlayer.reducePower(getCard().getElement(), powerCard.getPower());
        } else {
            Phase.arenaController.setGameMessage("Power tidak cukup!");
        }
        Phase.arenaController.render();
    }

    /**
     * Set the dialog for card that is in enemy's field action attack this if there
     * is our card in field that declares attack
     */
    void setCharacterDialogAttackedInField() {
        // kotak dialog ini adalah kotak dialog yang akan ditampilkan
        this.isAttacked.setOnMouseClicked(e -> {
            // implementasi kalau kartu ini diserang
            // Set nilai powerAttacked berdasarkan mode dari kartu yang diserang
            cardAttack.action((Character) this.card);
            thereIsCardAttack = false;
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

    /**
     * Set the dialog for cards in field in Main Phase
     * 
     * @param isSelf indicates whether the cards in field belong to current player
     *               or enemy
     */
    void setCharacterDialogInFieldMainPhase(boolean isSelf) {
        this.changePosition.setOnMouseClicked(e -> {
            // implementasi changePosition
            Character cardTemp = (Character) this.getCard();
            if (cardTemp.getMode() == Mode.DEFENSE) {
                this.imageView.setRotate(0);
                cardTemp.setMode(Mode.ATTACK); // set mode ke attack
            } else { // awalnya attack
                this.imageView.setRotate(90);
                cardTemp.setMode(Mode.DEFENSE); // set mode ke defense
            }
            this.root.getChildren().clear();
            this.root.getChildren().addAll(this.HandDialog, this.imageView);
            this.isClicked = true;
        });
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
            Player myPlayer = GameState.getInstance().getCurrentPlayer();
            Player enemyPlayer = GameState.getInstance().getOtherPlayer();
            try {
                if (skillOnAction instanceof SkillDestroy) {
                    // remove skill
                    ((Character) getCard()).getCharSkills().forEach(item -> {
                        if (!myPlayer.getDeck().getSkills().remove(item)) {
                            enemyPlayer.getDeck().getSkills().remove(item);
                        }
                    });
                    // remove character
                    if (!myPlayer.getDeck().getCharacters().remove(getCard())) {
                        enemyPlayer.getDeck().getCharacters().remove(getCard());
                    }
                    myPlayer.getDeck().getHandCards().remove(skillOnAction);
                } else {
                    Character cardChar = (Character) getCard();
                    skillOnAction.action(cardChar);
                    myPlayer.getDeck().moveToArea(skillOnAction);
                }
                myPlayer.reducePower(skillOnAction.getElement(), skillOnAction.getPower());
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
                            this.HandDialog.getChildren().addAll(this.destroy, this.changePosition);
                        } else {
                            this.emptyCardDialog();
                        }
                    } else {
                        if (isSelf) {
                            this.HandDialog.getChildren().addAll(this.destroy, this.changePosition, this.activateOnThis);
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

    /**
     * Set the dialog for cards in field in Battle Phase
     */
    void setCharacterDialogInFieldBattlePhase() {
        this.attack.setOnMouseClicked(e -> {
            // implementasikan attack
            thereIsCardAttack = true;
            cardAttack = (Character) this.card;
            Phase.arenaController.render();
            // bikin button directAttack visible jika enemyPlayer tidak punya karakter di
            // field
            if (GameState.getInstance().getOtherPlayer().getDeck().getCharacters().size() == 0) {
                Phase.arenaController.getDirectAttack().setVisible(true);
            }
            Phase.arenaController.setGameMessage("Klik target serangan");
        });

        this.imageView.setOnMouseClicked(el -> {
            if (this.isClicked) {
                this.emptyCardDialog();
            } else {
                // cek mode dari kartu
                Character karakter = (Character) this.getCard();
                if (karakter.getMode() == Mode.ATTACK && !karakter.isAttackThisTurn()) {
                    this.HandDialog.getChildren().addAll(this.attack);
                } else {
                    this.HandDialog.getChildren().clear();
                }
                this.root.getChildren().clear();
                this.root.getChildren().addAll(this.HandDialog, this.imageView);
                this.isClicked = true;
            }
        });
    }

}
