package com.avatarduel.model;

public class Player {
    private int hp = 80;
    private Deck deck;
    private Card[] cards;

    public Player(Deck deck) {
        this.hp = 80;
        this.deck = deck;
        this.cards = new Card[10];
    }

    public void pickCard(Card card) {
        this.cards[cards.length] = card;
    }
}
