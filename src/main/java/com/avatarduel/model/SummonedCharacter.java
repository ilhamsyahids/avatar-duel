package com.avatarduel.model;

public class SummonedCharacter implements ISummoned {
    private Character character;
    private boolean open;
    private boolean inAttack;

    SummonedCharacter(Character M, boolean buka, boolean attack) {
        character = M;
        open = buka;
        inAttack = attack;
    }

    @Override
    public boolean flip() {
        if (!open) {
            open = true;
            return true;
        }
        return false;
    }

    @Override
    public void rotate() {
        inAttack = !inAttack;
    }

    @Override
    public int getPositionValue() {
        if (inAttack) return character.getAttack();
        else return character.getDefense();
    }

    @Override
    public void render() {
        System.out.println(String.format("Character %s dalam keadaan %s dengan posisi %s", character.getName(), (open ? "terbuka" : "tertutup"), (inAttack ? "menyerang" : "bertahan")));
    }
}
