package com.avatarduel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

import com.avatarduel.model.GameState;
import com.avatarduel.model.Land;
import com.avatarduel.model.SkillAura;
import com.avatarduel.model.SkillDestroy;
import com.avatarduel.model.SkillPowerUp;
import com.avatarduel.model.AllCards;
import com.avatarduel.model.Character;
import javafx.scene.Parent;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/land.csv";
  private static final String SKILL_AURA_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_aura.csv";
  private static final String SKILL_POWERUP_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_power_up.csv";
  private static final String SKILL_DESTROY_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_destroy.csv";
  private static final String CHAR_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/character.csv";

  // variabel-variable untuk tampilan GUI
  Stage window;
  Scene scene1, scene2;

  public void loadCards() throws IOException {
    AllCards.addCard(Land.class, LAND_CSV_FILE_PATH);
    AllCards.addCard(SkillAura.class, SKILL_AURA_CSV_FILE_PATH);
    AllCards.addCard(SkillPowerUp.class, SKILL_POWERUP_CSV_FILE_PATH);
    AllCards.addCard(SkillDestroy.class, SKILL_DESTROY_CSV_FILE_PATH);
    AllCards.addCard(Character.class, CHAR_CSV_FILE_PATH);
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