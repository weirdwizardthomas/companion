package com.via.android_development.companion.ui.companion_create;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.local.CompanionRepository;
import com.via.android_development.companion.utility.EnumTranslator;

import java.util.List;

class CompanionCreateViewModel extends AndroidViewModel {

    public static final int DEFAULT_RACE_INDEX = 8;
    public static final int DEFAULT_PROFESSION_INDEX = 5;
    public static final int DEFAULT_ALIGNMENT_INDEX = 5;

    private final CompanionRepository companionRepository;

    public CompanionCreateViewModel(Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
    }

    public void insert(Companion companion) {
        companionRepository.insert(companion);
    }

    public LiveData<List<Companion>> getAllCompanions() {
        return companionRepository.getAllCompanions();
    }

    public void deleteAllCompanions() {
        companionRepository.deleteAllCompanions();
    }

    public void update(Companion companion) {
        companionRepository.update(companion);
    }

    public static String[] getAllRaces() {
        return EnumTranslator.getAllRaces();
    }

    public static String[] getAllProfessions() {
        return EnumTranslator.getAllProfessions();
    }

    public static String[] getAllAlignments() {
        return EnumTranslator.getAllAlignments();
    }

    public static String getRaceByIndex(int index) {
        return EnumTranslator.getAllRaces()[index];
    }

    public static String getProfessionByIndex(int index) {
        return EnumTranslator.getAllProfessions()[index];
    }

    public static String getAlignmentByIndex(int index) {
        return EnumTranslator.getAllAlignments()[index];
    }
}
