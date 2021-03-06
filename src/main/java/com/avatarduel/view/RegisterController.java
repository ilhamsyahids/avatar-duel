package com.avatarduel.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.model.Phase;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    private MediaPlayer player;

    @FXML
    private Pane utama;
    @FXML
    private TextField playerOneName;
    @FXML
    private TextField playerTwoName;
    @FXML
    private CheckBox soundControl;

    /**
     * Initialize the RegisterUI.fxml
     *
     * @param url the url
     * @param rb  the ResourceBundle
     */
    public void initialize(URL url, ResourceBundle rb) {
        Media soundtrack = new Media(
                new File("src/main/resources/com/avatarduel/card/data/soundtrack/Intro.mp3").toURI().toString());
        player = new MediaPlayer(soundtrack);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (soundControl.isSelected())
                    player.play();
                else
                    player.pause();
            }
        };
        soundControl.setOnAction(event);
        setBackground();
    }

    /**
     * Set the background of RegisterUI.fxml with the image on pict path
     */
    private void setBackground() {
        Image image = new Image("file:src/main/resources/com/avatarduel/card/image/background/RegBackground.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true));

        Background background = new Background(backgroundImage);
        utama.setBackground(background);
    }

    /**
     * Change the scene of UI from Register.fxml to ArenaController
     *
     * @param event the event
     * @throws IOException the IOException
     */
    public void changeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ArenaUI.fxml"));
        Parent otherStage = loader.load();
        Scene otherScene = new Scene(otherStage, 1360, 700);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(otherScene);
        stage.setX(0);
        stage.setY(0);

        ArenaController controller = loader.getController();
        Phase.getInstancePhase().setController(controller);
        Phase.getInstancePhase().startGame();
        String playerOne = playerOneName.getText();
        String playerTwo = playerTwoName.getText();
        controller.setName(playerOne, playerTwo);
        player.stop();
        stage.show();
        if (soundControl.isSelected())
            controller.playSound();
    }

}
