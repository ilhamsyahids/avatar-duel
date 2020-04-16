package com.avatarduel.model;

import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    private static final String LAND_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/land.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_aura.csv";
    private static final String SKILL_POWERUP_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_power_up.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/skill_destroy.csv";
    private static final String CHAR_CSV_FILE_PATH = "src/main/resources/com/avatarduel/card/data/character.csv";

    @Before
    public void setUp() throws Exception {
        CardsRepository.addCard(Land.class, LAND_CSV_FILE_PATH);
        CardsRepository.addCard(SkillAura.class, SKILL_AURA_CSV_FILE_PATH);
        CardsRepository.addCard(SkillPowerUp.class, SKILL_POWERUP_CSV_FILE_PATH);
        CardsRepository.addCard(SkillDestroy.class, SKILL_DESTROY_CSV_FILE_PATH);
        CardsRepository.addCard(Character.class, CHAR_CSV_FILE_PATH);
    }

    @Test
    public void checkCardInDeck() {
        Deck deck = new Deck();
        deck.takeCardToHand();
        assertEquals(deck.getLeftTakeCards(), Deck.MAXCARDSTAKKEN - 1);
        deck.takeCardsToHand(5);
        assertEquals(deck.getLeftTakeCards(), Deck.MAXCARDSTAKKEN - 6);
        deck.setLeftTakeCards(6);
        assertEquals(deck.getLeftTakeCards(), 6);
    }

    @Test
    public void checkHandCards() {
        Deck deck = new Deck();
        assertEquals(deck.getHandCards().size(), 0);
        deck.takeCardsToHand(5);
        assertEquals(deck.getHandCards().size(), 5);
    }

    @Test
    public void checkArea() throws Exception {
        Deck deck = new Deck();
        Character c1 = new Character("Char1", "", Element.AIR, "", 1, 2, 3);
        SkillDestroy d1 = new SkillDestroy("Destroy1", "", Element.AIR, "", 1);
        deck.getHandCards().add(c1);
        deck.getHandCards().add(d1);
        assertEquals(deck.getHandCards().size(), 2);
        try {
            deck.moveToArea(deck.getHandCards().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(deck.getHandCards().size(), 1);
        deck.moveToArea(deck.getHandCards().get(0));
        assertEquals(deck.getCharacters().size(), 1);
        assertEquals(deck.getHandCards().size(), 0);
        assertEquals(deck.getSkills().size(), 1);
    }
}