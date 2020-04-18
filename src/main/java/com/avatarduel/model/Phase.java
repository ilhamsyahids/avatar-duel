package com.avatarduel.model;

import com.avatarduel.view.ArenaController;
import com.avatarduel.view.CardUI;
import com.avatarduel.view.Rendered;
import javafx.scene.text.Font;

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

    /**
     * Function that is called once to start the game for the first time
     */
    public void startGame() {
        drawPhase();
    }

    /**
     * Return single instance of this(Phase) class
     *
     * @return INSTANCEPHASE
     */
    public static Phase getInstancePhase() {
        return INSTANCEPHASE;
    }

    /**
     * Set atribute "fase" to FASE.DRAW and adjust the UI in arenaController according to its new fase
     */
    public void drawPhase() {
        fase = Fase.DRAW;
        GameState.getInstance().getCurrentPlayer().getDeck().takeCardToHand();
        // direct attack akan visible diatur di button attack di kelas CardUI
        arenaController.getDirectAttack().setVisible(false);
        arenaController.changePhasePosition(273);
        arenaController.getEndTextLabel().setText("END");
        arenaController.getDrawTextLabel().setText("--> DRAW");
        
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            mainPhase();
        });
        arenaController.render();
    }

    /**
     * Set atribute "fase" to FASE.MAIN and adjust the UI in arenaController according to its new fase
     */
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

    /**
     * Set atribute "fase" to FASE.BATTLE and adjust the UI in arenaController according to its new fase
     */
    public void battlePhase() {
        fase = Fase.BATTLE;

        arenaController.changePhasePosition(327);
        arenaController.getMainTextLabel().setText("MAIN");
        arenaController.getBattleTextLabel().setText("--> BATTLE");
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            CardUI.setThereIsCardAttack(false);
            Phase.arenaController.setGameMessage("");
            // set semua kartu current player di field udh bsa attack lagi
            GameState.getInstance().getCurrentPlayer().getDeck().getCharacters().forEach(item -> {
                item.setIsAttackThisTurn(false);
            });
            endPhase();
        });
        arenaController.render();
    }
    /**
     * Set atribute "fase" to FASE.BATTLE and adjust the UI in arenaController according to its new fase
     */
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