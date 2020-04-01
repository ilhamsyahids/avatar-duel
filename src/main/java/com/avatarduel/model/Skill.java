package com.avatarduel.model;

public abstract class Skill extends Card {
    public Skill() {
        super();
    }

    public Skill(String name, String description, Element element) {
        super(name, description, element);
    }

    abstract public void useSkill(Character card);
}
