package com.avatarduel.model.card;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SkillPowerUpTest {
    @Test
    public void action() {
        SkillPowerUp sp = new SkillPowerUp("", "", Element.AIR, "", 1);
        Character ch = new Character("", "", Element.AIR, "", 1, 1, 1);
        assertEquals(ch.getCharSkills(), new ArrayList<Skill>());
        sp.action(ch);
        assertTrue(ch.getCharSkills().size() == 1);
    }
}