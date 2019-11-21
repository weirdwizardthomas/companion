package com.via.android_development.companion.ui.companion_edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.ui.companions_overview.CompanionOverviewFragment;
import com.via.android_development.companion.utility.EnumTranslator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class EditCompanionFragment extends Fragment {

    private EditCompanionViewModel editCompanionViewModel;

    private EditText name;
    private Spinner raceSpinner;
    private Spinner professionSpinner;
    private Spinner alignmentSpinner;

    private List<AttributeWrapper> attributes;
    private Map<String, CheckBox> savingThrowCheckboxes;
    private Map<String, CheckBox> skillCheckboxes;

    private EditText background;
    private EditText traits;
    private EditText ideals;
    private EditText bonds;
    private EditText flaws;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        int id = getArguments() != null ? getArguments().getInt(CompanionOverviewFragment.ID_KEY) : -1;

        editCompanionViewModel = ViewModelProviders
                .of(this, new FirebaseEditCompanionViewModelFactory(getActivity().getApplication(), Integer.toString(id)))
                .get(EditCompanionViewModel.class);

        View root = inflater.inflate(R.layout.companion_edit_fragment, container, false);

        setHasOptionsMenu(true);
        initialiseLayout(root);

        LiveData<Task<DocumentSnapshot>> liveData = editCompanionViewModel.getdataSnapshotLiveData();
        liveData.observe(this, new Observer<Task<DocumentSnapshot>>() {
            @Override
            public void onChanged(Task<DocumentSnapshot> documentSnapshotTask) {
                if (documentSnapshotTask.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = documentSnapshotTask.getResult();
                    FirebaseCompanion companion = documentSnapshot.toObject(FirebaseCompanion.class);
                    if (companion != null) {
                        editCompanionViewModel.setCompanion(companion);
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
        inflater.inflate(R.menu.top_save_options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.saveButton) {
            updateAdventurerInstance();
            Navigation.findNavController(Objects.requireNonNull(getView())).popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateAdventurerInstance() {
        FirebaseCompanion firebaseCompanion = editCompanionViewModel.getCompanion();

        firebaseCompanion.setName(name.getText().toString());
        firebaseCompanion.setRace(raceSpinner.getSelectedItem().toString());
        firebaseCompanion.setProfession(professionSpinner.getSelectedItem().toString());
        firebaseCompanion.setAlignment(alignmentSpinner.getSelectedItem().toString());
        firebaseCompanion.setStrength(attributes.get(0).getValue());
        firebaseCompanion.setDexterity(attributes.get(1).getValue());
        firebaseCompanion.setConstitution(attributes.get(2).getValue());
        firebaseCompanion.setIntelligence(attributes.get(3).getValue());
        firebaseCompanion.setWisdom(attributes.get(4).getValue());
        firebaseCompanion.setCharisma(attributes.get(5).getValue());
        firebaseCompanion.setSavingThrowProficiencies(getProficiencies(savingThrowCheckboxes));
        firebaseCompanion.setSkillProficiencies(getProficiencies(skillCheckboxes));
        firebaseCompanion.setBackground(background.getText().toString());
        firebaseCompanion.setPersonalityTraits(traits.getText().toString());
        firebaseCompanion.setIdeals(ideals.getText().toString());
        firebaseCompanion.setBonds(bonds.getText().toString());
        firebaseCompanion.setFlaws(flaws.getText().toString());

        editCompanionViewModel.saveCompanion(firebaseCompanion);
    }

    private List<String> getProficiencies(Map<String, CheckBox> checkboxes) {
        List<String> proficiencies = new ArrayList<>();

        for (Map.Entry<String, CheckBox> entry : checkboxes.entrySet())
            if (entry.getValue().isChecked())
                proficiencies.add(entry.getKey());

        return proficiencies;
    }

    private void initialiseLayout(View root) {
        initialiseName(root);
        initialiseRaceSpinner(root);
        initialiseProfessionSpinner(root);
        initialiseAlignmentSpinner(root);
        initialiseAttributes(root);
        initialiseCheckboxes(root);
        initialiseBackstory(root);
    }

    private void initialiseAlignmentSpinner(View root) {
        alignmentSpinner = root.findViewById(R.id.alignmentSpinner);
        ArrayAdapter<String> alignments = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, EnumTranslator.getAllAlignmentsList());
        alignments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(alignments);
    }

    private void initialiseName(View root) {
        name = root.findViewById(R.id.nameInput);
    }

    private void initialiseCheckboxes(View root) {
        savingThrowCheckboxes = new HashMap<>();
        View parent = root.findViewById(R.id.proficiencies);

        savingThrowCheckboxes.put(getString(R.string.skillsAthletics), (CheckBox) parent.findViewById(R.id.athletics));
        savingThrowCheckboxes.put(getString(R.string.skillsAcrobatics), (CheckBox) parent.findViewById(R.id.acrobatics));
        savingThrowCheckboxes.put(getString(R.string.skillsSleightOfHand), (CheckBox) parent.findViewById(R.id.sleightOfHand));
        savingThrowCheckboxes.put(getString(R.string.skillsStealth), (CheckBox) parent.findViewById(R.id.stealth));
        savingThrowCheckboxes.put(getString(R.string.skillsArcana), (CheckBox) parent.findViewById(R.id.deception));
        savingThrowCheckboxes.put(getString(R.string.skillsHistory), (CheckBox) parent.findViewById(R.id.history));
        savingThrowCheckboxes.put(getString(R.string.skillsInvestigation), (CheckBox) parent.findViewById(R.id.investigation));
        savingThrowCheckboxes.put(getString(R.string.skillsNature), (CheckBox) parent.findViewById(R.id.nature));
        savingThrowCheckboxes.put(getString(R.string.skillsReligion), (CheckBox) parent.findViewById(R.id.religion));
        savingThrowCheckboxes.put(getString(R.string.skillsAnimalHandling), (CheckBox) parent.findViewById(R.id.animalHandling));
        savingThrowCheckboxes.put(getString(R.string.skillsInsight), (CheckBox) parent.findViewById(R.id.insight));
        savingThrowCheckboxes.put(getString(R.string.skillsMedicine), (CheckBox) parent.findViewById(R.id.medicine));
        savingThrowCheckboxes.put(getString(R.string.skillsPerception), (CheckBox) parent.findViewById(R.id.perception));
        savingThrowCheckboxes.put(getString(R.string.skillsSurvival), (CheckBox) parent.findViewById(R.id.survival));
        savingThrowCheckboxes.put(getString(R.string.skillsDeception), (CheckBox) parent.findViewById(R.id.deception));
        savingThrowCheckboxes.put(getString(R.string.skillsIntimidation), (CheckBox) parent.findViewById(R.id.intimidation));
        savingThrowCheckboxes.put(getString(R.string.skillsPerformance), (CheckBox) parent.findViewById(R.id.performance));
        savingThrowCheckboxes.put(getString(R.string.skillsPersuasion), (CheckBox) parent.findViewById(R.id.persuasion));

        skillCheckboxes = new HashMap<>();
        skillCheckboxes.put(getString(R.string.attributeStrength), (CheckBox) parent.findViewById(R.id.strength));
        skillCheckboxes.put(getString(R.string.attributeDexterity), (CheckBox) parent.findViewById(R.id.dexterity));
        skillCheckboxes.put(getString(R.string.attributeConstitution), (CheckBox) parent.findViewById(R.id.constitution));
        skillCheckboxes.put(getString(R.string.attributeIntelligence), (CheckBox) parent.findViewById(R.id.intelligence));
        skillCheckboxes.put(getString(R.string.attributeWisdom), (CheckBox) parent.findViewById(R.id.wisdom));
        skillCheckboxes.put(getString(R.string.attributeCharisma), (CheckBox) parent.findViewById(R.id.charisma));
    }

    private void initialiseProfessionSpinner(View root) {
        professionSpinner = root.findViewById(R.id.professionSpinner);
        ArrayAdapter<String> professions = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, EnumTranslator.getAllProfessionsList());
        professions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        professionSpinner.setAdapter(professions);
    }

    private void initialiseRaceSpinner(View root) {
        raceSpinner = root.findViewById(R.id.raceSpinner);
        ArrayAdapter<String> races = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, EnumTranslator.getAllRacesList());
        races.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(races);
    }

    private void initialiseAttributes(View root) {
        View attributeParent = root.findViewById(R.id.stats);
        attributes = new ArrayList<>();
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.strength), getString(R.string.attributeStrength)));
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.dexterity), getString(R.string.attributeDexterity)));
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.constitution), getString(R.string.attributeConstitution)));
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.intelligence), getString(R.string.attributeIntelligence)));
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.wisdom), getString(R.string.attributeWisdom)));
        attributes.add(new AttributeWrapper(attributeParent.findViewById(R.id.charisma), getString(R.string.attributeCharisma)));
    }

    private void initialiseBackstory(View root) {
        View backstoryParent = root.findViewById(R.id.backstory);
        background = backstoryParent.findViewById(R.id.backgroundContent);
        traits = backstoryParent.findViewById(R.id.traitsContent);
        ideals = backstoryParent.findViewById(R.id.idealsContent);
        bonds = backstoryParent.findViewById(R.id.bondsContent);
        flaws = backstoryParent.findViewById(R.id.flawsContent);
    }

    private void updateDisplayedValues() {
        FirebaseCompanion companion = editCompanionViewModel.getCompanion();
        name.setText(companion.getName());

        attributes.get(0).setPickerValue(companion.getStrength());
        attributes.get(1).setPickerValue(companion.getDexterity());
        attributes.get(2).setPickerValue(companion.getConstitution());
        attributes.get(3).setPickerValue(companion.getIntelligence());
        attributes.get(4).setPickerValue(companion.getWisdom());
        attributes.get(5).setPickerValue(companion.getCharisma());

        Set<String> dummy = new HashSet<>(companion.getSavingThrowProficiencies());
        for (Map.Entry<String, CheckBox> entry : savingThrowCheckboxes.entrySet()) {
            String key = entry.getKey();
            CheckBox checkbox = entry.getValue();
            checkbox.setChecked(dummy.contains(key));
        }

        dummy = new HashSet<>(companion.getSkillProficiencies());
        for (Map.Entry<String, CheckBox> entry : skillCheckboxes.entrySet()) {
            String key = entry.getKey();
            CheckBox checkbox = entry.getValue();
            checkbox.setChecked(dummy.contains(key));
        }

        raceSpinner.setSelection(EnumTranslator.getAllRacesList().indexOf(companion.getRace()));
        professionSpinner.setSelection(EnumTranslator.getAllProfessionsList().indexOf(companion.getProfession()));
        alignmentSpinner.setSelection(EnumTranslator.getAllAlignmentsList().indexOf(companion.getAlignment()));

        background.setText(companion.getBackground());
        traits.setText(companion.getPersonalityTraits());
        ideals.setText(companion.getIdeals());
        bonds.setText(companion.getBonds());
        flaws.setText(companion.getFlaws());
    }


}
