package com.via.android_development.companion.ui.companion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.ui.companions_overview.CompanionOverviewFragment;
import com.via.android_development.companion.utility.DieRoller;
import com.via.android_development.companion.utility.StatCalculator;
import com.via.android_development.companion.utility.enums.Attribute;
import com.via.android_development.companion.utility.enums.Skill;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompanionFragment extends Fragment {
    public static final String COLLECTION_NAME = "Adventurers";

    private CompanionViewModel companionViewModel;

    private Map<String, Button> buttons;
    private TextView name;
    private TextView raceAndProfession;
    private TextView hitpoints;
    private TextView temporaryHitpoints;
    private TextView armourClass;
    private TextView speed;
    private TextView initiative;

    private TextView background;
    private TextView ideals;
    private TextView traits;
    private TextView flaws;
    private TextView bonds;

    private Switch statsSwitch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companionViewModel = ViewModelProviders.of(this).get(CompanionViewModel.class);
        View root = inflater.inflate(R.layout.companion_fragment, container, false);

        initialiseLayout(root);
        setHasOptionsMenu(true);

        loadAdventurer();

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.top_edit_options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.editButton) {
            Bundle bundle = new Bundle();
            bundle.putInt(CompanionOverviewFragment.ID_KEY, companionViewModel.getCompanion().getId());
            Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.companionToEdit, bundle);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAdventurer() {
        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(Context.MODE_PRIVATE);
        if (sharedPref.contains(CompanionOverviewFragment.ID_KEY)) {
            int id = sharedPref.getInt(CompanionOverviewFragment.ID_KEY, -1);

            FirebaseFirestore
                    .getInstance()
                    .collection(COLLECTION_NAME)
                    .document(String.valueOf(id))
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                FirebaseCompanion firebaseCompanion = documentSnapshot.toObject(FirebaseCompanion.class);
                                companionViewModel.setCompanion(firebaseCompanion);
                                updateDisplayedValues();
                            }
                        }
                    });
        }
    }


    private void initialiseLayout(View root) {
        initialiseButtons(root);
        initialiseStatSwitch(root);

        name = root.findViewById(R.id.name);
        raceAndProfession = root.findViewById(R.id.raceAndProfession);
        hitpoints = root.findViewById(R.id.hitpointsValue);
        temporaryHitpoints = root.findViewById(R.id.temporaryHitpointsValue);
        armourClass = root.findViewById(R.id.armourClassValue);
        speed = root.findViewById(R.id.speedValue);
        initiative = root.findViewById(R.id.initiativeValue);

        background = root.findViewById(R.id.backgroundContent);
        ideals = root.findViewById(R.id.idealsContent);
        traits = root.findViewById(R.id.traitsContent);
        flaws = root.findViewById(R.id.flawsContent);
        bonds = root.findViewById(R.id.bondsContent);
    }

    private void updateDisplayedValues() {
        FirebaseCompanion companion = companionViewModel.getCompanion();
        updateStatButtons(getCompanionAbilityValues());
        name.setText(companion.getName());

        String raceAndStringText = companion.getRace() + " " + companion.getProfession();
        raceAndProfession.setText(raceAndStringText);
        String hpText = companion.getHitpoints() + "/" + companion.getMaximalHitpoints();
        hitpoints.setText(hpText);

        temporaryHitpoints.setText(Integer.toString(companion.getTemporaryHitpoints()));
        armourClass.setText(Integer.toString(companion.getArmourClass()));
        speed.setText(Integer.toString(companion.getSpeed()));
        initiative.setText(Integer.toString(companion.getInitiative()));
        background.setText(companion.getBackground());
        ideals.setText(companion.getIdeals());
        traits.setText(companion.getPersonalityTraits());
        flaws.setText(companion.getFlaws());
        bonds.setText(companion.getBonds());
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
        String dummy = label + " " + value;
        Objects.requireNonNull(buttons.get(key)).setText(dummy);
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
        buttons.put(String.valueOf(Skill.ARCANA), (Button) root.findViewById(R.id.deception));
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
        FirebaseCompanion companion = companionViewModel.getCompanion();
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
        FirebaseCompanion companion = companionViewModel.getCompanion();
        Map<String, String> dummy = new HashMap<>();

        dummy.put(getString(R.string.attributeStrength), StatCalculator.abilityModifierAsString(companion.getStrength()));
        dummy.put(getString(R.string.attributeDexterity), StatCalculator.abilityModifierAsString(companion.getDexterity()));
        dummy.put(getString(R.string.attributeConstitution), StatCalculator.abilityModifierAsString(companion.getConstitution()));
        dummy.put(getString(R.string.attributeIntelligence), StatCalculator.abilityModifierAsString(companion.getIntelligence()));
        dummy.put(getString(R.string.attributeWisdom), StatCalculator.abilityModifierAsString(companion.getWisdom()));
        dummy.put(getString(R.string.attributeCharisma), StatCalculator.abilityModifierAsString(companion.getCharisma()));

        return dummy;
    }

}