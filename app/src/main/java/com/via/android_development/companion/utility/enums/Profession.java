package com.via.android_development.companion.utility.enums;

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


    public String toString() {
        return stringValue;
    }
}
