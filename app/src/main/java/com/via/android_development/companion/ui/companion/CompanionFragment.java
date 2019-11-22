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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.ui.companions_overview.CompanionOverviewViewModel;
import com.via.android_development.companion.utility.DieRoller;
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

        int id = getSavedId();
        companionViewModel = ViewModelProviders
                .of(this, new CompanionViewModelFactory(getActivity().getApplication(), Integer.toString(id)))
                .get(CompanionViewModel.class);
        View root = inflater.inflate(R.layout.companion_fragment, container, false);

        initialiseLayout(root);
        setHasOptionsMenu(true);

        LiveData<Task<DocumentSnapshot>> liveData = companionViewModel.getdataSnapshotLiveData();
        liveData.observe(this, new Observer<Task<DocumentSnapshot>>() {
            @Override
            public void onChanged(Task<DocumentSnapshot> documentSnapshotTask) {
                if (documentSnapshotTask.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = documentSnapshotTask.getResult();
                    FirebaseCompanion companion = documentSnapshot.toObject(FirebaseCompanion.class);
                    if (companion != null) {
                        companionViewModel.setCompanion(companion);
                        updateDisplayedValues();
                    }
                }

            }
        });
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
            FirebaseCompanion companion = companionViewModel.getCompanion();
            Bundle bundle = new Bundle();
            bundle.putInt(CompanionOverviewViewModel.ID_KEY, companion.getId());
            Navigation.findNavController(Objects.requireNonNull(getView())).navigate(R.id.companionToEdit, bundle);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addOnClickListenersToButtons() {
        for (Map.Entry<String, Button> entry : buttons.entrySet()) {
            entry.getValue().setText(entry.getKey());
            entry.getValue().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), Integer.toString(DieRoller.getInstance().rollDie(DieRoller.D20)), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private int getSavedId() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        return sharedPref.contains(CompanionOverviewViewModel.ID_KEY)
                ? sharedPref.getInt(CompanionOverviewViewModel.ID_KEY, -1)
                : -1;
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


        View skillsParent = root.findViewById(R.id.skills);

        //Skills
        buttons.put(String.valueOf(Skill.ATHLETICS), (Button) skillsParent.findViewById(R.id.athletics));
        buttons.put(String.valueOf(Skill.ACROBATICS), (Button) skillsParent.findViewById(R.id.acrobatics));
        buttons.put(String.valueOf(Skill.SLEIGHT_OF_HAND), (Button) skillsParent.findViewById(R.id.sleightOfHand));
        buttons.put(String.valueOf(Skill.STEALTH), (Button) skillsParent.findViewById(R.id.stealth));
        buttons.put(String.valueOf(Skill.ARCANA), (Button) skillsParent.findViewById(R.id.deception));
        buttons.put(String.valueOf(Skill.HISTORY), (Button) skillsParent.findViewById(R.id.history));
        buttons.put(String.valueOf(Skill.INVESTIGATION), (Button) skillsParent.findViewById(R.id.investigation));
        buttons.put(String.valueOf(Skill.NATURE), (Button) skillsParent.findViewById(R.id.nature));
        buttons.put(String.valueOf(Skill.RELIGION), (Button) skillsParent.findViewById(R.id.religion));
        buttons.put(String.valueOf(Skill.ANIMAL_HANDLING), (Button) skillsParent.findViewById(R.id.animalHandling));
        buttons.put(String.valueOf(Skill.INSIGHT), (Button) skillsParent.findViewById(R.id.insight));
        buttons.put(String.valueOf(Skill.MEDICINE), (Button) skillsParent.findViewById(R.id.medicine));
        buttons.put(String.valueOf(Skill.PERCEPTION), (Button) skillsParent.findViewById(R.id.perception));
        buttons.put(String.valueOf(Skill.SURVIVAL), (Button) skillsParent.findViewById(R.id.survival));
        buttons.put(String.valueOf(Skill.DECEPTION), (Button) skillsParent.findViewById(R.id.deception));
        buttons.put(String.valueOf(Skill.INTIMIDATION), (Button) skillsParent.findViewById(R.id.intimidation));
        buttons.put(String.valueOf(Skill.PERFORMANCE), (Button) skillsParent.findViewById(R.id.performance));
        buttons.put(String.valueOf(Skill.PERSUASION), (Button) skillsParent.findViewById(R.id.persuasion));

        addOnClickListenersToButtons();
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

        View backstoryParent = root.findViewById(R.id.backstory);

        background = backstoryParent.findViewById(R.id.backgroundContent);
        ideals = backstoryParent.findViewById(R.id.idealsContent);
        traits = backstoryParent.findViewById(R.id.traitsContent);
        flaws = backstoryParent.findViewById(R.id.flawsContent);
        bonds = backstoryParent.findViewById(R.id.bondsContent);
    }

    private void initialiseStatSwitch(View root) {
        statsSwitch = root.findViewById(R.id.stats_switch);
        statsSwitch.setChecked(false);

        statsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateStatButtons((
                        isChecked
                                ? companionViewModel.getCompanionAbilityModifiers()
                                : companionViewModel.getCompanionAbilityValues()));
            }
        });
    }

    private void updateDisplayedValues() {
        updateStatButtons(companionViewModel.getCompanionAbilityValues());

        FirebaseCompanion companion = companionViewModel.getCompanion();
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

    private void updateStatButtons(Map<String, String> dummy) {
        for (Map.Entry<String, String> entry : dummy.entrySet())
            updateButtonLabel(entry.getKey(), entry.getKey(), entry.getValue());
    }

    private void updateButtonLabel(String key, String label, String value) {
        String dummy = label + " " + value;
        Objects.requireNonNull(buttons.get(key)).setText(dummy);
    }
}