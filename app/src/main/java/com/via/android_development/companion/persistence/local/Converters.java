package com.via.android_development.companion.persistence.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Converters {
    @TypeConverter
    public static List<String> listFromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static Set<String> setFromString(String value) {
        Type listType = new TypeToken<HashSet<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String stringFromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static String stringFromSet(Set<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}