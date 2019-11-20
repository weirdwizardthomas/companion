package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;

public class CompanionViewModel extends AndroidViewModel {

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

    public void deleteCompanion() {
        FirebaseFirestore
                .getInstance()
                .collection(CompanionFragment.COLLECTION_NAME)
                .document(String.valueOf(companion.getId()))
                .delete();
    }
}