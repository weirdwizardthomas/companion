package com.via.android_development.companion.utility.enums;

public enum Attribute {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    CHARISMA("Charisma");

    private final String stringValue;

    Attribute(final String s) {
        stringValue = s;
    }

    public String toString() {
        return stringValue;
    }

}
