package com.avatarduel.model;

public class SkillAura extends Skill {
    private int attack;
    private int defense;

    public SkillAura(String name, String description, Element element, String imagePath, int attack, int defense, int power) {
        super(name, description, element, power, imagePath);
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public void action(Character character) {
        character.setAttack(character.getAttack() + attack);
        character.setDefense(character.getDefense() + defense);
    }
}
