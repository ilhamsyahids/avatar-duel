package com.avatarduel.model;

public class WithAura extends SkillDecorator {
    private int amount;

    public WithAura(Skill decoratedSkill, int amount) {
        super(decoratedSkill);
        this.amount = amount;
    }
}
