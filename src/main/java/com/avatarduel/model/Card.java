package com.avatarduel.model;

public class Card {
    private String name;
    private String description;
    private Element element;

    public Card(String name, String description, Element element) {
        this.name = name;
        this.description = description;
        this.element = element;
    }

    public Card() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return name + ":" + element + ":" + description.subSequence(0, 10);
    }
}
