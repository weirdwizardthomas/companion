package com.via.android_development.companion.ui.companion_edit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditCompanionViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;

    public EditCompanionViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EditCompanionViewModel(mApplication, mParam);
    }
}
