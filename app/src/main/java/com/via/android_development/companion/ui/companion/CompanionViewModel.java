package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.local.CompanionRepository;

public class CompanionViewModel extends AndroidViewModel {

    private CompanionRepository companionRepository;

    public CompanionViewModel(Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
    }

    public Companion getMockup() {
        Companion companion = new Companion();
        companion.setName("Tom");

        return companion;
    }

    public void insertCompanion(Companion companion) {
        companionRepository.insert(companion);
    }
}