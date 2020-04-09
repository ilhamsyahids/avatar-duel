package com.avatarduel.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

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
        assertTrue(mapPower.equals(player.getMapPower()));

        player.addPower(Element.AIR, 3);
        assertEquals((int)player.getMapPower().get(Element.AIR).getX(), 3);
        assertEquals((int)player.getMapPower().get(Element.AIR).getY(), 3);

        player.reducePower(Element.AIR, 2);
        assertEquals((int)player.getMapPower().get(Element.AIR).getX(), 3);
        assertEquals((int)player.getMapPower().get(Element.AIR).getY(), 1);

        player.resetPower();
        Map<Element, Point> mapPower2 = new HashMap<>();
        mapPower2.put(Element.AIR, new Point(3, 3));
        mapPower2.put(Element.FIRE, new Point(0, 0));
        mapPower2.put(Element.EARTH, new Point(0, 0));
        mapPower2.put(Element.WATER, new Point(0, 0));
        assertTrue(mapPower2.equals(player.getMapPower()));
    }

    @Test
    public void checkHp() {
        assertEquals(player.getHp(), 80);
        player.reduceHp(10);
        assertEquals(player.getHp(), 70);
    }
}