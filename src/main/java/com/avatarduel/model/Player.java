package com.avatarduel.model;

import javafx.util.Pair;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private int hp;
    private Deck deck;
    private Map<Element, Pair<Integer, Integer>> mapPower;

    public Player() {
        this.hp = 80;
        this.deck = new Deck();
        this.mapPower = new HashMap<>();
        mapPower.put(Element.AIR, new Pair<>(0, 0));
        mapPower.put(Element.FIRE, new Pair<>(0, 0));
        mapPower.put(Element.EARTH, new Pair<>(0, 0));
        mapPower.put(Element.WATER, new Pair<>(0, 0));
        deck.takeCardsToHand(7);
        deck.getHandCards().forEach(el -> {
            useCard(el);
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
