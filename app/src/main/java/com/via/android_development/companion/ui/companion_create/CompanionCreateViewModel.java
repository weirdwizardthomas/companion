package com.via.android_development.companion.ui.companion_create;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.repository.CompanionRepository;
import com.via.android_development.companion.utility.Alignment;
import com.via.android_development.companion.utility.Profession;
import com.via.android_development.companion.utility.Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.via.android_development.companion.R.string.attributeCharisma;
import static com.via.android_development.companion.R.string.attributeConstitution;
import static com.via.android_development.companion.R.string.attributeDexterity;
import static com.via.android_development.companion.R.string.attributeIntelligence;
import static com.via.android_development.companion.R.string.attributeStrength;
import static com.via.android_development.companion.R.string.attributeWisdom;

public class CompanionCreateViewModel extends AndroidViewModel {

    public static final int DEFAULT_RACE_INDEX = 8;
    public static final int DEFAULT_PROFESSION_INDEX = 5;
    public static final int DEFAULT_ALIGNMENT_INDEX = 5;

    private CompanionRepository companionRepository;

    public CompanionCreateViewModel(Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
    }

    public void insert(Companion companion) {
        companionRepository.insert(companion);
    }

    public Companion getCompanionById(int id) {
        return companionRepository.getCompanionById(id);
    }

    public LiveData<List<Companion>> getAllCompanions() {
        return companionRepository.getAllCompanions();
    }

    public static List<Integer> getAllAttributesIds() {
        return new ArrayList<>(Arrays.asList(
                attributeStrength,
                attributeDexterity,
                attributeConstitution,
                attributeIntelligence,
                attributeWisdom,
                attributeCharisma));

    }

    public static String[] getAllRaces() {
        List<String> races = new ArrayList<>();
        for (Race race : Race.values()) races.add(race.toString());
        return races.toArray(new String[0]);
    }

    public static String[] getAllProfessions() {
        List<String> professions = new ArrayList<>();
        for (Profession profession : Profession.values()) professions.add(profession.toString());
        return professions.toArray(new String[0]);
    }

    public static String[] getAllAlignments() {
        List<String> alignments = new ArrayList<>();
        for (Alignment alignment : Alignment.values()) alignments.add(alignment.toString());
        return alignments.toArray(new String[0]);
    }
}
