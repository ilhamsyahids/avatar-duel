package com.avatarduel.model;

public class SkillAura extends Skill {
    private int attack;
    private int defense;
    private int power;

    public SkillAura(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    @Override
    public void useSkill(Character card) {
        card.setAttack(card.getAttack() + attack);
        card.setDefense(card.getDefense() + defense);
    }
}
