package com.avatarduel.model.card;

public class SkillDestroy extends Skill {
    public SkillDestroy(String name, String description, Element element, String imagePath, int power) {
        super(name, description, element, power, imagePath);
    }

    @Override
    public void action(Character character) {
        // Do Nothing
    }
}
