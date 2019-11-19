package com.via.android_development.companion.ui.companions_overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;
import com.via.android_development.companion.persistence.local.Companion;

import java.util.ArrayList;
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

        getDataFromFirebase();

        return root;
    }

    private void getDataFromFirebase() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Adventurers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Companion> companions = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FirebaseCompanion firebaseCompanion = documentSnapshot.toObject(FirebaseCompanion.class);
                    companions.add(new Companion(firebaseCompanion));
                }
                companionsAdapter.setData(companions);
            }
        });
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
    }
}
