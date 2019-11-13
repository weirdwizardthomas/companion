package com.via.android_development.companion.persistence.api;

import com.via.android_development.companion.persistence.api.response.AllBriefSpellsResponse;
import com.via.android_development.companion.persistence.api.response.SpellCountResponse;
import com.via.android_development.companion.persistence.api.response.SpellDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpellAPI {

    @GET("spells")
    Call<SpellCountResponse> getSpellCount();

    @GET("spells")
    Call<AllBriefSpellsResponse> getAllBriefSpells();

    @GET("spells/{index}")
    Call<SpellDetailsResponse> getSpellDetails(@Path("index") int index);
}
