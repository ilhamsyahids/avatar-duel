package com.avatarduel.model.card;

import java.util.ArrayList;

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

    /**
     * Rest of can take cards, format: <b>60/60</b>
     * 
     * @return String like the format
     */
    public String getRestOfCanTakeCards() {
        return getLeftTakeCards() + "/" + MAXCARDSTAKKEN;
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
        for (int i = 0; i < MAXCARDSTAKKEN; i++) {
            allCards.add(CardsRepository.getInstance().getCard());
        }
    }

    public void takeCardToHand() {
        if (handCards.size() < 8) {
            handCards.add(allCards.remove(0));
            setLeftTakeCards(getLeftTakeCards() - 1);
        }
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
        } else if (card instanceof Land) {
            ((Land) card).action();
        }
        handCards.remove(card);
    }

    /*
    deleted soon UwU
    public void hasAttacked(Character thisChar){
        characterArea.remove(thisChar);
        thisChar.setUdhAttackThisTurn(true);
        characterArea.add(thisChar);
    }
    */
}
