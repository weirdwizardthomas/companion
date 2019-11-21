package com.via.android_development.companion.ui.companion_edit;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FirebaseEditCompanionViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;

    public FirebaseEditCompanionViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EditCompanionViewModel(mApplication, mParam);
    }
}
