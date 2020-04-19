package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        CardsRepository.addCard(Land.class, AvatarDuel.LAND_CSV_FILE_PATH);
        CardsRepository.addCard(SkillAura.class, AvatarDuel.SKILL_AURA_CSV_FILE_PATH);
        CardsRepository.addCard(SkillPowerUp.class, AvatarDuel.SKILL_POWERUP_CSV_FILE_PATH);
        CardsRepository.addCard(SkillDestroy.class, AvatarDuel.SKILL_DESTROY_CSV_FILE_PATH);
        CardsRepository.addCard(Character.class, AvatarDuel.CHAR_CSV_FILE_PATH);
        player = new Player();
    }

    @Test
    public void getNSetDeck() {
        Deck deck = new Deck();
        player.setdeck(deck);
        assertSame(deck, player.getDeck());
    }

    @Test
    public void checkMapPower() {
        Map<Element, Point> mapPower = new HashMap<>();
        mapPower.put(Element.AIR, new Point(0, 0));
        mapPower.put(Element.FIRE, new Point(0, 0));
        mapPower.put(Element.EARTH, new Point(0, 0));
        mapPower.put(Element.WATER, new Point(0, 0));
        mapPower.put(Element.ENERGY, new Point(0, 0));
        assertEquals(mapPower, player.getMapPower());

        player.addPower(Element.AIR, 3);
        assertEquals(player.getMaxPower(Element.AIR), 3);
        assertEquals(player.getValuePower(Element.AIR), 3);

        player.reducePower(Element.AIR, 2);
        assertEquals(player.getMaxPower(Element.AIR), 3);
        assertEquals(player.getValuePower(Element.AIR), 1);

        player.resetPower();
        Map<Element, Point> mapPower2 = new HashMap<>();
        mapPower2.put(Element.AIR, new Point(3, 3));
        mapPower2.put(Element.FIRE, new Point(0, 0));
        mapPower2.put(Element.EARTH, new Point(0, 0));
        mapPower2.put(Element.WATER, new Point(0, 0));
        mapPower2.put(Element.ENERGY, new Point(0, 0));
        assertEquals(mapPower2, player.getMapPower());
        assertEquals(player.getMaxPower(Element.AIR), 3);
    }

    @Test
    public void checkHp() {
        assertEquals(player.getHp(), 80);
        player.reduceHp(10);
        assertEquals(player.getHp(), 70);
        player.reduceHp(-10);
        assertEquals(player.getHp(), 70);
    }
}