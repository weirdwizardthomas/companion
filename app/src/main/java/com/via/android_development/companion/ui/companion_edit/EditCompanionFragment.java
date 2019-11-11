package com.via.android_development.companion.ui.companion_edit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.via.android_development.companion.R;

public class EditCompanionFragment extends Fragment {

    private EditCompanionViewModel mViewModel;

    public static EditCompanionFragment newInstance() {
        return new EditCompanionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.companion_edit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditCompanionViewModel.class);
        // TODO: Use the ViewModel
    }

}
