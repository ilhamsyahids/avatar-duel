package com.avatarduel.model.card;

public class SkillPowerUp extends Skill {

  /**
   * Constructor
   *
   * @param name
   * @param description
   * @param element
   * @param imagePath
   * @param power
   */
  public SkillPowerUp(String name, String description, Element element, String imagePath, int power) {
    super(name, description, element, power, imagePath);
  }

  @Override
  public void action(Character character) {
    character.addSkills(this);
  }

}