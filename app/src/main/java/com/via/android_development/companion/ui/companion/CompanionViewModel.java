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
    public LiveData<Task<DocumentSnapshot>> getdataSnapshotLiveData(){
        return liveData;
    }

    public FirebaseCompanion getCompanion() {
        return companion;
    }

    public void setCompanion(FirebaseCompanion companion) {
        this.companion = companion;
    }

}