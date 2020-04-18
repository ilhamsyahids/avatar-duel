package com.avatarduel.model;

public class GameState {
  public static GameState INSTANCE = new GameState();

  private Player myPlayer = new Player();
  private Player enemyPlayer = new Player();

  /**
   * Constructor
   */
  public GameState() {
    // Do nothing
  }

  /**
   *
   * @return single instance of this class
   */
  public static GameState getInstance() {
    return INSTANCE;
  }

  /**
   *
   * @return enemyPlayer
   */
  public Player getOtherPlayer() {
    return enemyPlayer;
  }

  /**
   *
   * @return currentPlayer
   */
  public Player getCurrentPlayer() {
    return myPlayer;
  }

  /**
   * set the next currentPlayer and enemyPlayer
   */
  public void nextPlayer() {
    Player temp = myPlayer;
    myPlayer = enemyPlayer;
    enemyPlayer = temp;

    myPlayer.resetPower();
    enemyPlayer.resetPower();
  }
}