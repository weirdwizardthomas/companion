package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.persistence.firebase.FirestoreQueryLiveData;
import com.via.android_development.companion.utility.StatCalculator;
import com.via.android_development.companion.utility.enums.Attribute;

import java.util.HashMap;
import java.util.Map;

import static com.via.android_development.companion.ui.companion.CompanionFragment.COLLECTION_NAME;

public class CompanionViewModel extends AndroidViewModel {
    private final FirebaseFirestore firestore;
    private final LiveData<Task<DocumentSnapshot>> liveData;
    private FirebaseCompanion companion;

    public CompanionViewModel(Application application, String observedDocumentPath) {
        super(application);
        firestore = FirebaseFirestore.getInstance();
        liveData = new FirestoreQueryLiveData(firestore.collection(COLLECTION_NAME).document(observedDocumentPath));
        companion = new FirebaseCompanion();
    }

    @NonNull
    public LiveData<Task<DocumentSnapshot>> getdataSnapshotLiveData() {
        return liveData;
    }

    public FirebaseCompanion getCompanion() {
        return companion;
    }

    public void setCompanion(FirebaseCompanion companion) {
        this.companion = companion;
    }


    public Map<String, String> getCompanionAbilityValues() {
        Map<String, String> dummy = new HashMap<>();

        dummy.put(String.valueOf(Attribute.STRENGTH), Integer.toString(companion.getStrength()));
        dummy.put(String.valueOf(Attribute.DEXTERITY), Integer.toString(companion.getDexterity()));
        dummy.put(String.valueOf(Attribute.CONSTITUTION), Integer.toString(companion.getConstitution()));
        dummy.put(String.valueOf(Attribute.INTELLIGENCE), Integer.toString(companion.getIntelligence()));
        dummy.put(String.valueOf(Attribute.WISDOM), Integer.toString(companion.getWisdom()));
        dummy.put(String.valueOf(Attribute.CHARISMA), Integer.toString(companion.getCharisma()));

        return dummy;
    }

    public Map<String, String> getCompanionAbilityModifiers() {
        Map<String, String> dummy = new HashMap<>();

        dummy.put(String.valueOf(Attribute.STRENGTH), StatCalculator.abilityModifierAsString(companion.getStrength()));
        dummy.put(String.valueOf(Attribute.DEXTERITY), StatCalculator.abilityModifierAsString(companion.getDexterity()));
        dummy.put(String.valueOf(Attribute.CONSTITUTION), StatCalculator.abilityModifierAsString(companion.getConstitution()));
        dummy.put(String.valueOf(Attribute.INTELLIGENCE), StatCalculator.abilityModifierAsString(companion.getIntelligence()));
        dummy.put(String.valueOf(Attribute.WISDOM), StatCalculator.abilityModifierAsString(companion.getWisdom()));
        dummy.put(String.valueOf(Attribute.CHARISMA), StatCalculator.abilityModifierAsString(companion.getCharisma()));

        return dummy;
    }
}