package com.avatarduel.model;

public class SkillDestroy extends Skill {
    public SkillDestroy(String name, String description, Element element, String imagePath, int power) {
        super(name, description, element, power, imagePath);
    }

    @Override
    public void action(Character character) {
        character.destroy();
    }
}
