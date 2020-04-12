package com.avatarduel.model;

import com.avatarduel.view.ArenaController;
import com.avatarduel.view.CardUI;
import com.avatarduel.view.Rendered;

public class Phase {
    public static Phase INSTANCEPHASE = new Phase();

    public static ArenaController arenaController;
    public Fase fase;

    /**
     * Contructors
     */
    public Phase() {
        // Do Nothing
    }

    /**
     * Type of Phase
     */
    public static enum Fase {
        DRAW, MAIN, BATTLE, END
    }

    /**
     * @return the fase
     */
    public Fase getFase() {
        return fase;
    }

    /**
     * Set UI controller for render
     * 
     * @param arenaController rendered class
     */
    public void setController(Rendered arenaController) {
        Phase.arenaController = (ArenaController) arenaController;
    }

    public void startGame() {
        drawPhase();
    }

    public static Phase getInstancePhase() {
        return INSTANCEPHASE;
    }

    public void drawPhase() {
        fase = Fase.DRAW;
        GameState.getInstance().getCurrentPlayer().getDeck().takeCardToHand();

        arenaController.changePhasePosition(273);
        arenaController.getEndTextLabel().setText("END");
        arenaController.getDrawTextLabel().setText("--> DRAW");

        arenaController.getEndPhase().setOnMouseClicked(el -> {
            mainPhase();
        });
        arenaController.render();
    }

    public void mainPhase() {
        fase = Fase.MAIN;
        GameState.getInstance().getCurrentPlayer().setTakeLand(true);

        arenaController.changePhasePosition(300);
        arenaController.getDrawTextLabel().setText("DRAW");
        arenaController.getMainTextLabel().setText("--> MAIN");

        arenaController.getEndPhase().setOnMouseClicked(el -> {
            battlePhase();
        });
        arenaController.render();
    }

    public void battlePhase() {
        fase = Fase.BATTLE;

        arenaController.changePhasePosition(327);
        arenaController.getMainTextLabel().setText("MAIN");
        arenaController.getBattleTextLabel().setText("--> BATTLE");

        arenaController.getEndPhase().setOnMouseClicked(el -> {
            CardUI.setPowerAttacked(9999);
            CardUI.setPowerAttack(9999);
            Phase.arenaController.setGameMessage("");
            endPhase();
        });
        arenaController.render();
    }

    public void endPhase() {
        fase = Fase.END;

        arenaController.changePhasePosition(354);
        arenaController.getBattleTextLabel().setText("BATTLE");
        arenaController.getEndTextLabel().setText("--> END");

        arenaController.getEndPhase().setOnMouseClicked(el -> {
            GameState.getInstance().nextPlayer();
            arenaController.switchPlayerName();
            drawPhase();
        });
    }
}