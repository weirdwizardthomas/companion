package com.via.android_development.companion.ui.companion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.via.android_development.companion.R;
import com.via.android_development.companion.utility.DieRoller;

import java.util.HashMap;
import java.util.Map;

public class CompanionFragment extends Fragment {

    private Map<String, Button> buttons;

    private CompanionViewModel companionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companionViewModel = ViewModelProviders.of(this).get(CompanionViewModel.class);
        View root = inflater.inflate(R.layout.companion_fragment, container, false);

        adjustForMenu(root);
        initialiseButtons(root);
        addOnClickListenersToButtons();

        return root;
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
        buttons.put(getString(R.string.attributeStrength), (Button) root.findViewById(R.id.strength));
        buttons.put(getString(R.string.attributeDexterity), (Button) root.findViewById(R.id.dexterity));
        buttons.put(getString(R.string.attributeConstitution), (Button) root.findViewById(R.id.constitution));
        buttons.put(getString(R.string.attributeIntelligence), (Button) root.findViewById(R.id.intelligence));
        buttons.put(getString(R.string.attributeWisdom), (Button) root.findViewById(R.id.wisdom));
        buttons.put(getString(R.string.attributeCharisma), (Button) root.findViewById(R.id.charisma));

        //Skills
        buttons.put(getString(R.string.skillsAthletics), (Button) root.findViewById(R.id.athletics));
        buttons.put(getString(R.string.skillsAcrobatics), (Button) root.findViewById(R.id.acrobatics));
        buttons.put(getString(R.string.skillsSleightOfHand), (Button) root.findViewById(R.id.sleightOfHand));
        buttons.put(getString(R.string.skillsStealth), (Button) root.findViewById(R.id.stealth));
        buttons.put(getString(R.string.skillsArcana), (Button) root.findViewById(R.id.arcana));
        buttons.put(getString(R.string.skillsHistory), (Button) root.findViewById(R.id.history));
        buttons.put(getString(R.string.skillsInvestigation), (Button) root.findViewById(R.id.investigation));
        buttons.put(getString(R.string.skillsNature), (Button) root.findViewById(R.id.nature));
        buttons.put(getString(R.string.skillsReligion), (Button) root.findViewById(R.id.religion));
        buttons.put(getString(R.string.skillsAnimalHandling), (Button) root.findViewById(R.id.animalHandling));
        buttons.put(getString(R.string.skillsInsight), (Button) root.findViewById(R.id.insight));
        buttons.put(getString(R.string.skillsMedicine), (Button) root.findViewById(R.id.medicine));
        buttons.put(getString(R.string.skillsPerception), (Button) root.findViewById(R.id.perception));
        buttons.put(getString(R.string.skillsSurvival), (Button) root.findViewById(R.id.survival));
        buttons.put(getString(R.string.skillsDeception), (Button) root.findViewById(R.id.deception));
        buttons.put(getString(R.string.skillsIntimidation), (Button) root.findViewById(R.id.intimidation));
        buttons.put(getString(R.string.skillsPerformance), (Button) root.findViewById(R.id.performance));
        buttons.put(getString(R.string.skillsPersuasion), (Button) root.findViewById(R.id.persuasion));
    }

    private void adjustForMenu(View root) {
        int bottomNavigationHeight = getActivity().findViewById(R.id.nav_view).getHeight();

        ScrollView scrollView = root.findViewById(R.id.mainScrollView);
        ScrollView.LayoutParams layoutParams = (ScrollView.LayoutParams) scrollView.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        scrollView.setLayoutParams(layoutParams);
    }
}