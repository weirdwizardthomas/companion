package com.via.android_development.companion.persistence.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.via.android_development.companion.persistence.api.RetrofitServiceGenerator;
import com.via.android_development.companion.persistence.api.SpellAPI;
import com.via.android_development.companion.persistence.api.pojo.BriefSpell;
import com.via.android_development.companion.persistence.api.pojo.Spell;
import com.via.android_development.companion.persistence.api.response.AllBriefSpellsResponse;
import com.via.android_development.companion.persistence.api.response.SpellDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpellRepository {
    SpellAPI spellAPI;

    public SpellRepository() {
        spellAPI = RetrofitServiceGenerator.getSpellAPI();
    }

    public LiveData<Spell> getSpell(int index) {
        final MutableLiveData<Spell> spell = new MutableLiveData<>();

        Call<SpellDetailsResponse> call = spellAPI.getSpellDetails(index);
        call.enqueue(new Callback<SpellDetailsResponse>() {
            @Override
            public void onResponse(Call<SpellDetailsResponse> call, Response<SpellDetailsResponse> response) {
                Spell dummy = response.body().getSpell();
                spell.setValue(dummy);
            }

            @Override
            public void onFailure(Call<SpellDetailsResponse> call, Throwable t) {
                //TODO
                int i = 1;
            }
        });

        return spell;
    }

    public LiveData<List<BriefSpell>> getBriefSpells() {
        final MutableLiveData<List<BriefSpell>> dummy = new MutableLiveData<>();
        Call<AllBriefSpellsResponse> call = spellAPI.getAllBriefSpells();
        call.enqueue(new Callback<AllBriefSpellsResponse>() {
            @Override
            public void onResponse(Call<AllBriefSpellsResponse> call, Response<AllBriefSpellsResponse> response) {
                if (response.code() == 200) {
                    dummy.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<AllBriefSpellsResponse> call, Throwable t) {
                //TODO
                int i = 1;
            }
        });

        return dummy;
    }
}
