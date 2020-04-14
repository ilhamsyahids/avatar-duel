package com.avatarduel.model.card;

import com.avatarduel.model.GameState;

import java.util.ArrayList;

public class Character extends Card implements Powerable {
    private int attack;
    private int defense;
    private int power;
    private Mode mode;
    private ArrayList<Skill> charSkills = new ArrayList<>();
    private boolean isAttackThisTurn;

    public Character(String name, String description, Element element, String imagePath, int attack, int defense,
            int power) {
        super(name, description, element, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        mode = Mode.ATTACK;
        isAttackThisTurn = false;
    }

    public void setIsAttackThisTurn(boolean val) {
        this.isAttackThisTurn = val;
    }

    public boolean isAttackThisTurn() {
        return this.isAttackThisTurn;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return the defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * @param defense the defense to set
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * @return the mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * @return the charSkills
     */
    public ArrayList<Skill> getCharSkills() {
        return charSkills;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public boolean isCanSummon() {
        int power = GameState.getInstance().getCurrentPlayer().getValuePower(getElement());
        return (getPower() <= power);

    }

    @Override
    public void action(Character character) {
        // character.destroy();
        // if (character.mode == Mode.ATTACK && character.getAttack() < attack) {
        // GameState.getInstance().getOtherPlayer().reduceHp(attack -
        // character.getAttack());
        // }
    }

    public void addSkills(Skill skill) {
        // add to list
        charSkills.add(skill);
        // action
        skill.action(this);
    }
}
