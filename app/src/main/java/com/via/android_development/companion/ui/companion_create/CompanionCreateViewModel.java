package com.via.android_development.companion.ui.companion_create;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.local.CompanionRepository;
import com.via.android_development.companion.ui.companion.CompanionFragment;
import com.via.android_development.companion.utility.EnumTranslator;

import java.util.List;

public class CompanionCreateViewModel extends AndroidViewModel {

    public static final int DEFAULT_RACE_INDEX = 8;
    public static final int DEFAULT_PROFESSION_INDEX = 5;
    public static final int DEFAULT_ALIGNMENT_INDEX = 5;

    private final CompanionRepository companionRepository;
    private Companion adventurer;

    public CompanionCreateViewModel(Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
        adventurer = new Companion();
    }

    public void deleteAllCompanions() {
        companionRepository.deleteAllCompanions();
    }

    public static String getAlignmentByIndex(int index) {
        return EnumTranslator.getAllAlignments()[index];
    }

    public static String[] getAllProfessions() {
        return EnumTranslator.getAllProfessions();
    }

    public static String[] getAllRaces() {
        return EnumTranslator.getAllRaces();
    }

    public static String[] getAllAlignments() {
        return EnumTranslator.getAllAlignments();
    }

    public static String getRaceByIndex(int index) {
        return EnumTranslator.getAllRaces()[index];
    }

    public Companion getAdventurer() {
        return adventurer;
    }

    public static String getProfessionByIndex(int index) {
        return EnumTranslator.getAllProfessions()[index];
    }

    public void setAdventurer(Companion adventurer) {
        this.adventurer = adventurer;
    }

    public LiveData<List<Companion>> getAllCompanions() {
        return companionRepository.getAllCompanions();
    }

    public void insert(Companion adventurer) {
        companionRepository.insert(adventurer);
    }

    public void saveToFirebase() {
        FirebaseCompanion firestoreAdventurer = new FirebaseCompanion(adventurer);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CompanionFragment.COLLECTION_NAME)
                .document(String.valueOf(firestoreAdventurer.getId()))
                .set(firestoreAdventurer);
    }

    public void update(Companion adventurer) {
        companionRepository.update(adventurer);
    }
}
