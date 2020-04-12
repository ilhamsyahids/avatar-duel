package com.avatarduel.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {
    @Before
    public void setUp() throws Exception {
        Land l1 = new Land("Land1", "", Element.FIRE, "");
        Land l2 = new Land("Land2", "", Element.FIRE, "");
        Land l3 = new Land("Land3", "", Element.FIRE, "");
        SkillAura s1 = new SkillAura("Aura1", "", Element.AIR, "", 1, 2, 2);
        SkillAura s2 = new SkillAura("Aura2", "", Element.AIR, "", 1, 2, 2);
        SkillAura s3 = new SkillAura("Aura3", "", Element.AIR, "", 1, 2, 2);
        SkillDestroy d1 = new SkillDestroy("Destroy1", "", Element.AIR, "", 1);
        SkillDestroy d2 = new SkillDestroy("Destroy2", "", Element.AIR, "", 1);
        SkillPowerUp p1 = new SkillPowerUp("Destroy3", "", Element.AIR, "", 1);
        Character c1 = new Character("Char1", "", Element.AIR, "", 1, 2, 3);
        Character c2 = new Character("Char2", "", Element.AIR, "", 1, 2, 3);
        Character c3 = new Character("Char3", "", Element.AIR, "", 1, 2, 3);
        CardsRepository.getInstance().getAllCards().add(c1);
        CardsRepository.getInstance().getAllCards().add(c2);
        CardsRepository.getInstance().getAllCards().add(c3);
        CardsRepository.getInstance().getAllCards().add(l1);
        CardsRepository.getInstance().getAllCards().add(l2);
        CardsRepository.getInstance().getAllCards().add(l3);
        CardsRepository.getInstance().getAllCards().add(s1);
        CardsRepository.getInstance().getAllCards().add(s2);
        CardsRepository.getInstance().getAllCards().add(s3);
        CardsRepository.getInstance().getAllCards().add(d1);
        CardsRepository.getInstance().getAllCards().add(d2);
        CardsRepository.getInstance().getAllCards().add(p1);
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
    public void checkArea() {
        Deck deck = new Deck();
        Character c1 = new Character("Char1", "", Element.AIR, "", 1, 2, 3);
        SkillDestroy d1 = new SkillDestroy("Destroy1", "", Element.AIR, "", 1);
        deck.getHandCards().add(c1);
        deck.getHandCards().add(d1);
        assertEquals(deck.getHandCards().size(), 2);
        deck.moveToArea(deck.getHandCards().get(0));
        assertEquals(deck.getHandCards().size(), 1);
        deck.moveToArea(deck.getHandCards().get(0));
        assertEquals(deck.getCharacters().size(), 1);
        assertEquals(deck.getHandCards().size(), 0);
        assertEquals(deck.getSkills().size(), 1);
    }
}