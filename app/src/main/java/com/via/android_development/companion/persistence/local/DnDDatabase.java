package com.via.android_development.companion.persistence.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Companion.class}, version = 5, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DnDDatabase extends RoomDatabase {

    private static DnDDatabase instance;

    public abstract CompanionDAO companionDAO();

    public static synchronized DnDDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), DnDDatabase.class, "dndb")
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }
}


