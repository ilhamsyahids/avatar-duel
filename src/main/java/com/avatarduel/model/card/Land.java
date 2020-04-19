package com.avatarduel.model.card;

import com.avatarduel.model.GameState;

public class Land extends Card {
  /**
   * Constructor with default value
   */
  public Land() {
    super();
  }

  /**
   * Constructor with defined value
   * 
   * @param name
   * @param description
   * @param element
   * @param imagePath
   */
  Land(String name, String description, Element element, String imagePath) {
    super(name, description, element, imagePath);
  }

  /**
   * Do action of landCard
   * 
   * @param character the target
   */
  @Override
  public void action(Character character) {
    action();
  }

  /**
   * Increase the player energy according to this land's element
   */
  public void action() {
    GameState.getInstance().getCurrentPlayer().addPower(getElement(), 1);
  }
}