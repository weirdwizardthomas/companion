package com.via.android_development.companion.ui.companion_create;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;
import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.ui.companion.CompanionFragment;
import com.via.android_development.companion.utility.enums.Alignment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


//BACKGROUND AND SHIT
public class CompanionCreateFragment3 extends Fragment {
    private CompanionCreateViewModel companionCreateViewModel;

    private NumberPicker alignmentPicker;

    private EditText backgroundInput;
    private EditText traitsInput;
    private EditText idealsInput;
    private EditText bondsInput;
    private EditText flawsInput;

    private Button resetButton;
    private Button nextArrowButton;

    private Companion dummy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        final View root = inflater.inflate(R.layout.companion_create_fragment_3, container, false);

        initialiseLayout(root);
        observeCompanions();

        return root;
    }

    private void initialiseLayout(View root) {
        initialiseTextInputs(root);
        initialiseAlignmentPicker(root);
        initialiseBottomButtons(root);
    }

    private void observeCompanions() {
        Observer<List<Companion>> companionsObserver = new Observer<List<Companion>>() {
            @Override
            public void onChanged(List<Companion> companions) {
                dummy = companions.get(0);
                updateDisplayedValues();
            }
        };

        companionCreateViewModel.getAllCompanions().observe(this, companionsObserver);
    }

    private void initialiseTextInputs(View root) {
        backgroundInput = root.findViewById(R.id.background_edit_text);
        traitsInput = root.findViewById(R.id.personality_traits_edit_text);
        idealsInput = root.findViewById(R.id.ideals_edit_text);
        bondsInput = root.findViewById(R.id.bonds_edit_text);
        flawsInput = root.findViewById(R.id.flaws_edit_text);
    }

    private void initialiseAlignmentPicker(View root) {
        alignmentPicker = root.findViewById(R.id.alignmentPicker);
        initialiseStringPicker(alignmentPicker, CompanionCreateViewModel.getAllAlignments(), CompanionCreateViewModel.DEFAULT_ALIGNMENT_INDEX);
    }

    private void initialiseBottomButtons(final View root) {
        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resetCompanion();
            }
        });

        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompanionInstance(); //get new values
                saveInstanceToFirestore(); //save to remote db
                companionCreateViewModel.deleteAllCompanions(); //perform cleanup
                Navigation.findNavController(root).navigate(R.id.create3_to_overview);
            }
        });

    }

    private void initialiseStringPicker(NumberPicker picker, String[] values, int defaultValue) {
        picker.setMinValue(1);
        picker.setMaxValue(values.length);
        picker.setDisplayedValues(values);
        picker.setValue(defaultValue);
    }

    private void resetCompanion() {
        dummy.setBackground("");
        dummy.setAlignment(String.valueOf(Alignment.TRUE_NEUTRAL));
        dummy.setPersonalityTraits("");
        dummy.setIdeals("");
        dummy.setBonds("");
        dummy.setFlaws("");
    }

    private void resetFields() {
        //reset alignment picker
        alignmentPicker.setValue(CompanionCreateViewModel.DEFAULT_ALIGNMENT_INDEX);

        //reset text inputs
        backgroundInput.setText("");
        traitsInput.setText("");
        idealsInput.setText("");
        bondsInput.setText("");
        flawsInput.setText("");
    }

    private void updateDisplayedValues() {
        updatePickerValue(alignmentPicker, dummy.getAlignment(), String.valueOf(Alignment.TRUE_NEUTRAL));
        backgroundInput.setText(dummy.getBackground());
        traitsInput.setText(dummy.getPersonalityTraits());
        idealsInput.setText(dummy.getIdeals());
        bondsInput.setText(dummy.getBonds());
        flawsInput.setText(dummy.getFlaws());
    }

    private void updateCompanionInstance() {
        dummy.setBackground(backgroundInput.getText().toString());
        dummy.setAlignment(CompanionCreateViewModel.getAlignmentByIndex(alignmentPicker.getValue() - 1));
        dummy.setBonds(bondsInput.getText().toString());
        dummy.setFlaws(flawsInput.getText().toString());
        dummy.setPersonalityTraits(traitsInput.getText().toString());
        dummy.setIdeals(idealsInput.getText().toString());

    }

    private void saveInstanceToFirestore() {

        int nextID;
        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);

        if (sharedPref.contains("NextID")) {
            nextID = sharedPref.getInt("NextID", 0);
            nextID++;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("NextID", nextID);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = sharedPref.edit();
            nextID = 0;
            editor.putInt("NextID", nextID);
            editor.apply();
        }

        dummy.setId(nextID);

        FirebaseCompanion firebaseCompanion = new FirebaseCompanion(dummy);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CompanionFragment.COLLECTION_NAME)
                .document(String.valueOf(firebaseCompanion.getId()))
                .set(firebaseCompanion);
    }

    private void updatePickerValue(NumberPicker picker, String item, String defaultValue) {
        String[] pickerValues = picker.getDisplayedValues();
        int index = Arrays.asList(pickerValues).indexOf(item == null ? defaultValue : item) + 1;
        picker.setValue(index);
    }

}
