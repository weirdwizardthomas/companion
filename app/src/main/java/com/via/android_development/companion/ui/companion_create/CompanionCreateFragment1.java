package com.via.android_development.companion.ui.companion_create;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.utility.StatCalculator;

import java.util.ArrayList;
import java.util.List;

public class CompanionCreateFragment1 extends Fragment {

    private EditText nameInput;
    private List<View> statItems;
    private List<NumberPicker> statPickers;
    private List<TextView> statLabels;
    private List<TextView> statModifiers;
    private NumberPicker racePicker;
    private NumberPicker professionPicker;
    private Button resetButton;
    private ImageButton previousArrowButton;
    private ImageButton nextArrowButton;

    private CompanionCreateViewModel companionCreateViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.companion_create_fragment_1, container, false);

        nameInput = root.findViewById(R.id.nameInput);

        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        Companion companion = new Companion();
        companion.setName("Tom");
        companionCreateViewModel.insert(companion);

        List<Companion> companions = companionCreateViewModel.getAllCompanions().getValue();
        initialiseStatBlock(root);
        initialisePickers(root);
        initialiseBottomButtons(root);
        return root;
    }

    private void initialiseBottomButtons(final View root) {
        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameInput.setText(""); //reset the name input

                for (NumberPicker numberPicker : statPickers)
                    numberPicker.setValue(Companion.DEFAULT_ATTRIBUTE_VALUE);

                for (TextView statModifier : statModifiers)
                    updateModifier(statModifier, Companion.DEFAULT_ATTRIBUTE_VALUE);

                racePicker.setValue(CompanionCreateViewModel.DEFAULT_RACE_INDEX);
                professionPicker.setValue(CompanionCreateViewModel.DEFAULT_PROFESSION_INDEX);
            }
        });

        adjustButtonBottomNavOffset(root);

        previousArrowButton = root.findViewById(R.id.previous_button);
        disableButton(previousArrowButton, R.drawable.ic_arrow_left_grey600_24dp);
        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompanionInstance();
                Bundle bundle = new Bundle();
                Navigation.findNavController(root).navigate(R.id.create1_to_create2, bundle);
            }
        });

    }

    private void initialisePickers(View root) {
        racePicker = root.findViewById(R.id.racePicker);
        initialiseStringPicker(racePicker,
                CompanionCreateViewModel.getAllRaces(),
                CompanionCreateViewModel.DEFAULT_RACE_INDEX);

        professionPicker = root.findViewById(R.id.professionPicker);
        initialiseStringPicker(professionPicker,
                CompanionCreateViewModel.getAllProfessions(),
                CompanionCreateViewModel.DEFAULT_PROFESSION_INDEX);
    }

    private void initialiseStringPicker(NumberPicker picker, String[] values, int defaultValues) {
        picker.setMinValue(1);
        picker.setMaxValue(values.length);
        picker.setDisplayedValues(values);
        picker.setValue(defaultValues);
    }

    private void initialiseStatBlock(View root) {
        statItems = new ArrayList<>();
        statLabels = new ArrayList<>();
        statPickers = new ArrayList<>();
        statModifiers = new ArrayList<>();

        statItems.add(root.findViewById(R.id.statsItem1));
        statItems.add(root.findViewById(R.id.statsItem2));
        statItems.add(root.findViewById(R.id.statsItem3));
        statItems.add(root.findViewById(R.id.statsItem4));
        statItems.add(root.findViewById(R.id.statsItem5));
        statItems.add(root.findViewById(R.id.statsItem6));

        for (View view : statItems) {
            statPickers.add((NumberPicker) view.findViewById(R.id.levelPicker));
            statLabels.add((TextView) view.findViewById(R.id.levelLabel));
            statModifiers.add((TextView) view.findViewById(R.id.modifier));
        }

        for (int i = 0; i < statLabels.size(); ++i)
            statLabels.get(i).setText(CompanionCreateViewModel.getAllAttributesIds().get(i));

        for (NumberPicker numberPicker : statPickers)
            setupAttributePicker(numberPicker);

        int i = 0;
        for (NumberPicker statPicker : statPickers) {
            final int finalI = i;
            statPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    updateModifier(statModifiers.get(finalI), newVal);
                }
            });
            ++i;
        }

    }

    @SuppressLint("WrongConstant")
    private void setupAttributePicker(NumberPicker numberPicker) {
        numberPicker.setMinValue(Companion.MIN_LEVEL);
        numberPicker.setMaxValue(Companion.MAX_LEVEL);
        numberPicker.setOrientation(NumberPicker.HORIZONTAL);
        numberPicker.setValue(Companion.DEFAULT_ATTRIBUTE_VALUE);
    }

    private void toggleButton(ImageButton button, int resourceId, boolean enable) {
        button.setEnabled(enable);
        button.setImageResource(resourceId);
    }

    private void disableButton(ImageButton button, int resourceId) {
        toggleButton(button, resourceId, false);
    }

    private void adjustButtonBottomNavOffset(View root) {
        int bottomNavigationHeight = getActivity().findViewById(R.id.nav_view).getHeight();

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) resetButton.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        resetButton.setLayoutParams(layoutParams);
    }

    private void updateModifier(TextView statModifier, int newVal) {
        int modifier = StatCalculator.abilityModifier(newVal);
        String modifierText = (modifier >= 0 ? "+" : "") + modifier;
        statModifier.setText(modifierText);
    }

    private void updateCompanionInstance() {

    }
}
