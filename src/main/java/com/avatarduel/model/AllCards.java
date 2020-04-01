package com.avatarduel.model;

import java.util.ArrayList;

public class AllCards
{  
    private static AllCards INSTANCE = new AllCards();
    private ArrayList<Card> allCards = new ArrayList<>();
  
    private AllCards() {}

    public static AllCards getInstance()
    { 
        return INSTANCE; 
    }

    public static void add(Card card) {
      getInstance().allCards.add(card);
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }
} 