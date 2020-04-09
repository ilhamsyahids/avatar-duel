package com.avatarduel.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        AllCards.addCharacter(c1);
        AllCards.addCharacter(c2);
        AllCards.addCharacter(c3);
        AllCards.addLand(l1);
        AllCards.addLand(l2);
        AllCards.addLand(l3);
        AllCards.addSkill(s1);
        AllCards.addSkill(s2);
        AllCards.addSkill(s3);
        AllCards.addSkill(d1);
        AllCards.addSkill(d2);
        AllCards.addSkill(p1);
        AllCards.addAll();
        new GameState();
    }

    @Test
    public void initGameState() {
        assertEquals(GameState.getInstance().getListPlayer().size(), GameState.numOfPlayer);
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