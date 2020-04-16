package com.avatarduel.model.card;

import com.avatarduel.model.GameState;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;

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
        boolean isPowerUp = false;
        int myAttack = getAttack();
        int enemyAttack = character.getAttack();
        int enemyDefense = character.getDefense();

        for (Skill skill : getCharSkills()) {
            if (skill instanceof SkillPowerUp) {
                isPowerUp = true;
            } else if (skill instanceof SkillAura) {
                myAttack += ((SkillAura) skill).getAttack();
            }
        }

        for (Skill skill : character.getCharSkills()) {
            if (skill instanceof SkillPowerUp) {
                isPowerUp = true;
            } else if (skill instanceof SkillAura) {
                enemyAttack += ((SkillAura) skill).getAttack();
                enemyDefense += ((SkillAura) skill).getDefense();
            }
        }

        if (isPowerUp) {
            if (myAttack > enemyAttack) {
                GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(character);
                GameState.getInstance().getOtherPlayer().reduceHp(myAttack - enemyAttack);
                Phase.arenaController.setGameMessage("Serangan berhasil");
            } else {
                Phase.arenaController.setGameMessage("Serangan gagal");
            }
        } else {
            if (myAttack > enemyDefense) {
                GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(character);
                Phase.arenaController.setGameMessage("Serangan berhasil");
            } else {
                Phase.arenaController.setGameMessage("Serangan gagal");
            }
        }

        this.setIsAttackThisTurn(true);
    }

    public void attackOnPlayer(Player P) {
        int myAttack = getAttack();
        for (Skill skill : getCharSkills()) {
            if (skill instanceof SkillAura) {
                myAttack += ((SkillAura) skill).getAttack();
            }
        }
        P.reduceHp(myAttack);
    } 

    public void addSkills(Skill skill) {
        // add to list
        charSkills.add(skill);
        // action
        // skill.action(this);
    }
}
