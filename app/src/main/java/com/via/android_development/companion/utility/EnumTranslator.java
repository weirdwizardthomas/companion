package com.via.android_development.companion.utility;

import com.via.android_development.companion.utility.enums.Alignment;
import com.via.android_development.companion.utility.enums.Profession;
import com.via.android_development.companion.utility.enums.Race;

import java.util.ArrayList;
import java.util.List;

public class EnumTranslator {

    public static String[] getAllRaces() {
        List<String> races = new ArrayList<>();
        for (Race race : Race.values())
            races.add(race.toString());
        return races.toArray(new String[0]);
    }

    public static String[] getAllProfessions() {
        List<String> professions = new ArrayList<>();
        for (Profession profession : Profession.values())
            professions.add(profession.toString());
        return professions.toArray(new String[0]);
    }

    public static String[] getAllAlignments() {
        List<String> alignments = new ArrayList<>();
        for (Alignment alignment : Alignment.values())
            alignments.add(alignment.toString());
        return alignments.toArray(new String[0]);
    }

    public static List<String> getAllRacesList() {
        List<String> races = new ArrayList<>();
        for (Race race : Race.values())
            races.add(race.toString());
        return races;
    }

    public static List<String> getAllProfessionsList() {
        List<String> professions = new ArrayList<>();
        for (Profession profession : Profession.values())
            professions.add(profession.toString());
        return professions;
    }

    public static List<String> getAllAlignmentsList() {
        List<String> alignments = new ArrayList<>();
        for (Alignment alignment : Alignment.values())
            alignments.add(alignment.toString());
        return alignments;
    }
}
