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
        assertEquals(1, ch.getCharSkills().size());
    }

    @Test
    public void getAttack() {
        assertEquals(1, sp.getAttack());
    }

    @Test
    public void getDefense() {
        assertEquals(3, sp.getDefense());
    }
}