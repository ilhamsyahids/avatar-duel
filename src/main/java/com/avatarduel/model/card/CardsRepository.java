package com.avatarduel.model.card;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.avatarduel.util.CSVReader;

public class CardsRepository {
    private static CardsRepository instance = new CardsRepository();
    private ArrayList<Card> allCards = new ArrayList<>();

    /**
     * Constructor
     */
    CardsRepository() {
    }

    /**
     * @return the instance of singleton class
     */
    public static CardsRepository getInstance() {
        return instance;
    }

    /**
     * Add card with specific subclass of Card
     * 
     * @param <T>  Subclass of Card
     * @param card Only accept Character, Land, SkillAura, SkillDestroy, or
     *             SkillPowerUp
     * @param url  URL path data of CSV file
     * @throws IOException
     */
    public static <T extends Card> void addCard(Class<T> card, String url) throws IOException {
        File fileCSV = new File(url);
        CSVReader cardReader = new CSVReader(fileCSV, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> cardRows = cardReader.read();
        Card cardObject = new Card() {
            @Override
            public void action(Character character) {
            }
        };
        for (String[] item : cardRows) {
            if (card.equals(Character.class)) {
                cardObject = new Character(item[1], item[3], Element.valueOf(item[2]), item[4],
                        Integer.parseInt(item[5]), Integer.parseInt(item[6]), Integer.parseInt(item[7]));
            } else if (card.equals(Land.class)) {
                cardObject = new Land(item[1], item[3], Element.valueOf(item[2]), item[4]);
            } else if (card.equals(SkillAura.class)) {
                cardObject = new SkillAura(item[1], item[3], Element.valueOf(item[2]), item[4],
                        Integer.parseInt(item[6]), Integer.parseInt(item[7]), Integer.parseInt(item[5]));
            } else if (card.equals(SkillDestroy.class)) {
                cardObject = new SkillDestroy(item[1], item[3], Element.valueOf(item[2]), item[4],
                        Integer.parseInt(item[5]));
            } else if (card.equals(SkillPowerUp.class)) {
                cardObject = new SkillPowerUp(item[1], item[3], Element.valueOf(item[2]), item[4],
                        Integer.parseInt(item[5]));
            }
            getInstance().getAllCards().add(cardObject);
        }
    }

    /**
     * Get Array of cards
     * 
     * @return Array of cards
     */
    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    /**
     * Get random card
     * 
     * @return the random card
     */
    public Card getCard() {
        int idx = new Random().nextInt(allCards.size());
        return allCards.get(idx);
    }
}