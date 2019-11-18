package com.via.android_development.companion.utility.enums;

public enum Race {
    DRAGONBORN("Dragonborn"),
    DWARF("Dwarf"),
    ELF("Elf"),
    GNOME("Gnome"),
    HALF_ELF("Half Elf"),
    HALFLING("Halfling"),
    HALF_ORC("Half Orc"),
    HUMAN("Human"),
    TIEFLING("Tiefling");

    private final String stringValue;

    Race(final String s) {
        stringValue = s;
    }


    public String toString() {
        return stringValue;
    }
}
