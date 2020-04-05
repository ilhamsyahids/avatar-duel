package com.avatarduel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

public class SimpleUIController implements Initializable {

    @FXML
    private Label enemyHP;

    @FXML
    private Label myHP;

    @FXML
    private GridPane enemyField;

    @FXML
    private GridPane myField;

    @FXML
    private GridPane myDeck;

    @FXML
    private GridPane enemyDeck;

    @FXML
    private ProgressBar enemyBar;

    @FXML
    private ProgressBar myBar;

    
    public void initialize(URL url, ResourceBundle rb){
        Integer myLife = 45;
        Integer enemyLife = 80;

        setParamLife(myLife,enemyLife);
    }
    
    public void setParamLife(Integer myLife, Integer enemyLife){
        this.myHP.setText(myLife.toString());
        this.enemyHP.setText(enemyLife.toString());
        this.myBar.setProgress(calculateBar(myLife));
        this.enemyBar.setProgress(calculateBar(enemyLife));
    }
 
    public double calculateBar(int life){
        // Melakukan perhitungan lifeBar player
        return 1.25*life/100;
    }
}
