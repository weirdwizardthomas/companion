package com.via.android_development.companion.ui.companions_overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.firebase.FirebaseCompanion;

import java.util.ArrayList;
import java.util.List;

import static com.via.android_development.companion.ui.companions_overview.CompanionOverviewFragment.ID_KEY;

public class CompanionAdapter extends RecyclerView.Adapter<CompanionAdapter.ViewHolder> {
    private List<FirebaseCompanion> data;
    private OnItemClickListener onItemClickListener;

    public CompanionAdapter(OnItemClickListener onItemClickListener) {
        data = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CompanionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_companion_brief, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanionAdapter.ViewHolder holder, int position) {
        FirebaseCompanion companion = data.get(position);

        holder.companionName.setText(companion.getName());
        holder.companionClass.setText(companion.getProfession());
        holder.companionLevel.setText(Integer.toString(companion.getTotalLevel()));
        holder.companionId = companion.getId();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<FirebaseCompanion> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView companionName;
        TextView companionLevel;
        TextView companionClass;
        ImageButton editButton;
        int companionId;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            companionName = itemView.findViewById(R.id.companionBrief_nameText);
            companionLevel = itemView.findViewById(R.id.companionBrief_totalLevel);
            companionClass = itemView.findViewById(R.id.companionBrief_classText);

            companionId = 0;

            editButton = itemView.findViewById(R.id.companionBrief_editCompanionButton);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ID_KEY, companionId);
                    Navigation.findNavController(itemView).navigate(R.id.overviewToEdit, bundle);
                }
            });


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FirebaseCompanion item = data.get(getAdapterPosition());
            onItemClickListener.onItemClick(item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FirebaseCompanion item);
    }
}
