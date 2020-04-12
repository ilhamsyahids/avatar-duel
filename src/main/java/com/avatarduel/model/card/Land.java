package com.avatarduel.model.card;

import com.avatarduel.model.GameState;

public class Land extends Card {
  public Land() {
    super();
  }

  public Land(String name, String description, Element element, String imagePath) {
    super(name, description, element, imagePath);
  }

  @Override
  public void action(Character character) {
    action();
  }

  public void action() {
    GameState.getInstance().getCurrentPlayer().addPower(getElement(), 1);
  }
}