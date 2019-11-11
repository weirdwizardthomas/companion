package com.via.android_development.companion.utility;

public enum Alignment {
    LAWFUL_GOOD("Lawful good"),
    NEUTRAL_GOOD("Neutral good"),
    CHAOTIC_GOOD("Chaotic good"),
    LAWFUL_NEUTRAL("Lawful neutral"),
    TRUE_NEUTRAL("True neutral"),
    CHAOTIC_NEUTRAL("Chaotic neutral"),
    LAWFUL_EVIL("Lawful evil"),
    NEUTRAL_EVIL("Neutral evil"),
    CHAOTIC_EVIL("Chaotic evil");

    private final String stringValue;

    Alignment(final String s) {
        stringValue = s;
    }


    public String toString() {
        return stringValue;
    }

}
