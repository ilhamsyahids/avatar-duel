package com.avatarduel.model.card;

import org.junit.Before;
import org.junit.Test;

import com.avatarduel.model.card.Character;

import static org.junit.Assert.*;

public class CharacterTest {
    private Character charr;

    @Before
    public void setUp() throws Exception {
        charr = new Character("Ilham", "Ini deskripsi", Element.EARTH, "path", 0, 2, 1);
    }

    @Test
    public void getAttack() {
        assertEquals(charr.getAttack(), 0);
    }

    @Test
    public void setAttack() {
        charr.setAttack(10);
        assertNotEquals(charr.getAttack(), 0);
        assertEquals(charr.getAttack(), 10);
    }

    @Test
    public void getDefense() {
        assertEquals(charr.getDefense(), 2);
    }

    @Test
    public void setDefense() {
        charr.setDefense(5);
        assertNotEquals(charr.getDefense(), 2);
        assertEquals(charr.getDefense(), 5);
    }

    @Test
    public void getPower() {
        assertEquals(charr.getPower(), 1);
    }

}