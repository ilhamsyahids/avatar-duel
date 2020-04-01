package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.model.SkillAura;
import com.avatarduel.model.AllCards;
import com.avatarduel.model.Character;
import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String CHAR_CSV_FILE_PATH = "card/data/character.csv";

  private AllCards allCards = AllCards.getInstance();

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
      allCards.add(l);
    }
    for (String[] item : skillRows) {
      SkillAura l = new SkillAura(item[1], item[3], Element.valueOf(item[2]), Integer.parseInt(item[6]), Integer.parseInt(item[7]), Integer.parseInt(item[5]));
      allCards.add(l);
    }
    for (String[] item : charRows) {
      Character l = new Character(item[1], item[3], Element.valueOf(item[2]), Integer.parseInt(item[5]), Integer.parseInt(item[6]), Integer.parseInt(item[7]));
      allCards.add(l);
    }
  }

  @Override
  public void start(Stage stage) {
    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Group root = new Group();
    root.getChildren().add(text);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      text.setText("Avatar Duel!");
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}