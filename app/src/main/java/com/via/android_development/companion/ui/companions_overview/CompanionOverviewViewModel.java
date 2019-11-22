package com.via.android_development.companion.ui.companions_overview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class CompanionOverviewViewModel extends AndroidViewModel {
    public static final String ID_KEY = "companionId";
    public static final String IS_OPEN_FROM_OVERVIEW = "openFromOverview";

    public CompanionOverviewViewModel(@NonNull Application application) {
        super(application);

    }

}
