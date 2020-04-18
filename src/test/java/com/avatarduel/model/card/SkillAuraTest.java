package com.avatarduel.model.card;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SkillAuraTest {
    SkillAura sp = new SkillAura("", "", Element.AIR, "", 1, 3, 2);
    Character ch = new Character("", "", Element.AIR, "", 1, 1, 1);

    @Test
    public void action() {
        assertEquals(ch.getCharSkills(), new ArrayList<Skill>());
        sp.action(ch);
        assertTrue(ch.getCharSkills().size() == 1);
    }

    @Test
    public void getAttack() {
        assertTrue(sp.getAttack() == 1);
    }

    @Test
    public void getDefense() {
        assertTrue(sp.getDefense() == 3);
    }
}