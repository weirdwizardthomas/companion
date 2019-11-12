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
        checkboxes.put(getString(R.string.skillsAthletics), (CheckBox) root.findViewById(R.id.athletics));
        checkboxes.put(getString(R.string.skillsAcrobatics), (CheckBox) root.findViewById(R.id.acrobatics));
        checkboxes.put(getString(R.string.skillsSleightOfHand), (CheckBox) root.findViewById(R.id.sleightOfHand));
        checkboxes.put(getString(R.string.skillsStealth), (CheckBox) root.findViewById(R.id.stealth));
        checkboxes.put(getString(R.string.skillsArcana), (CheckBox) root.findViewById(R.id.arcana));
        checkboxes.put(getString(R.string.skillsHistory), (CheckBox) root.findViewById(R.id.history));
        checkboxes.put(getString(R.string.skillsInvestigation), (CheckBox) root.findViewById(R.id.investigation));
        checkboxes.put(getString(R.string.skillsNature), (CheckBox) root.findViewById(R.id.nature));
        checkboxes.put(getString(R.string.skillsReligion), (CheckBox) root.findViewById(R.id.religion));
        checkboxes.put(getString(R.string.skillsAnimalHandling), (CheckBox) root.findViewById(R.id.animalHandling));
        checkboxes.put(getString(R.string.skillsInsight), (CheckBox) root.findViewById(R.id.insight));
        checkboxes.put(getString(R.string.skillsMedicine), (CheckBox) root.findViewById(R.id.medicine));
        checkboxes.put(getString(R.string.skillsPerception), (CheckBox) root.findViewById(R.id.perception));
        checkboxes.put(getString(R.string.skillsSurvival), (CheckBox) root.findViewById(R.id.survival));
        checkboxes.put(getString(R.string.skillsDeception), (CheckBox) root.findViewById(R.id.deception));
        checkboxes.put(getString(R.string.skillsIntimidation), (CheckBox) root.findViewById(R.id.intimidation));
        checkboxes.put(getString(R.string.skillsPerformance), (CheckBox) root.findViewById(R.id.performance));
        checkboxes.put(getString(R.string.skillsPersuasion), (CheckBox) root.findViewById(R.id.persuasion));
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
