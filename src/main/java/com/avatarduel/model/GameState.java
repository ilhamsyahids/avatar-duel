package com.avatarduel.model;

public class GameState {
  public static GameState INSTANCE = new GameState();

  private Player currentPlayer;
  private Player otherPlayer;

  public static GameState getInstance() {
    return INSTANCE;
  }

  public Player getOtherPlayer() {
    return otherPlayer;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public void setOtherPlayer(Player otherPlayer) {
    this.otherPlayer = otherPlayer;
  }

  public void nextPlayer() {
    Player temp = currentPlayer;
    currentPlayer = otherPlayer;
    otherPlayer = temp;
  }
}