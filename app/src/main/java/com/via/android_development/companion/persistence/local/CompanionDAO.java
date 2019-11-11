package com.via.android_development.companion.persistence.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompanionDAO {

    @Insert
    void insert(Companion companion);

    @Update
    void update(Companion companion);

    @Delete
    void delete(Companion companion);

    @Query("DELETE FROM Companion")
    void deleteAllCompanions();

    @Query("SELECT * FROM Companion")
    LiveData<List<Companion>> getAllCompanions();

    @Query("SELECT * FROM Companion WHERE id=:id")
    Companion getCompanionById(int id);
}
