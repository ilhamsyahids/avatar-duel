package com.avatarduel.model;

import com.avatarduel.ArenaController;
import com.avatarduel.KartuUI;

public class Phase {
    public static Phase INSTANCEPHASE = new Phase();

    public ArenaController arenaController;
    public Fase fase;

    public static enum Fase {
        DRAW,
        MAIN1,
        BATTLE,
        MAIN2,
        END
    }

    public Phase() {}

    public void setController(ArenaController arenaController) {
      this.arenaController = arenaController;
    }
    
    public void startGame() {
        System.out.println(":");
        drawPhase();
    }
    
    public static Phase getInstancePhase() {
        return INSTANCEPHASE;
    }

    public void drawPhase(){
        fase = Fase.DRAW;
        // 2. Power player di reset
        GameState.getInstance().getCurrentPlayer().resetPower();
        // 1. Player ngambil satu kartu dari deck, taruh di tangan
        GameState.getInstance().getCurrentPlayer().getDeck().takeCardToHand();
        arenaController.renderCardDraw();
        arenaController.getButtonPhase().setOnMouseClicked(el -> {
            main1Phase();
        });
    }
    
    public void main1Phase() {
        fase = Fase.MAIN1;
        arenaController.renderCardMain();
        // 1. meletakkan 0 atau lebih kartu karakter (bertarung/bertahan)
        // karakter yg baru diletakkan tidak dapat bertarung di battle phase
        // 2. mengubah posisi kartu CHAR di field
        // 3. meletakkan MAKS. 1 kartu land
        // 4. meletakkan 0 atau lebih kartu SKILL, memilih karakter yg ditargetkan
        // 5. membuang 0 atau lebih kartu SKILL
        arenaController.getButtonPhase().setOnMouseClicked(el -> {
            battlePhase();
        });
    }
    
    public void battlePhase(){
        fase = Fase.BATTLE;
        arenaController.renderCardBattle();
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
        arenaController.getButtonPhase().setOnMouseClicked(el -> {
            main2Phase();
        });
    }
    
    public void main2Phase(){
        fase = Fase.MAIN2;
        // 1. mainPhase1
        // 2. CHAR yang baru saja menyerang tidak dapat diubah posisi
        arenaController.getButtonPhase().setOnMouseClicked(el -> {
            endPhase();
        });
    }
    
    public void endPhase(){
        fase = Fase.END;
        // mengakhiri giliran
        arenaController.getButtonPhase().setOnMouseClicked(el -> {
            GameState.getInstance().nextPlayer();
            drawPhase();
        });
    }

    public void nextPhase() {

    }
}