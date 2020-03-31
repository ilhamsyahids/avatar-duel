package com.avatarduel.model;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    public final int maxCards = 60;
    private Queue<Card> cards = new LinkedList<>();

    public Deck(Card[] ListCards) {
        refill(ListCards);
    }

    private void refill(Card[] ListCards) {
        for (int i = 0; i < maxCards; i++) {
            int index = (int)(Math.random()*ListCards.length);
            Card card = new Card(ListCards[index].getName(), ListCards[index].getDescription(), ListCards[index].getElement());
            cards.add(card);
        }
    }

    public Card takeCard() {
        return cards.remove();
    }
}
