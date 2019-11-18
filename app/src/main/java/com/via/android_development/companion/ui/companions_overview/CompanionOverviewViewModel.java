package com.via.android_development.companion.ui.companions_overview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.persistence.local.CompanionRepository;

import java.util.List;

public class CompanionOverviewViewModel extends AndroidViewModel {
    CompanionRepository companionRepository;

    public CompanionOverviewViewModel(@NonNull Application application) {
        super(application);
        companionRepository = new CompanionRepository(application);
    }

    public LiveData<List<Companion>> getAllCompanions() {
        return companionRepository.getAllCompanions();
    }

    public void insert(Companion companion) {
        companionRepository.insert(companion);
    }
}
