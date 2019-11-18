package com.via.android_development.companion.ui.companion_create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.ui.companions_overview.CompanionOverviewFragment;
import com.via.android_development.companion.utility.enums.Attribute;
import com.via.android_development.companion.utility.enums.Profession;
import com.via.android_development.companion.utility.enums.Race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//LANDING - NAME, STATS, RACE & PROFESSION
public class CompanionCreateFragment1 extends Fragment {

    private EditText name;

    private NumberPicker racePicker;
    private NumberPicker professionPicker;

    private List<AttributeWrapper> items;

    private Button resetButton;
    private Button nextArrowButton;

    private Companion dummy;

    private CompanionCreateViewModel companionCreateViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.companion_create_fragment_1, container, false);

        companionCreateViewModel = ViewModelProviders.of(this).get(CompanionCreateViewModel.class);

        boolean openedFromOverview = getArguments().getBoolean(CompanionOverviewFragment.IS_OPEN_FROM_OVERVIEW);

        if (openedFromOverview) {
            //wipe and create a new character
            companionCreateViewModel.deleteAllCompanions();
            companionCreateViewModel.insert(new Companion());
        }

        initialiseLayout(root);
        observeCompanions();
        return root;
    }

    private void initialiseLayout(View root) {
        name = root.findViewById(R.id.name);
        initialiseStatBlock(root);
        initialisePickers(root);
        initialiseBottomButtons(root);
    }

    private void initialiseBottomButtons(final View root) {
        resetButton = root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(""); //reset the name input

                for (AttributeWrapper item : items)
                    item.reset();

                racePicker.setValue(CompanionCreateViewModel.DEFAULT_RACE_INDEX);
                professionPicker.setValue(CompanionCreateViewModel.DEFAULT_PROFESSION_INDEX);
            }
        });

        nextArrowButton = root.findViewById(R.id.next_button);
        nextArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCompanionInstance();
                Navigation.findNavController(root).navigate(R.id.create1_to_create2);
            }
        });

    }

    private void initialiseStatBlock(View root) {
        items = new ArrayList<>();

        items.add(new AttributeWrapper(root.findViewById(R.id.strength), String.valueOf(Attribute.STRENGTH)));
        items.add(new AttributeWrapper(root.findViewById(R.id.dexterity), String.valueOf(Attribute.DEXTERITY)));
        items.add(new AttributeWrapper(root.findViewById(R.id.constitution), String.valueOf(Attribute.CONSTITUTION)));
        items.add(new AttributeWrapper(root.findViewById(R.id.intelligence), String.valueOf(Attribute.INTELLIGENCE)));
        items.add(new AttributeWrapper(root.findViewById(R.id.wisdom), String.valueOf(Attribute.WISDOM)));
        items.add(new AttributeWrapper(root.findViewById(R.id.charisma), String.valueOf(Attribute.CHARISMA)));
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

    private void observeCompanions() {
        Observer<List<Companion>> companionsObserver = new Observer<List<Companion>>() {
            @Override
            public void onChanged(List<Companion> companions) {
                if (companions.size() != 0) {
                    dummy = companions.get(0);
                    updateDisplayedValues();
                }
            }
        };

        companionCreateViewModel.getAllCompanions().observe(this, companionsObserver);
    }

    private void saveCompanionInstance() {
        dummy.setName(name.getText().toString());
        dummy.setRace(CompanionCreateViewModel.getRaceByIndex(racePicker.getValue() - 1)); //offset for the difference between number picker and array index
        dummy.setProfession(CompanionCreateViewModel.getProfessionByIndex(professionPicker.getValue() - 1));//offset for the difference between number picker and array index
        dummy.setStrength(items.get(0).getPickerValue());
        dummy.setDexterity(items.get(1).getPickerValue());
        dummy.setConstitution(items.get(2).getPickerValue());
        dummy.setIntelligence(items.get(3).getPickerValue());
        dummy.setWisdom(items.get(4).getPickerValue());
        dummy.setCharisma(items.get(5).getPickerValue());

        companionCreateViewModel.update(dummy);
    }

    private void updateDisplayedValues() {
        name.setText(dummy.getName());
        updatePickerValue(racePicker, dummy.getRace(), String.valueOf(Race.HUMAN));
        updatePickerValue(professionPicker, dummy.getProfession(), String.valueOf(Profession.FIGHTER));
        items.get(0).setAttributeValue(dummy.getStrength());
        items.get(1).setAttributeValue(dummy.getDexterity());
        items.get(2).setAttributeValue(dummy.getConstitution());
        items.get(3).setAttributeValue(dummy.getIntelligence());
        items.get(4).setAttributeValue(dummy.getWisdom());
        items.get(5).setAttributeValue(dummy.getCharisma());
    }

    private void updatePickerValue(NumberPicker picker, String item, String defaultValue) {
        String[] pickerValues = picker.getDisplayedValues();
        int index = Arrays.asList(pickerValues).indexOf(item == null ? defaultValue : item) + 1;
        picker.setValue(index);
    }
}
