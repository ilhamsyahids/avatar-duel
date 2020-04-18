package com.avatarduel.model;

import org.junit.Before;
import org.junit.Test;

import com.avatarduel.view.ArenaController;

import static org.junit.Assert.*;

public class PhaseTest {

    @Before
    public void setUp() throws Exception {
        new Phase();
    }

    @Test
    public void getFase() {
        assertNull(Phase.getInstancePhase().getFase());
        Phase.getInstancePhase().fase = Phase.Fase.DRAW;
        assertEquals(Phase.getInstancePhase().getFase(), Phase.Fase.DRAW);
    }

    @Test
    public void setController() {
        assertNull(Phase.arenaController);
        Phase.getInstancePhase().setController(new ArenaController());
        assertNotNull(Phase.arenaController);
    }
}