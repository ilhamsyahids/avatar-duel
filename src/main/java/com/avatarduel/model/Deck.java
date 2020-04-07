package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    final static int MAXCARDSTAKKEN = 60;
    private ArrayList<Skill> skillArea = new ArrayList<>(8);
    private ArrayList<Character> characterArea = new ArrayList<>(8);
    private ArrayList<Card> handCards = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();
    private int countTakeCards = 0;

    public Deck() {
        refill();
    }

    public int getCountTakeCards() {
        return countTakeCards;
    }

    public void setCountTakeCards(int countTakeCards) {
        this.countTakeCards = countTakeCards;
    }

    public ArrayList<Character> getCharacters() {
        return characterArea;
    }

    public ArrayList<Skill> getSkills() {
        return skillArea;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    private void refill() {
        ArrayList<Card> ListCards = AllCards.getInstance().getAllCards();
        for (int i = 0; i < MAXCARDSTAKKEN; i++) {
            int idx = new Random().nextInt(ListCards.size());
            allCards.add(ListCards.get(idx));
        }
    }

    public void takeCardToHand() {
        handCards.add(allCards.remove(0));
        setCountTakeCards(getCountTakeCards() + 1);
    }

    public void takeCardsToHand(int n) {
        for (int i = 0; i < n; i++) {
            takeCardToHand();
        }
    }

    public void moveToArea(Card card) {
        if (Skill.class.isAssignableFrom(card.getClass())) {
            skillArea.add((Skill) card);
        } else if (card instanceof Character) {
            characterArea.add((Character) card);
        }
        handCards.remove(card);
    }
}
