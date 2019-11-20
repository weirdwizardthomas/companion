package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;

class CompanionViewModel extends AndroidViewModel {

    private FirebaseCompanion companion;

    public CompanionViewModel(Application application) {
        super(application);
        companion = new FirebaseCompanion();
    }

    public FirebaseCompanion getCompanion() {
        return companion;
    }

    public void setCompanion(FirebaseCompanion companion) {
        this.companion = companion;
    }

}