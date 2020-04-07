package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.avatarduel.model.Card;
import com.avatarduel.model.Character;
import com.avatarduel.model.GameState;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private HBox mySkillArea;
    @FXML
    private HBox myCharArea;
    @FXML
    private HBox myHand;
    @FXML
    private GridPane enemyDeck;
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
    private Label descriptionHover;

    int[] handIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int[] monsterIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int monsterIdx;
    int handIdx;

    private static final String HOVERED_CARD_STYLE = "-fx-opacity: 0.5;";
    private static final String IDLE_CARD_STYLE = "-fx-opacity: 1;";
    private static final String DETAIL_CARD_STYLE = "-fx-background-color: cadetblue;";

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
        renderCard();
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

    public void initDeck() {
        Image card = new Image(new File("background/flip.PNG").toURI().toString(), 117, 72, false, false);
        // myHand.add(new ImageView(card), 0, 0);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }

    public void initiateHands() {
        myHand.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().size());
            myHand.getChildren().add(cardUI);
            cardUI.setOnMouseEntered(e -> {
                cardUI.setStyle(HOVERED_CARD_STYLE);
                Image img = new Image(new File(item.getImage()).toURI().toString(), 200, 144, false, false);
                imageHover.getChildren().add(new ImageView(img));
                nameHover.setText(cardUI.getCard().getName());
                elementHover.setText(cardUI.getCard().getElement().toString());
                descriptionHover.setText(cardUI.getCard().getDescription());
                if (cardUI.getCard() instanceof Character) {
                    Character cardChar = (Character) cardUI.getCard();
                    attackHover.setText("ATK " +  cardChar.getAttack());
                    defenceHover.setText("DEF " +  cardChar.getDefense());
                    powerHover.setText("POW " +  cardChar.getPower());
                }
                paneHover.setStyle("-fx-opacity: 1;");
                paneHover.setStyle(DETAIL_CARD_STYLE);
            });
            cardUI.setOnMouseExited(e -> {
                cardUI.setStyle(IDLE_CARD_STYLE);
                paneHover.setStyle("-fx-opacity: 0;");
            });
            cardUI.setOnMouseClicked(el -> {
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().size());
                System.out.println(GameState.getInstance().getCurrentPlayer().getDeck().getSkills().size());
                renderCard();
            });
        });
    }

    public void renderArea() {
        myCharArea.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myCharArea.getChildren().add(cardUI);
        });
        mySkillArea.getChildren().clear();
        GameState.getInstance().getCurrentPlayer().getDeck().getSkills().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            mySkillArea.getChildren().add(cardUI);
        });
        
    }

    public void renderCard() {
        initDeck();
        initiateHands();
        renderArea();
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
}
