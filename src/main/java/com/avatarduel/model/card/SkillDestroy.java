package com.avatarduel.model.card;

public class SkillDestroy extends Skill {
    /**
     * Constuctor
     *
     * @param name
     * @param description
     * @param element
     * @param imagePath
     * @param power
     */
    public SkillDestroy(String name, String description, Element element, String imagePath, int power) {
        super(name, description, element, power, imagePath);
    }

    @Override
    public void action(Character character) {
        // Do Nothing
    }
}
