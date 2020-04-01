package com.avatarduel.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private int hp;
    private Deck deck;
    private ArrayList<Card> handCards;
    private Map<Element, Integer> mapPower;

    public Player() {
        this.hp = 80;
        this.deck = new Deck();
        this.handCards = new ArrayList<>();
        this.mapPower = new HashMap<>();
        mapPower.put(Element.AIR, 0);
        mapPower.put(Element.FIRE, 0);
        mapPower.put(Element.EARTH, 0);
        mapPower.put(Element.WATER, 0);
    }

    public void pickCard() {
        Card card = deck.takeCard();
        handCards.add(card);
    }

    public void useCard(Card card) {
        // if (card instanceof Land) {
        //     int num = mapPower.get(card.getElement());
        //     mapPower.put(card.getElement(), num + 1);
        // }
    }
}
