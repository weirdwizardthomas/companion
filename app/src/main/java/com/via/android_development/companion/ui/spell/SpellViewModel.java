package com.via.android_development.companion.ui.spell;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.via.android_development.companion.persistence.api.pojo.Spell;
import com.via.android_development.companion.persistence.api.repository.SpellRepository;

class SpellViewModel extends AndroidViewModel {
    private final SpellRepository spellRepository;

    public SpellViewModel(Application application) {
        super(application);
        spellRepository = new SpellRepository();
    }

    public LiveData<Spell> getSpell(int index) {
        return spellRepository.getSpell(index);
    }

}
