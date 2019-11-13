package com.via.android_development.companion.persistence.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {
    public static final String BASE_URL = "http://www.dnd5eapi.co/api/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static SpellAPI spellAPI = retrofit.create(SpellAPI.class);

    public static SpellAPI getSpellAPI() {
        return spellAPI;
    }
}
