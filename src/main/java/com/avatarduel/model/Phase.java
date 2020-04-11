package com.avatarduel.model;

import com.avatarduel.ArenaController;
import com.avatarduel.KartuUI;
import com.avatarduel.Rendered;

public class Phase {
    public static Phase INSTANCEPHASE = new Phase();

    public static ArenaController arenaController;
    public Fase fase;

    public static enum Fase {
        DRAW, MAIN, BATTLE, END
    }

    public Phase() {
    }

    /**
     * @return the fase
     */
    public Fase getFase() {
        return fase;
    }

    public void setController(Rendered arenaController) {
        Phase.arenaController = (ArenaController) arenaController;
    }

    public void startGame() {
        System.out.println(":");
        drawPhase();
    }

    public static Phase getInstancePhase() {
        return INSTANCEPHASE;
    }

    public void drawPhase() {
        fase = Fase.DRAW;
        // 2. Power player di reset
        GameState.getInstance().getCurrentPlayer().resetPower();
        arenaController.changePhasePosition(273);
        arenaController.getEndTextLabel().setText("END");
        arenaController.getDrawTextLabel().setText("--> DRAW");
        // 1. Player ngambil satu kartu dari deck, taruh di tangan
        GameState.getInstance().getCurrentPlayer().getDeck().takeCardToHand();
        arenaController.render();
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            main1Phase();
        });
    }

    public void main1Phase() {
        GameState.getInstance().getCurrentPlayer().setTakeLand(true);
        fase = Fase.MAIN;
        arenaController.render();
        // 1. meletakkan 0 atau lebih kartu karakter (bertarung/bertahan)
        // karakter yg baru diletakkan tidak dapat bertarung di battle phase
        // 2. mengubah posisi kartu CHAR di field
        // 3. meletakkan MAKS. 1 kartu land
        // 4. meletakkan 0 atau lebih kartu SKILL, memilih karakter yg ditargetkan
        // 5. membuang 0 atau lebih kartu SKILL
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            battlePhase();
        });
        arenaController.changePhasePosition(300);
        arenaController.getDrawTextLabel().setText("DRAW");
        arenaController.getMainTextLabel().setText("--> MAIN");
    }

    public void battlePhase() {
        fase = Fase.BATTLE;
        arenaController.render();
        // 1. player dapat menggunakan CHAR untuk menyerang CHAR lawan/HP lawan
        // 2. kalo ada CHAR di area lawan, gabisa langsung nyerang HP
        // 3. tiap CHAR nyerang maks 1 kali

        // NYERANG CHAR LAWAN:
        // CHAR lawan posisi menyerang
        // 1. Attack lawan <= Attack CHAR
        // 2. Setelah menyerang, char lawan mati
        // 3. Selisih ATTACK dikenakan ke HP lawan
        // CHAR lawan posisi bertahan
        // 1. Defense lawan <= Attack CHAR
        // 2. Setelah menyerang, char lawan mati
        // 3. Tidak ada HP yang berkurang
        // validasi trus manggil action(character)
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            // Setelah beres battle phase kembalikan variabel battle dari kartuUI ke nilai"
            // defaultnya
            KartuUI.setPowerAttacked(9999);
            KartuUI.setPowerAttack(9999);
            // hapus game message sblmnya
            Phase.arenaController.setGameMessage("");
            endPhase();
        });
        arenaController.changePhasePosition(327);
        arenaController.getMainTextLabel().setText("MAIN");
        arenaController.getBattleTextLabel().setText("--> BATTLE");

    }

    // public void main2Phase(){
    // fase = Fase.MAIN2;
    // // 1. mainPhase1
    // // 2. CHAR yang baru saja menyerang tidak dapat diubah posisi
    // arenaController.getEndPhase().setOnMouseClicked(el -> {
    // endPhase();
    // });
    // arenaController.changePhasePosition(354);
    // arenaController.getBattleTextLabel().setText("BATTLE");
    // arenaController.getMain2TextLabel().setText("--> MAIN 2");
    // }

    public void endPhase() {
        fase = Fase.END;
        // mengakhiri giliran
        arenaController.getEndPhase().setOnMouseClicked(el -> {
            GameState.getInstance().nextPlayer();
            drawPhase();

        });
        arenaController.changePhasePosition(354);
        arenaController.getBattleTextLabel().setText("BATTLE");
        arenaController.getEndTextLabel().setText("--> END");
    }

    public void nextPhase() {

    }
}