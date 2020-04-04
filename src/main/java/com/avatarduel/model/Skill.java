package com.avatarduel.model;

public abstract class Skill extends Card implements Powerable {
    private int power;

    public Skill() {
        super();
        power = 0;
    }

    public Skill(String name, String description, Element element, int power) {
        super(name, description, element);
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }
}
