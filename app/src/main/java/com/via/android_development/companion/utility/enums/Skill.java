package com.via.android_development.companion.utility.enums;

import androidx.annotation.NonNull;

public enum Skill {
    ATHLETICS("Athletics"),
    ACROBATICS("Acrobatics"),
    SLEIGHT_OF_HAND("Sleight of hand"),
    STEALTH("Stealth"),
    ARCANA("Arcana"),
    HISTORY("History"),
    INVESTIGATION("Investigation"),
    NATURE("Nature"),
    RELIGION("Religion"),
    ANIMAL_HANDLING("Animal handling"),
    INSIGHT("Insight"),
    MEDICINE("Medicine"),
    PERCEPTION("Perception"),
    SURVIVAL("Survival"),
    DECEPTION("Deception"),
    INTIMIDATION("Intimidation"),
    PERFORMANCE("Performance"),
    PERSUASION("Persuasion");

    private final String stringValue;

    Skill(final String s) {
        stringValue = s;
    }


    @NonNull
    public String toString() {
        return stringValue;
    }
}
