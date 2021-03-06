package com.avatarduel.model;

import com.avatarduel.model.card.Deck;
import com.avatarduel.model.card.Element;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Player {
    private boolean isCanTakeLand;
    private int hp;
    private Deck deck;
    private Map<Element, Point> mapPower;

    /**
     * Contructor Player
     */
    public Player() {
        this.setHp(80);
        this.deck = new Deck();
        this.mapPower = new HashMap<>();
        mapPower.put(Element.AIR, new Point(0, 0));
        mapPower.put(Element.FIRE, new Point(0, 0));
        mapPower.put(Element.EARTH, new Point(0, 0));
        mapPower.put(Element.WATER, new Point(0, 0));
        mapPower.put(Element.ENERGY, new Point(0, 0));
        deck.takeCardsToHand(7);
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @return the isCanTakeLand
     */
    public boolean isCanTakeLand() {
        return isCanTakeLand;
    }

    /**
     * Set is can take land
     * 
     * @param isCan value
     */
    public void setTakeLand(boolean isCan) {
        isCanTakeLand = isCan;
    }

    /**
     * Set the player's deck with the value of this parameter
     * 
     * @param deck the deck
     */
    void setdeck(Deck deck) {
        this.deck = deck;
    }

    /**
     *
     * @return the map of element with its corresponding value of this player
     */
    public Map<Element, Point> getMapPower() {
        return mapPower;
    }

    /**
     *
     * @param el render spesific power
     * @return string with format (el)/(its max value)
     */
    public String getSpecificPower(Element el) {
        return getValuePower(el) + "/" + getMaxPower(el);
    }

    /**
     *
     * @param el max power element
     * @return the max value of power of that el element
     */
    int getMaxPower(Element el) {
        return (int) getMapPower().get(el).getX();
    }

    /**
     *
     * @param el current element
     * @return the value of unused number of power of that el element
     */
    public int getValuePower(Element el) {
        return (int) getMapPower().get(el).getY();
    }

    /**
     *
     * @return hp of this player
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set hp of this player according to param hp
     * 
     * @param hp set to
     */
    private void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Reduce hp of this player with value of param hp
     * 
     * @param hp minus by
     */
    public void reduceHp(int hp) {
        int reducer = Math.max(hp, 0);
        this.hp -= reducer;
    }

    /**
     * Add power of el element with the value of param added
     * 
     * @param el    the element
     * @param added added by
     */
    public void addPower(Element el, int added) {
        int x = (int) mapPower.get(el).getX();
        int y = (int) mapPower.get(el).getY();
        mapPower.get(el).move(x + added, y + added);
    }

    /**
     * Reduce the power of el element with the value of param minus
     * 
     * @param el    the element
     * @param minus minus by
     */
    public void reducePower(Element el, int minus) {
        int x = (int) mapPower.get(el).getX();
        int y = (int) mapPower.get(el).getY();
        mapPower.get(el).move(x, y - minus);
    }

    /**
     * Reset all unused value of power to its maxValue
     */
    void resetPower() {
        mapPower.entrySet().forEach(el -> {
            int x = (int) el.getValue().getX();
            el.getValue().move(x, x);
        });
    }
}
