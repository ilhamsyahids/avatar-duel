package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

import com.avatarduel.model.Element;
import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.SkillAura;
import com.avatarduel.model.AllCards;
import com.avatarduel.model.Character;
import com.avatarduel.util.CSVReader;
import javafx.scene.Parent;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/land.csv";
  private static final String SKILL_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_aura.csv";
  private static final String CHAR_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/character.csv";

  //variabel-variable untuk tampilan GUI
  Stage window;
  Scene scene1, scene2;

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File((LAND_CSV_FILE_PATH));
    File skillCSVFile = new File((SKILL_CSV_FILE_PATH));
    File characterCSVFile = new File((CHAR_CSV_FILE_PATH ));
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
      Land l = new Land(item[1], item[3], Element.valueOf(item[2]), item[4]);
      AllCards.addLand(l);
    }
    for (String[] item : skillRows) {
      SkillAura l = new SkillAura(item[1], item[3], Element.valueOf(item[2]), item[4], Integer.parseInt(item[6]), Integer.parseInt(item[7]), Integer.parseInt(item[5]));
      AllCards.addSkill(l);
    }
    for (String[] item : charRows) {
      Character l = new Character(item[1], item[3], Element.valueOf(item[2]), item[4], Integer.parseInt(item[5]), Integer.parseInt(item[6]), Integer.parseInt(item[7]));
      AllCards.addCharacter(l);
    }
    AllCards.addAll();
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent window = FXMLLoader.load(getClass().getResource("RegisterUI.fxml"));
    Scene windowScene = new Scene(window, 500, 400);
    
    stage.setTitle("Avatar Duel");
    stage.getIcons().add(new Image("file:background/icon.PNG"));
    stage.setScene(windowScene);
    stage.show();

    try {
      this.loadCards();
      new GameState();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    Application.launch();
  }
}