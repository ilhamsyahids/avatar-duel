package com.avatarduel.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    public final int maxCards = 60;
    private Queue<Card> cards = new LinkedList<>();

    public Deck() {
        refill();
    }

    private void refill() {
        ArrayList<Card> ListCards = AllCards.getInstance().getAllCards();
        for (int i = 0; i < maxCards; i++) {
            int index = (int)(Math.random()*ListCards.size());
            // System.out.println(ListCards.get(index).getClass());
            // Card card = new Card(ListCards.get(index).getName(), ListCards.get(index).getDescription(), ListCards.get(index).getElement(), ListCards.get(index).getType());
            cards.add(ListCards.get(index));
        }
    }

    public Card takeCard() {
        return cards.remove();
    }
}
