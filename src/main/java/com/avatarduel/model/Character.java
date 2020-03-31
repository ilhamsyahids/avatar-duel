package com.avatarduel.model;

public class Character extends Card {
    private int power;

    public Character() {
        super();
    }

    public Character(String name, String description, Element element) {
        super(name, description, element);
    }
}
