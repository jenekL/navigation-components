package com.example.lw1.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.lw1.converters.EngWordsConverter;

@Entity
public class Archive {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @TypeConverters({EngWordsConverter.class})
    private EnglishWords word;

    public Archive() {
    }

    public EnglishWords getWord() {
        return word;
    }

    public void setWord(EnglishWords word) {
        this.word = word;
    }

    public Archive(long id, EnglishWords word) {
        this.id = id;
        this.word = word;
    }

    public Archive(EnglishWords word) {
        this.word = word;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
