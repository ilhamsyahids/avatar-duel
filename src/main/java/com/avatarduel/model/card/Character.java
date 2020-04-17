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

    /**
     * Contructor
     * @param name name, super
     * @param description description, super
     * @param element element, super
     * @param imagePath imagePath, super
     * @param attack the attack of this character
     * @param defense the defense of this character
     * @param power the power, implements from Powerable
     */
    public Character(String name, String description, Element element, String imagePath, int attack, int defense,
            int power) {
        super(name, description, element, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        mode = Mode.ATTACK;
        isAttackThisTurn = false;
    }

    /**
     * Set is can attack this turn
     * @param val value
     */
    public void setIsAttackThisTurn(boolean val) {
        this.isAttackThisTurn = val;
    }

    /**
     * @return isAttackThisTurn
     */
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

        myAttack = Math.max(0, myAttack);
        enemyAttack = Math.max(0, enemyAttack);

        if (character.getMode() == Mode.DEFENSE) {
            if (isPowerUp) {
                if (myAttack > enemyAttack) {
                    character.removeSkills();
                    GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(character);
                    GameState.getInstance().getOtherPlayer().reduceHp(myAttack - enemyAttack);
                    Phase.arenaController.setGameMessage("Serangan berhasil");
                } else {
                    Phase.arenaController.setGameMessage("Serangan gagal");
                }
            } else {
                if (myAttack > enemyDefense) {
                    character.removeSkills();
                    GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(character);
                    Phase.arenaController.setGameMessage("Serangan berhasil");
                } else {
                    Phase.arenaController.setGameMessage("Serangan gagal");
                }
            }
        } else {
            if (myAttack > enemyAttack) {
                character.removeSkills();
                GameState.getInstance().getOtherPlayer().getDeck().getCharacters().remove(character);
                GameState.getInstance().getOtherPlayer().reduceHp(myAttack - enemyAttack);
                Phase.arenaController.setGameMessage("Serangan berhasil");
            } else {
                Phase.arenaController.setGameMessage("Serangan gagal");
            }
        }

        this.setIsAttackThisTurn(true);
    }

    /**
     * Attack directly on Player
     * @param P the player
     */
    public void attackOnPlayer(Player P) {
        int myAttack = getAttack();
        for (Skill skill : getCharSkills()) {
            if (skill instanceof SkillAura) {
                myAttack += ((SkillAura) skill).getAttack();
            }
        }
        P.reduceHp(myAttack);
    }

    /**
     * Add skill to character
     * @param skill the skill
     */
    public void addSkills(Skill skill) {
        charSkills.add(skill);
    }

    /**
     * Remove all skills that attach character in arena
     */
    public void removeSkills() {
        getCharSkills().forEach(item -> {
            if (!GameState.getInstance().getCurrentPlayer().getDeck().getSkills().remove(item)) {
                GameState.getInstance().getOtherPlayer().getDeck().getSkills().remove(item);
            }
        });
    }
}
