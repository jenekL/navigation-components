package com.example.lw1.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.lw1.entities.EnglishWords;

import java.util.List;

@Dao
public interface EnglishWordsDAO {
    @Query("SELECT engWord from englishWords where id = :id")
    String getWord(long id);

    @Query("SELECT translate from englishWords where id = :id")
    String getTranslate(long id);

    @Query("SELECT translate from englishWords where engWord = :engWord")
    String getTranslateFromWord(String engWord);

    @Query("SELECT engWord from englishWords where engWord LIKE :translate")
    String getWordFromTranslate(String translate);

    @Query("SELECT * FROM englishWords")
    List<EnglishWords> getAll();

    @Query("UPDATE englishwords set counter = counter + 1  where engWord LIKE :eng")
    void updateCounter(String eng);

    @Query("Select counter from englishwords  where engWord LIKE :eng")
    int getCounter(String eng);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EnglishWords ... englishWords);

    @Delete
    void delete(EnglishWords englishWords);

}
