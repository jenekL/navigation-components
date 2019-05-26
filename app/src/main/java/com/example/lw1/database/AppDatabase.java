package com.example.lw1.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.lw1.DAO.ArchiveDAO;
import com.example.lw1.DAO.EnglishWordsDAO;
import com.example.lw1.entities.Archive;
import com.example.lw1.entities.EnglishWords;

@Database(entities = {EnglishWords.class, Archive.class}, version = 3)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract EnglishWordsDAO englishWordsDAO();
    public abstract ArchiveDAO archiveDAO();

}
