package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.avatarduel.model.Card;
import com.avatarduel.model.Character;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
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
    private HBox myDeck;
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

    int[] handIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int[] monsterIndexArr = {0, 0, 0, 0, 0, 0, 0, 0};
    int monsterIdx;
    int handIdx;
    int count = 0;

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
        initDeck();
        // refillDeck();
        count = 0;
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
        // myDeck.add(new ImageView(card), 0, 0);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
        initiateHands();
    }

    public void initiateHands(){
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().forEach(item -> {
            KartuUI cardUI = new KartuUI(item);
            myDeck.getChildren().add(cardUI);
            cardUI.setOnMouseClicked(el -> {
                GameState.getInstance().getCurrentPlayer().getDeck().moveToArea(cardUI.getCard());
                myDeck.getChildren().clear();
                initiateHands();
            });
        });
    }

    public void refillDeck() {
        // fungsi draw kartu
        Card card = GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().get(0);
        Image img = new Image(new File(card.getImage()).toURI().toString(), 70, 72, false, false);
        ImageView mv = new ImageView(img);
        handIdx = findAvIdx(handIndexArr);
        // KartuUI kartuIni = new KartuUI(mv, 999, handIdx);
        // kartuIni.setOnMouseClicked(event -> {
        //     summonCard(kartuIni);
        // });
        // myDeck.add(kartuIni, handIdx, 0);

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
