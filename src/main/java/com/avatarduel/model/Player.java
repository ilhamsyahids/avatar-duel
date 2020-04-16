package com.avatarduel.model;

import com.avatarduel.model.card.Deck;
import com.avatarduel.model.card.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public class Player {
    public boolean isCanTakeLand;
    private int hp;
    private Deck deck;
    private Map<Element, Point> mapPower;

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

    public void setTakeLand(boolean isCan) {
        isCanTakeLand = isCan;
    }

    public void setdeck(Deck deck) {
        this.deck = deck;
    }

    public Map<Element, Point> getMapPower() {
        return mapPower;
    }

    public String getSpecificPower(Element el) {
        return getValuePower(el) + "/" + getMaxPower(el);
    }

    public int getMaxPower(Element el) {
        return (int) getMapPower().get(el).getX();
    }

    public int getValuePower(Element el) {
        return (int) getMapPower().get(el).getY();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void reduceHp(int hp) {
        this.hp -= hp;
    }

    public void addPower(Element el, int added) {
        int x = (int) mapPower.get(el).getX();
        int y = (int) mapPower.get(el).getY();
        mapPower.get(el).move(x + added, y + added);
    }

    public void reducePower(Element el, int minus) {
        int x = (int) mapPower.get(el).getX();
        int y = (int) mapPower.get(el).getY();
        mapPower.get(el).move(x, y - minus);
    }

    public void resetPower() {
        mapPower.entrySet().forEach(el -> {
            int x = (int) el.getValue().getX();
            el.getValue().move(x, x);
        });
    }

    @Override
    public String toString() {
        return "Hand:" + deck.getHandCards();
    }
}
