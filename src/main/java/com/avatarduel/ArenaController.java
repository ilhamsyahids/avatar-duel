package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.avatarduel.model.Card;
import com.avatarduel.model.Character;
import com.avatarduel.model.Deck;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Phase.Fase;
import com.avatarduel.model.Skill;
import com.avatarduel.model.SkillAura;
//import java.awt.Rectangle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
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

    int[] handIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int[] monsterIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int monsterIdx;
    int handIdx;
    int winner;

    private static final String HOVERED_CARD_STYLE = "-fx-opacity: 0.5;";
    private static final String IDLE_CARD_STYLE = "-fx-opacity: 1;";
    private static final String DETAIL_CARD_STYLE = "-fx-background-color: cadetblue;-fx-opacity: 1;";

    public int findAvIdx(int[] arr){
        // mencari index di gridpane yg available
        for(int i=0; i<8; i++){
            if(arr[i] == 0){
                arr[i] = 1;
                return i;
            }
        }
        return 999;
    }

    public void initialize(URL url, ResourceBundle rb) {
        winner = -1;
        // renderCard();
        setBackground("file:background/arena.JPG");
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
        // myHand.add(new ImageView(card), 0, 0);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }

    public void renderHands() {
        otherHand.getChildren().clear();
        myHand.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().size());
            myHand.getChildren().add(cardUI);
            setHover(cardUI);
            cardUI.setOnMouseClicked(el -> {
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                renderCard();
            });
        });
        GameState.getInstance().getOtherPlayer().getDeck().getHandCards().forEach(item -> {
            Image img = new Image(new File("background/flip.PNG").toURI().toString(), 70, 72, false, false);
            ImageView imageView = new ImageView(img);
            otherHand.getChildren().add(imageView);
        });
    }

    public void setHover(KartuUI cardUI) {
        cardUI.setOnMouseEntered(e -> {
            cardUI.setStyle(HOVERED_CARD_STYLE);
            Image img = new Image(new File(cardUI.getCard().getImage()).toURI().toString(), 200, 200, false, false);
            imageHover.getChildren().add(new ImageView(img));
            nameHover.setText(cardUI.getCard().getName());
            elementHover.setText(cardUI.getCard().getElement().toString());
            if(elementHover.getText().equalsIgnoreCase("FIRE")){
                this.rectElemen.setFill(Color.web("#ff3333"));
                this.colorCard.setFill(Color.web("#ff8080"));
            } else if(elementHover.getText().equalsIgnoreCase("WATER")){
                this.rectElemen.setFill(Color.web("#33ccff"));
                this.colorCard.setFill(Color.web("#80dfff"));
            } else if(elementHover.getText().equalsIgnoreCase("EARTH")){
                this.rectElemen.setFill(Color.web("#b33c00"));
                this.colorCard.setFill(Color.web("#e64d00"));
            } else{
                this.rectElemen.setFill(Color.web("#e6ffff"));
                this.colorCard.setFill(Color.web("#ffffff"));
            }
            descriptionHover.setText(cardUI.getCard().getDescription());
            typeClass.setText(cardUI.getCard().getClass().getSimpleName());
            attackHover.setText("");
            defenceHover.setText("");
            powerHover.setText("");
            if (cardUI.getCard() instanceof Land) {
                // attackHover.setText("");
                // defenceHover.setText("");
                // powerHover.setText("");
            } else if (cardUI.getCard() instanceof Character) {
                Character cardChar = (Character) cardUI.getCard();
                attackHover.setText("ATK " +  cardChar.getAttack());
                defenceHover.setText("DEF " +  cardChar.getDefense());
                powerHover.setText("POW " +  cardChar.getPower());
            } else if (Skill.class.isAssignableFrom(cardUI.getCard().getClass())) {
                Skill cardChar = (Skill) cardUI.getCard();
                powerHover.setText("POW " +  cardChar.getPower());
                if (cardUI.getCard() instanceof SkillAura) {
                    SkillAura cardAura = (SkillAura) cardUI.getCard();
                    attackHover.setText("ATK " +  cardAura.getAttack());
                    defenceHover.setText("DEF " +  cardAura.getDefense());
                }
            }
            paneHover.setStyle(DETAIL_CARD_STYLE);
        });
        cardUI.setOnMouseExited(e -> {
            cardUI.setStyle(IDLE_CARD_STYLE);

            paneHover.setStyle("-fx-opacity: 0;");
        });
    }

    public Rectangle changePhase(double position){
        this.changePhase.setLayoutY(position);
        return this.changePhase;
    }
    
    public Label draw(){
        return this.draw;
    }
    
    public Label main1(){
        return this.main1;
    }
    
    public Label battle(){
        return this.battle;
    }
    
    public Label main2(){
        return this.main2;
    }
    
    public Label end(){
        return this.end;
    }
    
    public void renderArea() {
        myCharArea.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myCharArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        mySkillArea.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            mySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        enemyCharArea.getChildren().clear();
        GameState.getInstance().getOtherPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            enemyCharArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        enemySkillArea.getChildren().clear();
        GameState.getInstance().getOtherPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            enemySkillArea.getChildren().add(cardUI);
            setHover(cardUI);
        });
        
    }

    public void renderCard() {
        renderCount();
        renderHands();
        renderArea();
    }

    public void renderCount() {
        renderCountCard();
        myCountCard.setText(GameState.getInstance().getCurrentPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
        enemyCountCard.setText(GameState.getInstance().getOtherPlayer().getDeck().getLeftTakeCards() + "/" + Deck.MAXCARDSTAKKEN);
    }

    public void setParamLife(Integer myLife, Integer enemyLife){
        // Set parameter HPpoints dan HPBar sesuai dengan
        // life points player
        myHP.setText(myLife.toString());
        enemyHP.setText(enemyLife.toString());
        myBar.setProgress(calculateBar(myLife));
        enemyBar.setProgress(calculateBar(enemyLife));
    }
 
    public double calculateBar(int life){
        // Melakukan perhitungan lifeBar player
        return 1.25*life/100;
    }
    
    public void setName(String player1, String player2){
        playerOne.setText(player1);
        playerTwo.setText(player2);
    }

    public void summonCard(KartuUI kartuIni){
        monsterIdx = findAvIdx(monsterIndexArr);
        if(monsterIdx != 999){
            handIndexArr[kartuIni.deckCol] = 0;
            kartuIni.fieldCol = monsterIdx;
            kartuIni.deckCol = 999;
            myField.add(kartuIni,monsterIdx,0);
        }
    }

    public Button getButtonPhase() {
        return endPhase;
    }
}
