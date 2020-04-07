package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    public final static int MAXCARDSTAKKEN = 60;
    private ArrayList<Skill> skillArea = new ArrayList<>(8);
    private ArrayList<Character> characterArea = new ArrayList<>(8);
    private ArrayList<Card> handCards = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();
    private int leftTakeCards = MAXCARDSTAKKEN;

    public Deck() {
        refill();
    }

    public int getLeftTakeCards() {
        return leftTakeCards;
    }

    public void setLeftTakeCards(int leftTakeCards) {
        this.leftTakeCards = leftTakeCards;
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
        setLeftTakeCards(getLeftTakeCards() - 1);
    }

    public void takeCardsToHand(int n) {
        for (int i = 0; i < n; i++) {
            takeCardToHand();
        }
    }

    public void moveToArea(Card card) {
        if (card instanceof Character) {
            characterArea.add((Character) card);
        } else if (Skill.class.isAssignableFrom(card.getClass())) {
            skillArea.add((Skill) card);
        }
        handCards.remove(card);
    }
}
