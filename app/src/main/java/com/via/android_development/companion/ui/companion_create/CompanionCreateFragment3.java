package com.via.android_development.companion.ui.companion_create;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;

public class CompanionCreateFragment3 extends Fragment {

    private NumberPicker alignmentPicker;

    private EditText backgroundInput;

    private Button resetButton;
    private ImageButton previousArrowButton;
    private ImageButton nextArrowButton;

    private CompanionCreateViewModel companionCreateViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        final View root = inflater.inflate(R.layout.companion_create_fragment_3, container, false);

        backgroundInput = root.findViewById(R.id.background_edit_text);

        initialiseAlignmentPicker(root);
        initialiseBottomButtons(savedInstanceState, root);

        adjustButtonBottomNavOffset();

        return root;
    }

    private void initialiseBottomButtons(final Bundle savedInstanceState, final View root) {
        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reset alignment picker
                alignmentPicker.setValue(CompanionCreateViewModel.DEFAULT_ALIGNMENT_INDEX);

                //reset text inputs
                backgroundInput.setText("");
            }
        });

        previousArrowButton = root.findViewById(R.id.previous_button);
        previousArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create3_to_create1, savedInstanceState);
            }
        });

        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO save the member
                updateCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create3_to_overview);
            }
        });
    }

    private void initialiseAlignmentPicker(View root) {
        alignmentPicker = root.findViewById(R.id.alignmentPicker);
        initialiseStringPicker(alignmentPicker, CompanionCreateViewModel.getAllAlignments(), CompanionCreateViewModel.DEFAULT_ALIGNMENT_INDEX);
    }

    private void adjustButtonBottomNavOffset() {
        int bottomNavigationHeight = getActivity().findViewById(R.id.nav_view).getHeight();

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) resetButton.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        resetButton.setLayoutParams(layoutParams);
    }

    private void initialiseStringPicker(NumberPicker picker, String[] values, int defaultValues) {
        picker.setMinValue(1);
        picker.setMaxValue(values.length);
        picker.setDisplayedValues(values);
        picker.setValue(defaultValues);
    }

    private void updateCompanionInstance() {

    }
}
