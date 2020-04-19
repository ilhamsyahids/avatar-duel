package com.avatarduel.model.card;

public abstract class Card {
    private String name;
    private String description;
    private Element element;
    private String image;

    /**
     * Constuctor
     *
     * @param name        the name
     * @param description the description
     * @param element     the element
     * @param imagePath   the imagepath
     */
    Card(String name, String description, Element element, String imagePath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.image = imagePath;
    }

    /**
     * Constructor with default value
     */
    Card() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    /**
     * Action procedure to be overidden by children class
     * 
     * @param character target character
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
}
