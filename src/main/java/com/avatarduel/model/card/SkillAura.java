package com.avatarduel.model.card;

public class SkillAura extends Skill {
    private int attack;
    private int defense;

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

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
