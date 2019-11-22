package com.via.android_development.companion.ui.spell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.api.pojo.Spell;

import java.util.Objects;

public class SpellFragment extends Fragment {

    private SpellViewModel spellViewModel;

    private TextView name;
    private TextView description;
    private TextView higherLevels;
    private TextView range;
    private TextView duration;
    private TextView level;
    private TextView concentration;
    private TextView casting;
    private TextView components;

    private TextView higherLevelsLabel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        spellViewModel = ViewModelProviders.of(this).get(SpellViewModel.class);
        View root = inflater.inflate(R.layout.spell_fragment, container, false);
        int dummy = Objects.requireNonNull(getArguments()).getInt("spell_index");

        initialiseTextViews(root);

        adjustForMenu(root);

        observeSpell(dummy);
        return root;
    }

    private void adjustForMenu(View root) {
        int bottomNavigationHeight = Objects.requireNonNull(getActivity()).findViewById(R.id.nav_view).getHeight();

        ScrollView scrollView = root.findViewById(R.id.mainScrollView);
        ScrollView.LayoutParams layoutParams = (ScrollView.LayoutParams) scrollView.getLayoutParams();
        layoutParams.bottomMargin = bottomNavigationHeight;
        scrollView.setLayoutParams(layoutParams);
    }

    private void initialiseTextViews(View root) {
        name = root.findViewById(R.id.spellName);
        description = root.findViewById(R.id.descriptionContent);
        higherLevels = root.findViewById(R.id.higherLevelContent);
        range = root.findViewById(R.id.rangeContent);
        duration = root.findViewById(R.id.durationContent);
        level = root.findViewById(R.id.levelContent);
        concentration = root.findViewById(R.id.concentrationContent);
        casting = root.findViewById(R.id.castingContent);
        components = root.findViewById(R.id.componentsContent);


        higherLevelsLabel = root.findViewById(R.id.higherLevelLabel);
    }

    private void observeSpell(int dummy) {
        spellViewModel.getSpell(dummy).observe(this, new Observer<Spell>() {
            @Override
            public void onChanged(Spell spell) {
                updateDisplayedValues(spell);
            }
        });
    }

    private void updateDisplayedValues(Spell spell) {
        name.setText(spell.getName());
        description.setText(spell.getDescription().get(0));

        if (spell.getHigherLevel() == null) {
            higherLevels.setVisibility(View.GONE);
            higherLevelsLabel.setVisibility(View.GONE);
        } else {
            higherLevels.setVisibility(View.VISIBLE);
            higherLevelsLabel.setVisibility(View.VISIBLE);
            higherLevels.setText(spell.getHigherLevel().get(0));
        }

        range.setText(spell.getRange());
        duration.setText(spell.getDuration());
        level.setText(Integer.toString(spell.getLevel()));
        concentration.setText(spell.getConcentration());
        casting.setText(spell.getCasting_time());
        components.setText(spell.getComponentsAsString());
    }

}
