package com.example.lw1.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.lw1.entities.Archive;
import com.example.lw1.entities.EnglishWords;

import java.util.List;

@Dao
public interface ArchiveDAO {
    @Query("SELECT * FROM Archive")
    List<Archive> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Archive... archives);

    @Delete
    void delete(Archive archive);
}
