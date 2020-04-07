package com.avatarduel.model;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point; 



public class Player {
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
        deck.takeCardsToHand(7);
        deck.getHandCards().forEach(el -> {
            useCard(el);
        });
    }

    public Deck getDeck() {
        return deck;
    }

    public void setdeck(Deck deck) {
        this.deck = deck;
    }

    public Map<Element, Point> getMapPower() {
        return mapPower;
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
        int x = (int)mapPower.get(el).getX();
        int y = (int)mapPower.get(el).getY();
        mapPower.get(el).move(x + 1, y + 1);
    }

    public void resetPower() {
        mapPower.entrySet().forEach(el -> {
            int x = (int) el.getValue().getX();
            el.getValue().move(x, x);
        });
    }

    public void useCard(Card card) {
        System.out.println(card);
        System.out.println(card.getClass());
    }

    @Override
    public String toString() {
        return "Hand:" + deck.getHandCards();
    }
}
