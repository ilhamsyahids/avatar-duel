package com.avatarduel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

import com.avatarduel.model.GameState;
import com.avatarduel.model.card.Land;
import com.avatarduel.model.card.SkillAura;
import com.avatarduel.model.card.SkillDestroy;
import com.avatarduel.model.card.SkillPowerUp;
import com.avatarduel.model.card.CardsRepository;
import com.avatarduel.model.card.Character;
import javafx.scene.Parent;

public class AvatarDuel extends Application {
  public static final String LAND_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/land.csv";
  public static final String SKILL_AURA_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_aura.csv";
  public static final String SKILL_POWERUP_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_power_up.csv";
  public static final String SKILL_DESTROY_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_destroy.csv";
  public static final String CHAR_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/character.csv";

  private void loadCards() throws IOException {
    CardsRepository.addCard(Land.class, LAND_CSV_FILE_PATH);
    CardsRepository.addCard(SkillAura.class, SKILL_AURA_CSV_FILE_PATH);
    CardsRepository.addCard(SkillPowerUp.class, SKILL_POWERUP_CSV_FILE_PATH);
    CardsRepository.addCard(SkillDestroy.class, SKILL_DESTROY_CSV_FILE_PATH);
    CardsRepository.addCard(Character.class, CHAR_CSV_FILE_PATH);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent window = FXMLLoader.load(getClass().getResource("view/RegisterUI.fxml"));
    Scene windowScene = new Scene(window, 500, 400);

    stage.setTitle("Avatar Duel");
    stage.getIcons().add(new Image("file:src/main/resources/com/avatarduel/card/image/background/icon.PNG"));
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