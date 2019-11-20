package com.via.android_development.companion.ui.spellbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.via.android_development.companion.persistence.api.pojo.BriefSpell;
import com.via.android_development.companion.persistence.api.repository.SpellRepository;

import java.util.List;

public class SpellbookViewModel extends AndroidViewModel {
    private final SpellRepository spellRepository;

    public SpellbookViewModel(Application application) {
        super(application);
        spellRepository = new SpellRepository();
    }

    public LiveData<List<BriefSpell>> getBriefSpells() {
        return spellRepository.getBriefSpells();
    }

}