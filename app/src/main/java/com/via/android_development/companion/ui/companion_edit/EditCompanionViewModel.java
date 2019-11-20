package com.via.android_development.companion.ui.companion_edit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;

public class EditCompanionViewModel extends AndroidViewModel {

    private FirebaseCompanion companion;

    public EditCompanionViewModel(@NonNull Application application) {
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
