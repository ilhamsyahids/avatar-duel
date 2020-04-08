package com.avatarduel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.model.Phase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    @FXML private Pane utama;
    @FXML private TextField playerOneName;
    @FXML private TextField playerTwoName;
    @FXML private Label title;
    @FXML private Button playButton;
    
    public void initialize(URL url, ResourceBundle rb){
        setBackground("file:background/RegBackground.jpg");
    }
    
    public void setBackground(String pict){
        Image image = new Image(pict);
        BackgroundImage backgroundImage = new BackgroundImage(image,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(100,100,true,true,false,true));
        
        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }

    public void changeScene(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ArenaUI.fxml"));
        Parent otherStage = loader.load();
        Scene otherScene = new Scene(otherStage,1360,700);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(otherScene);
        stage.setX(0);
        stage.setY(0);
        
        ArenaController controller = loader.getController();
        Phase.getInstancePhase().setController(controller);
        Phase.getInstancePhase().startGame();
        String playerOne = playerOneName.getText();
        String playerTwo = playerTwoName.getText();
        controller.setName(playerOne, playerTwo);
        
        stage.show();
        
    }
    
}
