package com.avatarduel.model;

import javafx.scene.image.*;

public abstract class Card {
    private String name;
    private String description;
    private Element element;
    private Image image;

    public Card(String name, String description, Element element, String imagePath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.image = new Image("file:" + imagePath);
    }

    public Card() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    public abstract void action(Character character);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Element getElement() {
        return element;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name + ":" + element + ":" + description.subSequence(0, 10);
    }
}
