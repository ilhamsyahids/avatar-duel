package com.avatarduel.model.card;

import com.avatarduel.model.GameState;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LandTest {

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
        new GameState();
    }

    @Test
    public void actionTest() {
        Land ll = new Land("Land2", "", Element.FIRE, "");
        GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().set(0, ll);
        assertEquals(GameState.getInstance().getCurrentPlayer().getDeck().getHandCards().get(0), ll);
        ll.action();
        assertEquals((int) GameState.getInstance().getCurrentPlayer().getMapPower().get(Element.FIRE).getX(), 1);
        assertEquals((int) GameState.getInstance().getCurrentPlayer().getMapPower().get(Element.FIRE).getY(), 1);
    }
}