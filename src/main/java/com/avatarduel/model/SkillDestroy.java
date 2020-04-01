package com.avatarduel.model;

public class SkillDestroy extends Skill {
    final int inf = -999999;
    private int attack;
    private int defense;

    public SkillDestroy(String name, String description, Element element, int attack, int defense) {
        super(name, description, element);
        this.attack = inf;
        this.defense = inf;
    }

    @Override
    public void useSkill(Character card) {
        card.setAttack(card.getAttack() + attack);
        card.setDefense(card.getDefense() + defense);
    }
}
