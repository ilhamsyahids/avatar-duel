package com.avatarduel.model;

public class Character extends Card implements Powerable {
    private int attack;
    private int defense;
    private int power;
    private Mode mode;

    public Character(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.mode = Mode.DEFENSE;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void action(Character character) {
        // TODO Auto-generated method stub

    }
}
