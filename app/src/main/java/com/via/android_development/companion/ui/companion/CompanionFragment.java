package com.via.android_development.companion.ui.companion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;

public class CompanionFragment extends Fragment {

    private NumberPicker currentHitPointsPicker;

    private CompanionViewModel companionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companionViewModel = ViewModelProviders.of(this).get(CompanionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_companion, container, false);

        return root;
    }
}