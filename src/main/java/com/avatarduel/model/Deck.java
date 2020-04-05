package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Skill> skillArea = new ArrayList<>();
    private ArrayList<Character> characterArea = new ArrayList<>();
    private ArrayList<Card> handCards = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();
    private int countTakeCards = 0;

    public Deck() {
        refill();
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    private void refill() {
        ArrayList<Card> ListCards = AllCards.getInstance().getAllCards();
        for (int i = 0; i < Constans.MAXCARDS.getValue(); i++) {
            int idx = new Random().nextInt(ListCards.size());
            allCards.add(ListCards.get(idx));
        }
    }

    public void takeCardToHand() {
        handCards.add(allCards.remove(0));
        countTakeCards++;
    }

    public void takeCardsToHand(int n) {
        for (int i = 0; i < n; i++) {
            takeCardToHand();
        }
    }
}
