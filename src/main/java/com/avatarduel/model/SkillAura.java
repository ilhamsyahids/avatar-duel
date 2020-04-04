package com.avatarduel.model;

public class SkillAura extends Skill {
    private int attack;
    private int defense;

    public SkillAura(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element, power);
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public void action(Character character) {
        // TODO Auto-generated method stub

    }
}
