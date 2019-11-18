package com.via.android_development.companion.ui.companions_overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;

import java.util.List;


public class CompanionOverviewFragment extends Fragment implements CompanionAdapter.OnItemClickListener {

    public static final String ID_KEY = "companionId";
    public static final String IS_OPEN_FROM_OVERVIEW = "openFromInteview";

    private RecyclerView companionsRecyclerView;
    private CompanionAdapter companionsAdapter;
    private FloatingActionButton addCompanionButton;
    private CompanionOverviewViewModel companionOverviewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        companionOverviewViewModel = ViewModelProviders.of(this).get(CompanionOverviewViewModel.class);
        final View root = inflater.inflate(R.layout.companion_overview_fragment, container, false);

        initialiseRecyclerView(root);
        initialiseAddButton(root);

        observeCompanions();

        return root;
    }

    private void observeCompanions() {
        Observer<List<Companion>> companionObserver = new Observer<List<Companion>>() {
            @Override
            public void onChanged(List<Companion> companions) {
                companionsAdapter.setData(companions);
            }
        };

        companionOverviewViewModel.getAllCompanions().observe(this, companionObserver);
    }

    private void initialiseRecyclerView(final View root) {
        companionsAdapter = new CompanionAdapter(this);
        companionsRecyclerView = root.findViewById(R.id.companionOverview_recyclerView);
        companionsRecyclerView.hasFixedSize();
        companionsRecyclerView.setAdapter(companionsAdapter);
        companionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    private void initialiseAddButton(final View root) {
        addCompanionButton = root.findViewById(R.id.companionOverview_floatingActionButton);
        addCompanionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(IS_OPEN_FROM_OVERVIEW, true);
                Navigation.findNavController(root).navigate(R.id.overviewToCreateAction, bundle);
            }
        });
    }

    @Override
    public void onItemClick(Companion item) {
        Bundle bundle = new Bundle();
        bundle.putInt(CompanionOverviewFragment.ID_KEY, item.getId());
        Navigation.findNavController(this.getView()).navigate(R.id.overviewToDetailsAction, bundle);
    }
}
