package com.via.android_development.companion.ui.spellbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.api.pojo.BriefSpell;

import java.util.ArrayList;
import java.util.List;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {
    private List<BriefSpell> data;
    private final OnItemClickListener onItemClickListener;

    public SpellAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        data = new ArrayList<>();
    }

    public void setData(List<BriefSpell> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SpellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_spell_brief, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellAdapter.ViewHolder holder, int position) {
        BriefSpell briefSpell = data.get(position);
        holder.spellName.setText(briefSpell.getName());
        holder.url = briefSpell.getUrl();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        String url;
        final TextView spellName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            spellName = itemView.findViewById(R.id.ability_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BriefSpell item = data.get(getAdapterPosition());
            onItemClickListener.onItemClick(item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BriefSpell item);
    }
}
