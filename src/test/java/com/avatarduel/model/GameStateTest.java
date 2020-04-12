package com.avatarduel.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {

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
        AllCards.getInstance().getAllCards().add(c1);
        AllCards.getInstance().getAllCards().add(c2);
        AllCards.getInstance().getAllCards().add(c3);
        AllCards.getInstance().getAllCards().add(l1);
        AllCards.getInstance().getAllCards().add(l2);
        AllCards.getInstance().getAllCards().add(l3);
        AllCards.getInstance().getAllCards().add(s1);
        AllCards.getInstance().getAllCards().add(s2);
        AllCards.getInstance().getAllCards().add(s3);
        AllCards.getInstance().getAllCards().add(d1);
        AllCards.getInstance().getAllCards().add(d2);
        AllCards.getInstance().getAllCards().add(p1);
        new GameState();
    }

    @Test
    public void initGameState() {
        assertTrue(GameState.getInstance().getCurrentPlayer() instanceof Player);
        assertTrue(GameState.getInstance().getOtherPlayer() instanceof Player);
    }

    // Test Change Player
    @Test
    public void testPlayer() {
        Player curr = GameState.getInstance().getCurrentPlayer();
        Player other = GameState.getInstance().getOtherPlayer();
        GameState.getInstance().nextPlayer();
        assertEquals(curr, GameState.getInstance().getOtherPlayer());
        assertEquals(other, GameState.getInstance().getCurrentPlayer());
    }
}