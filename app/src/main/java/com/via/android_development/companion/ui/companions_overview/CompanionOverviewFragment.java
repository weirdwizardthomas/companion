package com.via.android_development.companion.ui.companions_overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;


public class CompanionOverviewFragment extends Fragment implements CompanionAdapter.OnItemClickListener {

    public static final String ID_KEY = "companionId";

    private RecyclerView companionsRecyclerView;
    private RecyclerView.Adapter companionsAdapter;

    private FloatingActionButton addCompanionButton;

    private View.OnClickListener onAddCompanionButtonClickListener;
    private CompanionOverviewViewModel companionOverviewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        companionOverviewViewModel = ViewModelProviders.of(this).get(CompanionOverviewViewModel.class);
        final View root = inflater.inflate(R.layout.companion_overview_fragment, container, false);

        companionOverviewViewModel.insert(new Companion("Tim"));
        companionOverviewViewModel.insert(new Companion("Tom"));

        initialiseRecyclerView(root);
        initialiseAddButton(root);

        return root;
    }

    private void initialiseRecyclerView(final View root) {
        companionsAdapter = new CompanionAdapter(companionOverviewViewModel.getAllCompanions(), this);
        companionsRecyclerView = root.findViewById(R.id.companionOverview_recyclerView);
        companionsRecyclerView.hasFixedSize();
        companionsRecyclerView.setAdapter(companionsAdapter);
        companionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    private void initialiseAddButton(final View root) {
        addCompanionButton = root.findViewById(R.id.companionOverview_floatingActionButton);
        onAddCompanionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.overviewToCreateAction);
            }
        };
        addCompanionButton.setOnClickListener(onAddCompanionButtonClickListener);
    }

    @Override
    public void onItemClick(Companion item) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID_KEY, item.getId());
        Navigation.findNavController(this.getView()).navigate(R.id.overViewToDetailsAction, bundle);
    }
}
