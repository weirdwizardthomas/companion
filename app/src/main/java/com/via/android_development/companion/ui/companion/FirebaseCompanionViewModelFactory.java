package com.via.android_development.companion.ui.companion;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FirebaseCompanionViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;

    public FirebaseCompanionViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CompanionViewModel(mApplication, mParam);
    }
}