package com.avatarduel.model;

import java.util.ArrayList;

public class GameState {
  final static int numOfPlayer = 2;
  private ArrayList<Player> listPlayer = new ArrayList<>(numOfPlayer);
  private int idPlayer = 0;

  public GameState() {
    listPlayer.add(new Player());
    listPlayer.add(new Player());
  }

  // public Player getOtherPlayer() {
  //   return listPlayer.get(idPlayer);
  // }

  public Player getCurrentPlayer() {
    return listPlayer.get(idPlayer);
  }

  // public void setCurrentPlayer(Player currentPlayer) {
  //   this.currentPlayer = currentPlayer;
  // }

  // public void setOtherPlayer(Player otherPlayer) {
  //   this.otherPlayer = otherPlayer;
  // }

  public void nextPlayer() {
    idPlayer = (idPlayer + 1) % 2;
  }
}