package com.avatarduel.model.card;

public class SkillAura extends Skill {
    private int attack;
    private int defense;

    /**
     * Constructor with defined value
     * 
     * @param name        the name
     * @param description the description
     * @param element     the element
     * @param imagePath   the image path
     * @param attack      the attack
     * @param defense     the defense
     * @param power       the power
     */
    public SkillAura(String name, String description, Element element, String imagePath, int attack, int defense,
            int power) {
        super(name, description, element, power, imagePath);
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public void action(Character character) {
        character.addSkills(this);
    }

    /**
     *
     * @return the attack of this skillAura that will be added to any character
     */
    public int getAttack() {
        return attack;
    }

    /**
     *
     * @return the defense of this skillAura that will be added to any character
     */
    public int getDefense() {
        return defense;
    }
}
