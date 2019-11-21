package com.via.android_development.companion.ui.companion_create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//PROFICIENCIES
public class CompanionCreateFragment2 extends Fragment {
    private CompanionCreateViewModel companionCreateViewModel;

    private Map<String, CheckBox> savingThrowCheckboxes;
    private Map<String, CheckBox> skillCheckboxes;

    private Button resetButton;
    private Button nextArrowButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.companion_create_fragment_2, container, false);

        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        initialiseLayout(root);
        observeCompanions();

        return root;
    }


    private Set<String> getProficiencies(Map<String, CheckBox> checkboxes) {
        Set<String> proficiencies = new HashSet<>();

        for (Map.Entry<String, CheckBox> entry : checkboxes.entrySet())
            if (entry.getValue().isChecked())
                proficiencies.add(entry.getKey());

        return proficiencies;
    }

    private void initialiseLayout(View root) {
        initialiseCheckBoxes(root);
        initialiseBottomButtons(root);
    }

    private void initialiseCheckBoxes(View root) {
        savingThrowCheckboxes = new HashMap<>();
        savingThrowCheckboxes.put(getString(R.string.skillsAthletics), (CheckBox) root.findViewById(R.id.athletics));
        savingThrowCheckboxes.put(getString(R.string.skillsAcrobatics), (CheckBox) root.findViewById(R.id.acrobatics));
        savingThrowCheckboxes.put(getString(R.string.skillsSleightOfHand), (CheckBox) root.findViewById(R.id.sleightOfHand));
        savingThrowCheckboxes.put(getString(R.string.skillsStealth), (CheckBox) root.findViewById(R.id.stealth));
        savingThrowCheckboxes.put(getString(R.string.skillsArcana), (CheckBox) root.findViewById(R.id.deception));
        savingThrowCheckboxes.put(getString(R.string.skillsHistory), (CheckBox) root.findViewById(R.id.history));
        savingThrowCheckboxes.put(getString(R.string.skillsInvestigation), (CheckBox) root.findViewById(R.id.investigation));
        savingThrowCheckboxes.put(getString(R.string.skillsNature), (CheckBox) root.findViewById(R.id.nature));
        savingThrowCheckboxes.put(getString(R.string.skillsReligion), (CheckBox) root.findViewById(R.id.religion));
        savingThrowCheckboxes.put(getString(R.string.skillsAnimalHandling), (CheckBox) root.findViewById(R.id.animalHandling));
        savingThrowCheckboxes.put(getString(R.string.skillsInsight), (CheckBox) root.findViewById(R.id.insight));
        savingThrowCheckboxes.put(getString(R.string.skillsMedicine), (CheckBox) root.findViewById(R.id.medicine));
        savingThrowCheckboxes.put(getString(R.string.skillsPerception), (CheckBox) root.findViewById(R.id.perception));
        savingThrowCheckboxes.put(getString(R.string.skillsSurvival), (CheckBox) root.findViewById(R.id.survival));
        savingThrowCheckboxes.put(getString(R.string.skillsDeception), (CheckBox) root.findViewById(R.id.deception));
        savingThrowCheckboxes.put(getString(R.string.skillsIntimidation), (CheckBox) root.findViewById(R.id.intimidation));
        savingThrowCheckboxes.put(getString(R.string.skillsPerformance), (CheckBox) root.findViewById(R.id.performance));
        savingThrowCheckboxes.put(getString(R.string.skillsPersuasion), (CheckBox) root.findViewById(R.id.persuasion));

        skillCheckboxes = new HashMap<>();
        skillCheckboxes.put(getString(R.string.attributeStrength), (CheckBox) root.findViewById(R.id.strength_saving_throw_checkbox));
        skillCheckboxes.put(getString(R.string.attributeDexterity), (CheckBox) root.findViewById(R.id.dexterity_saving_throw_checkbox));
        skillCheckboxes.put(getString(R.string.attributeConstitution), (CheckBox) root.findViewById(R.id.constitution_saving_throw_checkbox));
        skillCheckboxes.put(getString(R.string.attributeIntelligence), (CheckBox) root.findViewById(R.id.intelligence_saving_throw_checkbox));
        skillCheckboxes.put(getString(R.string.attributeWisdom), (CheckBox) root.findViewById(R.id.wisdom_saving_throw_checkbox));
        skillCheckboxes.put(getString(R.string.attributeCharisma), (CheckBox) root.findViewById(R.id.charisma_saving_throw_checkbox));
    }

    private void initialiseBottomButtons(final View root) {

        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CheckBox checkBox : savingThrowCheckboxes.values())
                    checkBox.setChecked(false);
                for (CheckBox checkBox : skillCheckboxes.values())
                    checkBox.setChecked(false);
            }
        });

        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create2_to_create3);
            }
        });

    }

    private void observeCompanions() {
        Observer<List<Companion>> companionObserver = new Observer<List<Companion>>() {
            @Override
            public void onChanged(List<Companion> companions) {
                companionCreateViewModel.setAdventurer(companions.get(0));
                updateDisplayedValues();
            }
        };
        companionCreateViewModel.getAllCompanions().observe(this, companionObserver);
    }

    private void updateDisplayedValues() {
        Companion dummy = companionCreateViewModel.getAdventurer();

        Set<String> skills = dummy.getSkillProficiencies();
        for (Map.Entry<String, CheckBox> entry : skillCheckboxes.entrySet())
            entry.getValue().setChecked(skills.contains(entry.getKey()));

        Set<String> savingThrows = dummy.getSavingThrowProficiencies();
        for (Map.Entry<String, CheckBox> entry : savingThrowCheckboxes.entrySet())
            entry.getValue().setChecked(savingThrows.contains(entry.getKey()));
    }

    private void updateCompanionInstance() {
        Set<String> savingThrows = getProficiencies(savingThrowCheckboxes);
        Set<String> skills = getProficiencies(skillCheckboxes);

        Companion dummy = companionCreateViewModel.getAdventurer();
        dummy.setSkillProficiencies(skills);
        dummy.setSavingThrowProficiencies(savingThrows);
        companionCreateViewModel.update(dummy);
    }
}
