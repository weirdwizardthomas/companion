package com.via.android_development.companion.ui.companion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.utility.DieRoller;
import com.via.android_development.companion.utility.StatCalculator;
import com.via.android_development.companion.utility.enums.Attribute;
import com.via.android_development.companion.utility.enums.Skill;

import java.util.HashMap;
import java.util.Map;

public class CompanionFragment extends Fragment {

    private Map<String, Button> buttons;
    private TextView name;
    private TextView raceAndProfession;
    private Switch statsSwitch;

    private CompanionViewModel companionViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companionViewModel = ViewModelProviders.of(this).get(CompanionViewModel.class);
        View root = inflater.inflate(R.layout.companion_fragment, container, false);

        adjustForMenu(root);
        initialiseButtons(root);
        initialiseStatSwitch(root);

        name = root.findViewById(R.id.name);
        raceAndProfession = root.findViewById(R.id.race_and_profession);

        loadCompanion();

        return root;
    }

    private void loadCompanion() {
        updateStatButtons(getCompanionAbilityValues());
        Companion companion = companionViewModel.getMockup();
        name.setText(companion.getName());
        raceAndProfession.setText(new StringBuilder().append(companion.getRace()).append(" ").toString())
        ;

    }

    private void initialiseStatSwitch(View root) {
        statsSwitch = root.findViewById(R.id.stats_switch);
        statsSwitch.setChecked(false);

        statsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateStatButtons((isChecked ? getCompanionAbilityModifiers() : getCompanionAbilityValues()));
            }
        });

        //Initialise default values
        updateStatButtons(getCompanionAbilityValues());
    }

    private void updateStatButtons(Map<String, String> dummy) {
        for (Map.Entry<String, String> entry : dummy.entrySet())
            updateButton(entry.getKey(), entry.getKey(), entry.getValue());
    }

    private void updateButton(String key, String label, String value) {
        String dummy = new StringBuilder().append(label).append(" ").append(value).toString();
        buttons.get(key).setText(dummy);
    }

    private void addOnClickListenersToButtons() {
        for (Map.Entry<String, Button> entry : buttons.entrySet()) {
            entry.getValue().setText(entry.getKey());
            entry.getValue().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), Integer.toString(DieRoller.getInstance().rollDie(20)), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initialiseButtons(View root) {
        buttons = new HashMap<>();

        //Attributes
        buttons.put(String.valueOf(Attribute.STRENGTH), (Button) root.findViewById(R.id.strength));
        buttons.put(String.valueOf(Attribute.DEXTERITY), (Button) root.findViewById(R.id.dexterity));
        buttons.put(String.valueOf(Attribute.CONSTITUTION), (Button) root.findViewById(R.id.constitution));
        buttons.put(String.valueOf(Attribute.INTELLIGENCE), (Button) root.findViewById(R.id.intelligence));
        buttons.put(String.valueOf(Attribute.WISDOM), (Button) root.findViewById(R.id.wisdom));
        buttons.put(String.valueOf(Attribute.CHARISMA), (Button) root.findViewById(R.id.charisma));

        //Skills
        buttons.put(String.valueOf(Skill.ATHLETICS), (Button) root.findViewById(R.id.athletics));
        buttons.put(String.valueOf(Skill.ACROBATICS), (Button) root.findViewById(R.id.acrobatics));
        buttons.put(String.valueOf(Skill.SLEIGHT_OF_HAND), (Button) root.findViewById(R.id.sleightOfHand));
        buttons.put(String.valueOf(Skill.STEALTH), (Button) root.findViewById(R.id.stealth));
        buttons.put(String.valueOf(Skill.ARCANA), (Button) root.findViewById(R.id.arcana));
        buttons.put(String.valueOf(Skill.HISTORY), (Button) root.findViewById(R.id.history));
        buttons.put(String.valueOf(Skill.INVESTIGATION), (Button) root.findViewById(R.id.investigation));
        buttons.put(String.valueOf(Skill.NATURE), (Button) root.findViewById(R.id.nature));
        buttons.put(String.valueOf(Skill.RELIGION), (Button) root.findViewById(R.id.religion));
        buttons.put(String.valueOf(Skill.ANIMAL_HANDLING), (Button) root.findViewById(R.id.animalHandling));
        buttons.put(String.valueOf(Skill.INSIGHT), (Button) root.findViewById(R.id.insight));
        buttons.put(String.valueOf(Skill.MEDICINE), (Button) root.findViewById(R.id.medicine));
        buttons.put(String.valueOf(Skill.PERCEPTION), (Button) root.findViewById(R.id.perception));
        buttons.put(String.valueOf(Skill.SURVIVAL), (Button) root.findViewById(R.id.survival));
        buttons.put(String.valueOf(Skill.DECEPTION), (Button) root.findViewById(R.id.deception));
        buttons.put(String.valueOf(Skill.INTIMIDATION), (Button) root.findViewById(R.id.intimidation));
        buttons.put(String.valueOf(Skill.PERFORMANCE), (Button) root.findViewById(R.id.performance));
        buttons.put(String.valueOf(Skill.PERSUASION), (Button) root.findViewById(R.id.persuasion));

        addOnClickListenersToButtons();
    }

    private Map<String, String> getCompanionAbilityValues() {
        Companion companion = companionViewModel.getMockup();
        Map<String, String> dummy = new HashMap<>();

        dummy.put(String.valueOf(Attribute.STRENGTH), Integer.toString(companion.getStrength()));
        dummy.put(String.valueOf(Attribute.DEXTERITY), Integer.toString(companion.getDexterity()));
        dummy.put(String.valueOf(Attribute.CONSTITUTION), Integer.toString(companion.getConstitution()));
        dummy.put(String.valueOf(Attribute.INTELLIGENCE), Integer.toString(companion.getIntelligence()));
        dummy.put(String.valueOf(Attribute.WISDOM), Integer.toString(companion.getWisdom()));
        dummy.put(String.valueOf(Attribute.CHARISMA), Integer.toString(companion.getCharisma()));

        return dummy;
    }

    private Map<String, String> getCompanionAbilityModifiers() {
        Companion companion = companionViewModel.getMockup();
        Map<String, String> dummy = new HashMap<>();

        dummy.put(getString(R.string.attributeStrength), StatCalculator.abilityModifierAsString(companion.getStrength()));
        dummy.put(getString(R.string.attributeDexterity), StatCalculator.abilityModifierAsString(companion.getDexterity()));
        dummy.put(getString(R.string.attributeConstitution), StatCalculator.abilityModifierAsString(companion.getConstitution()));
        dummy.put(getString(R.string.attributeIntelligence), StatCalculator.abilityModifierAsString(companion.getIntelligence()));
        dummy.put(getString(R.string.attributeWisdom), StatCalculator.abilityModifierAsString(companion.getWisdom()));
        dummy.put(getString(R.string.attributeCharisma), StatCalculator.abilityModifierAsString(companion.getCharisma()));

        return dummy;
    }

    private void adjustForMenu(View root) {
        int bottomNavigationHeight = getActivity().findViewById(R.id.nav_view).getHeight();

        ScrollView scrollView = root.findViewById(R.id.mainScrollView);
        ScrollView.LayoutParams layoutParams = (ScrollView.LayoutParams) scrollView.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        scrollView.setLayoutParams(layoutParams);
    }
}