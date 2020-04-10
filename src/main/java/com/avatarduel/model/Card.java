package com.avatarduel.model;

public abstract class Card {
    private String name;
    private String description;
    private Element element;
    private String image;
    private Mode mode;

    public Card(String name, String description, Element element, String imagePath, Mode mode) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.image = imagePath;
        this.mode = mode;
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

    public Mode getMode(){ return mode; }

    public Element getElement() {
        return element;
    }

    public String getImage() {
        return image;
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }
    
    @Override
    public String toString() {
        return name + ":" + element + ":" + image.split("/")[image.split("/").length - 1] + this.getClass().getSimpleName();
    }
}
