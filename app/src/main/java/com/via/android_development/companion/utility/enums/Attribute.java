package com.via.android_development.companion.utility.enums;

import androidx.annotation.NonNull;

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

    @NonNull
    public String toString() {
        return stringValue;
    }

}
