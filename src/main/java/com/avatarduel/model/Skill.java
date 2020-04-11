package com.avatarduel.model;

public abstract class Skill extends Card implements Powerable {
    private int power;

    public Skill() {
        super();
        power = 0;
    }

    public Skill(String name, String description, Element element, int power, String imagePath) {
        super(name, description, element, imagePath, Mode.ATTACK);
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }
}
