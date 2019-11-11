package com.via.android_development.companion.ui.companions_overview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.via.android_development.companion.persistence.local.Companion;

import java.util.ArrayList;
import java.util.List;

public class CompanionOverviewViewModel extends AndroidViewModel {
    private List<Companion> allCompanions;

    public CompanionOverviewViewModel(@NonNull Application application) {
        super(application);
        allCompanions = new ArrayList<>();
    }

    public List<Companion> getAllCompanions() {
        return allCompanions;
    }

    public void insert(Companion companion) {
        allCompanions.add(companion);
    }
}
