package com.avatarduel.model.card;

public abstract class Card {
    private String name;
    private String description;
    private Element element;
    private String image;

    /**
     * Constuctor
     *
     * @param name
     * @param description
     * @param element
     * @param imagePath
     */
    public Card(String name, String description, Element element, String imagePath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.image = imagePath;
    }

    /**
     * Constructor with default value
     */
    public Card() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    /**
     * Action procedure to be overidden by children class
     * @param character
     */
    public abstract void action(Character character);

    /**
     *
     * @return name (name of this card)
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description (the description of this card)
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return element (element of the card)
     */
    public Element getElement() {
        return element;
    }

    /**
     *
     * @return the image path of the card
     */
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name + ":" + element + ":" + image.split("/")[image.split("/").length - 1]
                + this.getClass().getSimpleName();
    }
}
