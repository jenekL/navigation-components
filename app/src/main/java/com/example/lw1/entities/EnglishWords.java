package com.example.lw1.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EnglishWords {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String engWord;
    private String translate;
    private int counter = 0;

    public EnglishWords(String engWord, String translate) {
        this.engWord = engWord;
        this.translate = translate;
    }

    public EnglishWords() {
    }

    public String getEngWord() {
        return engWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
