package com.via.android_development.companion.utility;

public class StatCalculator {

    public static int proficiencyBonus(int totalLevel) {
        if (totalLevel <= 4)
            return 2;
        if (totalLevel <= 8)
            return 3;
        if (totalLevel <= 12)
            return 4;
        if (totalLevel <= 16)
            return 5;
        return 6;
    }

    public static int abilityModifier(int abilityLevel) {
        return (abilityLevel - 10) / 2;
    }

    public static int passiveAbility(int abilityLevel) {
        return 10 + abilityModifier(abilityLevel);
    }

    public static String abilityModifierAsString(int abilityLevel) {
        int modifier = abilityModifier(abilityLevel);
        return (modifier >= 0 ? "+" : "") + modifier;
    }
}
