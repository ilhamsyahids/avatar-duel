package com.avatarduel.model;

public class GameState {
  public static GameState INSTANCE = new GameState();

  private Player myPlayer = new Player();
  private Player enemyPlayer = new Player();

  public GameState() {
    // Do nothing
  }

  public static GameState getInstance() {
    return INSTANCE;
  }

  public Player getOtherPlayer() {
    return enemyPlayer;
  }

  public Player getCurrentPlayer() {
    return myPlayer;
  }

  public void nextPlayer() {
    Player temp = myPlayer;
    myPlayer = enemyPlayer;
    enemyPlayer = temp;

    myPlayer.resetPower();
    enemyPlayer.resetPower();
  }
}