package com.via.android_development.companion.ui.companion_create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.via.android_development.companion.R;

import java.util.HashMap;
import java.util.Map;

//PROFICIENCIES
public class CompanionCreateFragment2 extends Fragment {
    private CompanionCreateViewModel companionCreateViewModel;

    private Button resetButton;
    private ImageButton previousArrowButton;
    private ImageButton nextArrowButton;

    private Map<String, CheckBox> checkboxes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.companion_create_fragment_2, container, false);

        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        initialiseCheckBoxes(root);


        initialiseBottomButtons(root, savedInstanceState);
        return root;
    }

    private void initialiseCheckBoxes(View root) {
        checkboxes = new HashMap<>();
        checkboxes.put(getString(R.string.attributeStrength), (CheckBox) root.findViewById(R.id.strength_saving_throw_checkbox));
        checkboxes.put(getString(R.string.attributeDexterity), (CheckBox) root.findViewById(R.id.wisdom_saving_throw_checkbox));
        checkboxes.put(getString(R.string.attributeConstitution), (CheckBox) root.findViewById(R.id.constitution_saving_throw_checkbox));
        checkboxes.put(getString(R.string.attributeIntelligence), (CheckBox) root.findViewById(R.id.intelligence_saving_throw_checkbox));
        checkboxes.put(getString(R.string.attributeWisdom), (CheckBox) root.findViewById(R.id.wisdom_saving_throw_checkbox));
        checkboxes.put(getString(R.string.attributeCharisma), (CheckBox) root.findViewById(R.id.charisma_saving_throw_checkbox));
        checkboxes.put(getString(R.string.skill_athletics), (CheckBox) root.findViewById(R.id.athletics_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_acrobatics), (CheckBox) root.findViewById(R.id.acrobatics_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_sleight_of_hand), (CheckBox) root.findViewById(R.id.sleight_of_hand_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_stealth), (CheckBox) root.findViewById(R.id.stealth_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_arcana), (CheckBox) root.findViewById(R.id.arcana_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_history), (CheckBox) root.findViewById(R.id.history_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_investigation), (CheckBox) root.findViewById(R.id.investigation_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_nature), (CheckBox) root.findViewById(R.id.nature_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_religion), (CheckBox) root.findViewById(R.id.religion_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_animal_handling), (CheckBox) root.findViewById(R.id.animal_handling_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_insight), (CheckBox) root.findViewById(R.id.insight_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_medicine), (CheckBox) root.findViewById(R.id.medicine_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_perception), (CheckBox) root.findViewById(R.id.perception_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_survival), (CheckBox) root.findViewById(R.id.survival_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_deception), (CheckBox) root.findViewById(R.id.deception_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_intimidation), (CheckBox) root.findViewById(R.id.intimidation_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_performance), (CheckBox) root.findViewById(R.id.performance_skill_proficiency_checkbox));
        checkboxes.put(getString(R.string.skill_persuasion), (CheckBox) root.findViewById(R.id.persuasion_skill_proficiency_checkbox));
    }

    private void initialiseBottomButtons(final View root, final Bundle savedInstanceState) {

        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CheckBox checkBox : checkboxes.values())
                    checkBox.setChecked(false);
            }
        });

        previousArrowButton = root.findViewById(R.id.previous_button);
        previousArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create2_to_create1, savedInstanceState);
            }
        });

        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                updateCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create2_to_create3, bundle);
            }
        });

        adjustButtonBottomNavOffset();
    }

    private void adjustButtonBottomNavOffset() {
        int bottomNavigationHeight = getActivity().findViewById(R.id.nav_view).getHeight();

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) resetButton.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        resetButton.setLayoutParams(layoutParams);
    }

    private void updateCompanionInstance() {
    }
}
