package com.avatarduel.model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Skill> skillArea = new ArrayList<>(Constans.MAXAREAFIELD.getValue());
    private ArrayList<Character> characterArea = new ArrayList<>(Constans.MAXAREAFIELD.getValue());
    private ArrayList<Card> handCards = new ArrayList<>(Constans.MAXHANDCARDS.getValue());
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
        // ArrayList<Card> ListCards = AllCards.getInstance().getAllCards();
        // for (int i = 0; i < maxCards; i++) {
        //     int index = (int)(Math.random()*ListCards.size());
        //     // System.out.println(ListCards.get(index).getClass());
        //     // Card card = new Card(ListCards.get(index).getName(), ListCards.get(index).getDescription(), ListCards.get(index).getElement(), ListCards.get(index).getType());
            
        //     cards.add(ListCards.remove(index));
        // }
    }

    public void takeCardToHand() {
        handCards.add(AllCards.getInstance().getAllCards().remove(0));
        countTakeCards++;
    }

    public void takeCardsToHand(int n) {
        for (int i = 0; i < n; i++) {
            takeCardToHand();
        }
    }
}
