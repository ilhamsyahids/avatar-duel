package com.avatarduel.model.card;

public class SkillPowerUp extends Skill {

  /**
   * Constructor
   *
   * @param name        the name
   * @param description the description
   * @param element     the element
   * @param imagePath   the image path
   * @param power       the power
   */
  public SkillPowerUp(String name, String description, Element element, String imagePath, int power) {
    super(name, description, element, power, imagePath);
  }

  @Override
  public void action(Character character) {
    character.addSkills(this);
  }

}