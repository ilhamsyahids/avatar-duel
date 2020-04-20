package com.avatarduel.model.card;

import com.avatarduel.model.GameState;

public abstract class Skill extends Card implements Powerable {
    private int power;

    /**
     * Constructor with default value
     */
    public Skill() {
        super();
        power = 0;
    }

    /**
     * Constructor with defined value
     *
     * @param name        the name
     * @param description the description
     * @param element     the element
     * @param power       the power
     * @param imagePath   the imagepath
     */
    public Skill(String name, String description, Element element, int power, String imagePath) {
        super(name, description, element, imagePath);
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public boolean isCanSummon() {
        int power = (int) GameState.getInstance().getCurrentPlayer().getMapPower().get(getElement()).getY();
        return (getPower() <= power);

    }
}
