package com.via.android_development.companion.utility.enums;

import androidx.annotation.NonNull;

public enum Profession {
    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String stringValue;

    Profession(final String s) {
        stringValue = s;
    }


    @NonNull
    public String toString() {
        return stringValue;
    }
}
