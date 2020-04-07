package com.avatarduel;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.layout.Pane;

public class ArenaController implements Initializable{

    @FXML private Pane utama;
    @FXML private Label enemyHP;
    @FXML private Label myHP;
    @FXML private GridPane enemyField;
    @FXML private GridPane myField;
    @FXML private GridPane myDeck;
    @FXML private GridPane enemyDeck;
    @FXML private ProgressBar enemyBar;
    @FXML private ProgressBar myBar;
    @FXML private Label playerTwo;
    @FXML private Label playerOne;
    @FXML private Pane fillMyCard;
    @FXML private Pane fillEnemyCard;
    
    int count;
    
    public void initialize(URL url, ResourceBundle rb){
        initDeck();
//        refillDeck();
        count = 0;
    }
    
    public void setBackground(String pict){
        // Set background pada this scene
        Image image = new Image(pict);
        BackgroundImage backgroundImage = new BackgroundImage(image,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(100,100,false,false,false,true));
        
        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }
    
    public void initDeck(){
        Image card = new Image(new File("background/flip.PNG").toURI().toString(), 117, 72, false, false);
//        myDeck.add(new ImageView(card), 0, 0);
        fillMyCard.getChildren().add(new ImageView(card));
        fillEnemyCard.getChildren().add(new ImageView(card));
    }
    
    public void refillDeck(){
        if(count < 7){
            Image card = new Image(new File("background/card.PNG").toURI().toString(), 63.86, 72, false, false);
            myDeck.add(new ImageView(card), count, 0);
            count++;
        } else{
            //Do nothing
            
        }
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
}
