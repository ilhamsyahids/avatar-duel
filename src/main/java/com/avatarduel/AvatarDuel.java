package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent; 

import com.avatarduel.model.Element;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.SkillAura;
import com.avatarduel.model.AllCards;
import com.avatarduel.model.Character;
import com.avatarduel.util.CSVReader;


public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String CHAR_CSV_FILE_PATH = "card/data/character.csv";

  //variabel-variable untuk tampilan GUI
  Stage window;
  Scene scene1, scene2;

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    File skillCSVFile = new File(getClass().getResource(SKILL_CSV_FILE_PATH).toURI());
    File characterCSVFile = new File(getClass().getResource(CHAR_CSV_FILE_PATH ).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    CSVReader skillReader = new CSVReader(skillCSVFile, "\t");
    CSVReader charReader = new CSVReader(characterCSVFile, "\t");
    landReader.setSkipHeader(true);
    skillReader.setSkipHeader(true);
    charReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    List<String[]> skillRows = skillReader.read();
    List<String[]> charRows = charReader.read();
    for (String[] item : landRows) {
      Land l = new Land(item[1], item[3], Element.valueOf(item[2]));
      AllCards.addLand(l);
    }
    for (String[] item : skillRows) {
      SkillAura l = new SkillAura(item[1], item[3], Element.valueOf(item[2]), Integer.parseInt(item[6]), Integer.parseInt(item[7]), Integer.parseInt(item[5]));
      AllCards.addSkill(l);
    }
    for (String[] item : charRows) {
      Character l = new Character(item[1], item[3], Element.valueOf(item[2]), Integer.parseInt(item[5]), Integer.parseInt(item[6]), Integer.parseInt(item[7]));
      AllCards.addCharacter(l);
    }
    AllCards.addAll();
  }

  @Override
  public void start(Stage stage) throws IOException {
    Pane pane = FXMLLoader.load(getClass().getResource("SimpleUI.fxml"));
    Scene scene2 = new Scene(pane, 1366, 768);
    

    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Group root = new Group();
    root.getChildren().add(text);

    //window = primaryStage;

    //Button 1
    //Label label1 = new Label("Welcome to the first scene!");
    //Button button1 = new Button("Go to scene 2");
    //button1.setOnAction(e -> window.setScene(scene2));
    Image image = new Image("file:lalala.png");
    //ImageView mv = new ImageView(image);
    Label label1 = new Label("Welcome players");
    Button button1 = new Button("Start");
    VBox layout1 = new VBox(20);

    button1.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->  stage.setScene(scene2));

    // create a background image
    BackgroundImage backgroundimage = new BackgroundImage(image,
    BackgroundRepeat.NO_REPEAT,
    BackgroundRepeat.NO_REPEAT,
    BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT);
    
    // create Background
    Background background = new Background(backgroundimage);
    
    // set background
    layout1.setBackground(background);

    layout1.setAlignment(Pos.CENTER);
    //layout1.setPadding(new Insets(200, 300, 200, 300));
    layout1.setSpacing(20);
    //layout1.setStyle("-fx-background-color: #336699;");
    //Group root2 = new Group();
    //root2.getChildren().addAll(mv,label1,button1);
    layout1.getChildren().addAll(label1,button1);
    Scene scene = new Scene(layout1, 1000, 600);
    //Scene scene = new Scene(layout1, 750, 500);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      new GameState();
      text.setText("Avatar Duel!");
    } catch (Exception e) {
      
    }
  }

  public static void main(String[] args) {
    Application.launch();
  }
}