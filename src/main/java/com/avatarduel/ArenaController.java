package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.model.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.avatarduel.model.Mode;
import com.avatarduel.model.Deck;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.Skill;
import com.avatarduel.model.SkillAura;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ArenaController implements Initializable {

    @FXML
    private Pane utama;
    @FXML
    private Label enemyHP;
    @FXML
    private Label myHP;
    @FXML
    private GridPane enemyField;
    @FXML
    private GridPane myField;
    @FXML
    private HBox otherHand;
    @FXML
    private HBox enemySkillArea;
    @FXML
    private HBox enemyCharArea;
    @FXML
    private HBox mySkillArea;
    @FXML
    private HBox myCharArea;
    @FXML
    private HBox myHand;
    @FXML
    private ProgressBar enemyBar;
    @FXML
    private ProgressBar myBar;
    @FXML
    private Label playerTwo;
    @FXML
    private Label playerOne;
    @FXML
    private Pane fillMyCard;
    @FXML
    private Pane fillEnemyCard;
    @FXML
    private Pane paneHover;
    @FXML
    private Pane imageHover;
    @FXML
    private Label nameHover;
    @FXML
    private Label elementHover;
    @FXML
    private Label attackHover;
    @FXML
    private Label defenceHover;
    @FXML
    private Label powerHover;
    @FXML
    private Label myCountCard;
    @FXML
    private Label enemyCountCard;
    @FXML
    private Label descriptionHover;
    @FXML
    private Label typeClass;
    @FXML
    private Button endPhase;
    @FXML
    private Rectangle rectElemen;
    @FXML
    private Rectangle colorCard;
    @FXML
    private Rectangle changePhase;
    @FXML
    private Label draw;
    @FXML
    private Label main1;
    @FXML
    private Label battle;
    @FXML
    private Label end;
    @FXML
    private Label main2;
    @FXML
    private Label thoseFire;
    @FXML
    private Label thoseWater;
    @FXML
    private Label thoseAir;
    @FXML
    private Label thoseEarth;
    @FXML
    private Label thisEarth;
    @FXML
    private Label thisAir;
    @FXML
    private Label thisWater;
    @FXML
    private Label thisFire;


    private static final String HOVERED_CARD_STYLE = "-fx-opacity: 0.5;";
    private static final String IDLE_CARD_STYLE = "-fx-opacity: 1;";
    private static final String REMOVE_CARD_STYLE = "-fx-opacity: 0;";

    public Rectangle changePhase(double position) {
        this.changePhase.setLayoutY(position);
        return this.changePhase;
    }

    public Label getDrawTextLabel() {
        return this.draw;
    }

    public Label getMain1TextLabel() {
        return this.main1;
    }

    public Label getBattleTextLabel() {
        return this.battle;
    }

    public Label getMain2TextLabel() {
        return this.main2;
    }

    public Label getEndTextLabel() {
        return this.end;
    }

    public Button getButtonPhase() {
        return endPhase;
    }

    public void initialize(URL url, ResourceBundle rb) {
        setBackground("file:background/arena.JPG");
        renderCountCard();
    }

    public void setBackground(String pict) {
        // Set background pada this scene
        Image image = new Image(pict);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, false, false, false, true));

        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }

    public void renderCountCard() {
        Image card = new Image(new File("background/flip.PNG").toURI().toString(), 93, 68, false, false);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }

    private void renderEnemyHand() {
        otherHand.getChildren().clear();
        GameState.getInstance().getOtherPlayer().getDeck().getHandCards().forEach(item -> {
            Image img = new Image(new File("background/flip.PNG").toURI().toString(), 70, 72, false, false);
            ImageView imageView = new ImageView(img);
            otherHand.getChildren().add(imageView);
        });
    }

    public void setCardsOnHandsDialog(KartuUI cardUI){
        cardUI.imageView.setOnMouseClicked(el -> {
            cardUI.summon.setOnMouseClicked(e -> {
                // implementasi summon
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                renderCardMain();

            });
            cardUI.set.setOnMouseClicked(e -> {
                // implementasi set
                cardUI.getCard().setMode(Mode.DEFENSE);
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                renderCardMain();
            });

            cardUI.activate.setOnMouseClicked(e ->{
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                renderCardMain();
                // tambahin kodingan efek dari kartunya disini..

            });

            if(cardUI.isClicked){
                cardUI.HandDialog.getChildren().clear();
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.imageView);
                cardUI.isClicked = false;
            }
            else{
                // tampilin menu tergantung state dari kartuUInya
                if(cardUI.getCard() instanceof Character){ // tampilin pilihan set kalau mmg dia karakter
                    cardUI.HandDialog.getChildren().addAll(cardUI.summon,cardUI.set);
                }
                else { //bukan karakter
                    cardUI.HandDialog.getChildren().addAll(cardUI.activate);
                }
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.HandDialog,cardUI.imageView);
                cardUI.isClicked = true;
            }


        });
    }

    public void setCharactersDialogInField(KartuUI cardUI){
        cardUI.attack.setOnMouseClicked(e -> {
            // implementasikan attack disini Gil
            System.out.println("Aku nyerang"); // ini dummy aja

        });
        cardUI.changePosition.setOnMouseClicked(e -> {
            // implementasi changePosition
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(0);
                cardUI.getCard().setMode(Mode.ATTACK); // set mode ke attack
            }
            else{ //awalnya attack
                cardUI.imageView.setRotate(90);
                cardUI.getCard().setMode(Mode.DEFENSE); // set mode ke defense
            }
        });


        cardUI.imageView.setOnMouseClicked(el -> {
            if (cardUI.isClicked) {
                cardUI.HandDialog.getChildren().clear();
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.imageView);
                cardUI.isClicked = false;
            } else {
                //cek mode dari kartu
                if(cardUI.getCard().getMode() == Mode.ATTACK){
                    cardUI.HandDialog.getChildren().addAll(cardUI.attack, cardUI.changePosition);
                }
                else{
                    cardUI.HandDialog.getChildren().addAll(cardUI.changePosition);
                }
                cardUI.root.getChildren().clear();
                cardUI.root.getChildren().addAll(cardUI.HandDialog,cardUI.imageView);
                cardUI.isClicked = true;
            }
        });
    }

    public void renderHandsDraw() {
        myHand.getChildren().clear();
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();

        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myHand.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(90);
            }
            myCharArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            mySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        // renderMyArea();
    }

    public void renderHandsMain() {
        myHand.getChildren().clear();
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();

        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myHand.getChildren().add(cardUI);
            setHover(cardUI);
            setCardsOnHandsDialog(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(90);
            }
            myCharArea.getChildren().add(cardUI);
            setCharactersDialogInField(cardUI); // untuk ubah cuman bisa change position dan destroy
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            mySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
    }

    public void renderHandsBattle() {
        myHand.getChildren().clear();
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();
        
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myHand.getChildren().add(cardUI);
            setHover(cardUI);
        });

        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(90);
            }
            myCharArea.getChildren().add(cardUI);
            setCharactersDialogInField(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            mySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        // renderMyArea();
    }

    public void setCanMoveToArena(KartuUI card) {
        card.setOnMouseClicked(el -> {
            // 3. meletakkan MAKS. 1 kartu land
            if (card.getCard() instanceof Land) {
                if (GameState.getInstance().getCurrentPlayer().getTakeLand()) {
                    GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(card.getCard());
                    GameState.getInstance().getCurrentPlayer().setTakeLand(false);
                }
            } else if (((card.getCard() instanceof Character)
                    && (GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size() < 8))
                    || ((card.getCard() instanceof Skill)
                            && (GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size() < 8))) {
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(card.getCard());
            }
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
            renderCardMain();
        });
    }

    public void klikCharArena(KartuUI card) {
        card.setOnMouseClicked(el -> {
            // nampilin dialogbox
            renderCardBattle();
        });
    }

    public void klikCharArenaLawan(KartuUI cardArena, KartuUI cardLawan) {
        cardLawan.setOnMouseClicked(el -> {
            Character charArena = (Character) (cardArena.getCard());
            Character charLawan = (Character) (cardLawan.getCard());
            // CHAR lawan posisi menyerang
            // 1. Attack lawan <= Attack CHAR
            // CHAR lawan posisi bertahan
            // 1. Defense lawan <= Attack CHAR
            if (((charLawan.getMode() == Mode.ATTACK) && (charLawan.getAttack() <= charArena.getAttack()))
                    || ((charLawan.getMode() == Mode.DEFENSE) && (charLawan.getDefense() <= charArena.getAttack()))) {
                charArena.action(charLawan);
            }
            renderCardBattle();
        });
    }

    public void setHover(KartuUI cardUI) {
        cardUI.imageView.setOnMouseEntered(e -> {
            cardUI.setStyle(HOVERED_CARD_STYLE);
            Image img = new Image(new File(cardUI.getCard().getImage()).toURI().toString(), 200, 200, false, false);
            imageHover.getChildren().add(new ImageView(img));
            nameHover.setText(cardUI.getCard().getName());
            elementHover.setText(cardUI.getCard().getElement().toString());
            if (elementHover.getText().equalsIgnoreCase("FIRE")) {
                this.rectElemen.setFill(Color.web("#ff3333"));
                this.colorCard.setFill(Color.web("#ff8080"));
            } else if (elementHover.getText().equalsIgnoreCase("WATER")) {
                this.rectElemen.setFill(Color.web("#33ccff"));
                this.colorCard.setFill(Color.web("#80dfff"));
            } else if (elementHover.getText().equalsIgnoreCase("EARTH")) {
                this.rectElemen.setFill(Color.web("#b33c00"));
                this.colorCard.setFill(Color.web("#e64d00"));
            } else {
                this.rectElemen.setFill(Color.web("#e6ffff"));
                this.colorCard.setFill(Color.web("#ffffff"));
            }
            descriptionHover.setText(cardUI.getCard().getDescription());
            typeClass.setText(cardUI.getCard().getClass().getSimpleName());
            attackHover.setText("");
            defenceHover.setText("");
            powerHover.setText("");
            if (cardUI.getCard() instanceof Character) {
                Character cardChar = (Character) cardUI.getCard();
                attackHover.setText("ATK " + cardChar.getAttack());
                defenceHover.setText("DEF " + cardChar.getDefense());
                powerHover.setText("POW " + cardChar.getPower());
            } else if (Skill.class.isAssignableFrom(cardUI.getCard().getClass())) {
                Skill cardChar = (Skill) cardUI.getCard();
                powerHover.setText("POW " + cardChar.getPower());
                if (cardUI.getCard() instanceof SkillAura) {
                    SkillAura cardAura = (SkillAura) cardUI.getCard();
                    attackHover.setText("ATK " + cardAura.getAttack());
                    defenceHover.setText("DEF " + cardAura.getDefense());
                }
            }
            paneHover.setStyle(IDLE_CARD_STYLE);
        });
        cardUI.setOnMouseExited(e -> {
            cardUI.setStyle(IDLE_CARD_STYLE);
            paneHover.setStyle(REMOVE_CARD_STYLE);
        });
    }

    public void renderArea() {
        enemyCharArea.getChildren().clear();
        enemySkillArea.getChildren().clear();

        GameState.getInstance().getOtherPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            if(cardUI.getCard().getMode() == Mode.DEFENSE){
                cardUI.imageView.setRotate(90);
            }
            enemyCharArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        GameState.getInstance().getOtherPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            enemySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
    }

    public void clearAreaCard() {
        myCharArea.getChildren().clear();
        mySkillArea.getChildren().clear();
    }

    public void renderCardMain() {
        renderHandsMain();
        alwaysRender();
    }

    public void renderCardDraw() {
        renderHandsDraw();
        alwaysRender();
    }

    public void renderCardBattle() {
        renderHandsBattle();
        alwaysRender();
    }

    public void alwaysRender() {
        renderEnemyHand();
        renderCount();
        renderArea();
    }

    public void renderCount() {
        myCountCard.setText(
                GameState.getInstance().getCurrentPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
        enemyCountCard.setText(
                GameState.getInstance().getOtherPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
    }

    public void setParamLife(Integer myLife, Integer enemyLife) {
        // Set parameter HPpoints dan HPBar sesuai dengan
        // life points player
        myHP.setText(myLife.toString());
        enemyHP.setText(enemyLife.toString());
        myBar.setProgress(calculateBar(myLife));
        enemyBar.setProgress(calculateBar(enemyLife));
    }

    public double calculateBar(int life) {
        // Melakukan perhitungan lifeBar player
        return 1.25 * life / 100;
    }

    public void setName(String player1, String player2) {
        playerOne.setText(player1);
        playerTwo.setText(player2);
    }
}
