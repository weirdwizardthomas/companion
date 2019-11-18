package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.local.CompanionRepository;
import com.via.android_development.companion.utility.enums.Alignment;

public class CompanionViewModel extends AndroidViewModel {

    private CompanionRepository companionRepository;

    public CompanionViewModel(Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
    }

    public Companion getMockup() {
        Companion companion = new Companion();
        companion.setName("Tom");
        companion.setProfession("Fighter");
        companion.setStrength(11);
        companion.setDexterity(12);
        companion.setConstitution(8);
        companion.setIntelligence(16);
        companion.setWisdom(18);
        companion.setCharisma(10);
        companion.setBonds("My brother");
        companion.setBackground("Sailor");
        companion.setFlaws("Hums constantly");
        companion.setAlignment(String.valueOf(Alignment.NEUTRAL_GOOD));
        companion.setPersonalityTraits("Always humble");

        return companion;
    }

    public void insertCompanion(Companion companion) {
        companionRepository.insert(companion);
    }
}