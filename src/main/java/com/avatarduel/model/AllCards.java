package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Collections;

public class AllCards {
    private static AllCards INSTANCE = new AllCards();
    private ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<Character> allCharacters = new ArrayList<>();
    private ArrayList<Skill> allSkills = new ArrayList<>();
    private ArrayList<Land> allLands = new ArrayList<>();

    AllCards() {
    }

    public static AllCards getInstance() {
        return INSTANCE;
    }

    public static void addLand(Land Land) {
        getInstance().allLands.add(Land);
    }

    public static void addSkill(Skill skill) {
        getInstance().allSkills.add(skill);
    }

    public static void addCharacter(Character character) {
        getInstance().allCharacters.add(character);
    }

    public ArrayList<Character> getAllCharacters() {
        return allCharacters;
    }

    public ArrayList<Land> getAllLands() {
        return allLands;
    }

    public ArrayList<Skill> getAllSkills() {
        return allSkills;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void addAll() {
        getInstance().allCards.clear();
        getInstance().allCards.addAll(getInstance().getAllCharacters());
        getInstance().allCards.addAll(getInstance().getAllSkills());
        getInstance().allCards.addAll(getInstance().getAllLands());
        Collections.shuffle(getInstance().allCards);
    }
}