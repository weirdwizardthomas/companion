package com.via.android_development.companion.persistence.local;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CompanionRepository {
    private final CompanionDAO companionDAO;
    private final LiveData<List<Companion>> allCompanions;

    public CompanionRepository(Application application) {
        DnDDatabase database = DnDDatabase.getInstance(application);
        companionDAO = database.companionDAO();
        allCompanions = companionDAO.getAllCompanions();
    }

    public void insert(Companion companion) {
        //companionDAO.insert(companion);
        new InsertCompanionAsyncTask(companionDAO).execute(companion);
    }

    public void update(Companion companion) {
        new UpdateCompanionAsyncTask(companionDAO).execute(companion);
    }

    public void deleteAllCompanions() {
        new DeleteAllCompanionsAsyncTask(companionDAO).execute();
    }

    public LiveData<List<Companion>> getAllCompanions() {
        return allCompanions;
    }

    public LiveData<Companion> getCompanionById(int id) {
        return companionDAO.getCompanionById(id);
    }

    private static class InsertCompanionAsyncTask extends AsyncTask<Companion, Void, Void> {
        private final CompanionDAO companionDAO;

        private InsertCompanionAsyncTask(CompanionDAO companionDAO) {
            this.companionDAO = companionDAO;
        }

        @Override
        protected Void doInBackground(Companion... companions) {
            companionDAO.insert(companions[0]);
            return null;
        }
    }

    private static class UpdateCompanionAsyncTask extends AsyncTask<Companion, Void, Void> {
        private final CompanionDAO companionDAO;

        private UpdateCompanionAsyncTask(CompanionDAO companionDAO) {
            this.companionDAO = companionDAO;
        }

        @Override
        protected Void doInBackground(Companion... companions) {
            companionDAO.update(companions[0]);
            return null;
        }
    }

    private static class DeleteCompanionAsyncTask extends AsyncTask<Companion, Void, Void> {
        private final CompanionDAO companionDAO;

        private DeleteCompanionAsyncTask(CompanionDAO companionDAO) {
            this.companionDAO = companionDAO;
        }

        @Override
        protected Void doInBackground(Companion... companions) {
            companionDAO.delete(companions[0]);
            return null;
        }
    }

    private static class DeleteAllCompanionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private final CompanionDAO companionDAO;

        private DeleteAllCompanionsAsyncTask(CompanionDAO companionDAO) {
            this.companionDAO = companionDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            companionDAO.deleteAllCompanions();
            return null;
        }
    }

}
