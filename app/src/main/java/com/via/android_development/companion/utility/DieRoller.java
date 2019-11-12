package com.via.android_development.companion.utility;

import java.util.Random;

public class DieRoller {

    private static DieRoller instance;
    private final Random random;

    private DieRoller() {
        random = new Random();
    }

    public static DieRoller getInstance() {
        if (instance == null)
            instance = new DieRoller();
        return instance;
    }

    public int rollDie(int facets) {
        return random.nextInt(facets) + 1;
    }

    public int rollDie(int facets, int modifier) {
        return random.nextInt(facets) + 1 + modifier;
    }

    public int rollDice(int[] diceFacets) {
        int result = 0;
        for (int facet : diceFacets) result += rollDie(facet);
        return result;
    }

    public int rollDice(int[] diceFacets, int[] modifiers) {
        int modifierSum = 0;
        for (int modifier : modifiers) modifierSum += modifier;
        return rollDice(diceFacets) + modifierSum;
    }
}
