package com.via.android_development.companion.utility.enums;

import androidx.annotation.NonNull;

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


    @NonNull
    public String toString() {
        return stringValue;
    }
}
