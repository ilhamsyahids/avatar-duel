package com.avatarduel.model;

public class SkillDecorator extends Card {
    private final Skill decoratedSkill;

    public SkillDecorator(Skill decoratedSkill) {
        this.decoratedSkill = decoratedSkill;
    }
}
