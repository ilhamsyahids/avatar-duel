package com.avatarduel.model.card;

import java.util.ArrayList;

public class Deck {
    public final static int MAXCARDSTAKKEN = 60;
    public final static int MAXCARDSINAREA = 6;
    private ArrayList<Skill> skillArea = new ArrayList<>(MAXCARDSINAREA);
    private ArrayList<Character> characterArea = new ArrayList<>(MAXCARDSINAREA);
    private ArrayList<Card> handCards = new ArrayList<>();
    private ArrayList<Card> allCards = new ArrayList<>();
    private int leftTakeCards = MAXCARDSTAKKEN;

    /**
     * Constructor
     */
    public Deck() {
        refill();
    }

    /**
     *
     * @return card that is left in Deck ( num of Cards that is Drawable )
     */
    public int getLeftTakeCards() {
        return leftTakeCards;
    }

    /**
     * Update number of remaining Cards in Deck
     *
     * @param leftTakeCards
     */
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

    /**
     *
     * @return the list of Character in Field
     */
    public ArrayList<Character> getCharacters() {
        return characterArea;
    }

    /**
     *
     * @return the list of Skill Card in Field
     */
    public ArrayList<Skill> getSkills() {
        return skillArea;
    }

    /**
     *
     * @return the list of Card in Hand
     */
    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    /**
     * Set the list of handCards
     * @param handCards
     */
    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    /**
     * Initialize all cards in Deck from CardsRepository(can be any type)
     */
    private void refill() {
        for (int i = 0; i < MAXCARDSTAKKEN; i++) {
            allCards.add(CardsRepository.getInstance().getCard());
        }
    }

    /**
     * Add card to Hand From Deck(allCards)
     */
    public void takeCardToHand() {
        if (handCards.size() < 8) {
            handCards.add(allCards.remove(0));
            setLeftTakeCards(getLeftTakeCards() - 1);
        }
    }

    /**
     * Add card to Hand From Deck(allCards) n times
     * @param n
     */
    public void takeCardsToHand(int n) {
        for (int i = 0; i < n; i++) {
            takeCardToHand();
        }
    }

    /**
     * Move the card from hand to field if space in field for that type of card is not full
     * @param card
     * @throws Exception
     */
    public void moveToArea(Card card) throws Exception {
        if (card instanceof Character) {
            if (characterArea.size() >= MAXCARDSINAREA)
                throw new Exception("Character Area Penuh!");
            characterArea.add((Character) card);
        } else if (Skill.class.isAssignableFrom(card.getClass())) {
            if (skillArea.size() >= MAXCARDSINAREA)
                throw new Exception("Skill Area Penuh!");
            skillArea.add((Skill) card);
        } else if (card instanceof Land) {
            ((Land) card).action();
        }
        handCards.remove(card);
    }

}
