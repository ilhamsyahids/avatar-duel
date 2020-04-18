package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {

    @Before
    public void setUp() throws Exception {
        CardsRepository.addCard(Land.class, AvatarDuel.LAND_CSV_FILE_PATH);
        CardsRepository.addCard(SkillAura.class, AvatarDuel.SKILL_AURA_CSV_FILE_PATH);
        CardsRepository.addCard(SkillPowerUp.class, AvatarDuel.SKILL_POWERUP_CSV_FILE_PATH);
        CardsRepository.addCard(SkillDestroy.class, AvatarDuel.SKILL_DESTROY_CSV_FILE_PATH);
        CardsRepository.addCard(Character.class, AvatarDuel.CHAR_CSV_FILE_PATH);
        new GameState();
    }

    @Test
    public void initGameState() {
        assertNotNull(GameState.getInstance().getCurrentPlayer());
        assertNotNull(GameState.getInstance().getOtherPlayer());
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