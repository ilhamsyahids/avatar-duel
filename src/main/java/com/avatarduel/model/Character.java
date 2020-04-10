package com.avatarduel.model;

import java.util.ArrayList;

public class Character extends Card implements Powerable {
    private int attack;
    private int defense;
    private int power;
    private Mode mode;
    private ArrayList<Skill> charSkills = new ArrayList<>();

    public Character(String name, String description, Element element, String imagePath, int attack, int defense, int power) {
        super(name, description, element, imagePath, Mode.ATTACK);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        mode = Mode.ATTACK;
    }
    
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setMode(Mode m){
        mode = m;
    }

    public Mode getMode(){
        return mode;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getPower() {
        return power;
    }
    @Override
    public boolean isCanSummon(){
        int power = (int)GameState.getInstance().getCurrentPlayer().getMapPower().get(getElement()).getY();
        // powerChar<=power -> true
        return(getPower()<=power);
        
    }
    @Override
    public void action(Character character) {
        // character.destroy();
        // if (character.mode == Mode.ATTACK && character.getAttack() < attack) {
        //     GameState.getInstance().getOtherPlayer().reduceHp(attack - character.getAttack());
        // }
    }

    public void destroy() {
        super.setMode(Mode.DESTROY);
        //super.mode = Mode.DESTROY;
    }
    public ArrayList<Skill> getCharSkills() {
        return charSkills;
    }
    public void addToCharSkills(Skill skill){
        // add to list
        charSkills.add(skill);
        // action
        skill.action(this);
    }
}
