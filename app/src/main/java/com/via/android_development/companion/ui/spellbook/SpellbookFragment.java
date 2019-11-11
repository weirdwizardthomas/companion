package com.via.android_development.companion.ui.spellbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.via.android_development.companion.R;

public class SpellbookFragment extends Fragment {

    private SpellbookViewModel spellbookViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        spellbookViewModel = ViewModelProviders.of(this).get(SpellbookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_spellbook, container, false);

        return root;
    }
}