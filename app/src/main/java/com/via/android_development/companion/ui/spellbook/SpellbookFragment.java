package com.via.android_development.companion.ui.spellbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.api.pojo.BriefSpell;

import java.util.List;
import java.util.Objects;

public class SpellbookFragment extends Fragment implements SpellAdapter.OnItemClickListener {

    private static final String SPELL_INDEX_KEY = "spell_index";

    private SpellbookViewModel spellbookViewModel;
    private RecyclerView spellRecyclerView;
    private SpellAdapter spellAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        spellbookViewModel = ViewModelProviders.of(this).get(SpellbookViewModel.class);
        View root = inflater.inflate(R.layout.spellbook_fragment, container, false);

        initialiseRecyclerView(root);
        observeSpells();

        return root;
    }

    private void observeSpells() {
        Observer<List<BriefSpell>> spellObserver = new Observer<List<BriefSpell>>() {
            @Override
            public void onChanged(List<BriefSpell> briefSpells) {
                spellAdapter.setData(briefSpells);
            }
        };

        spellbookViewModel.getBriefSpells().observe(this, spellObserver);
    }

    private void initialiseRecyclerView(View root) {
        spellAdapter = new SpellAdapter(this);
        spellRecyclerView = root.findViewById(R.id.spellsRecyclerView);
        spellRecyclerView.hasFixedSize();
        spellRecyclerView.setAdapter(spellAdapter);
        spellRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClick(BriefSpell item) {

        String path = item.getUrl();
        String relativePath = path.substring(path.lastIndexOf("/") + 1);

        int index = Integer.parseInt(relativePath);
        Bundle bundle = new Bundle();
        bundle.putInt(SPELL_INDEX_KEY, index);
        Navigation.findNavController(Objects.requireNonNull(this.getView())).navigate(R.id.openSpell, bundle);
    }
}