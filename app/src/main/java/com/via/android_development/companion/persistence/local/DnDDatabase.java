package com.via.android_development.companion.persistence.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Companion.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DnDDatabase extends RoomDatabase {
    private static DnDDatabase instance;

    public abstract CompanionDAO companionDAO();

    public static synchronized DnDDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, DnDDatabase.class, "dndb")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CompanionDAO companionDAO;

        private PopulateDbAsyncTask(DnDDatabase db) {
            companionDAO = db.companionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            companionDAO.insert(new Companion("A"));
            companionDAO.insert(new Companion("B"));
            companionDAO.insert(new Companion("C"));
            return null;
        }
    }

}


