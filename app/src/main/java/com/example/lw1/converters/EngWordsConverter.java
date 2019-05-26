package com.example.lw1.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.lw1.entities.EnglishWords;

public class EngWordsConverter {
    @TypeConverter
    public static String fromWord(EnglishWords englishWords) {
        return englishWords.getEngWord() + ":" + englishWords.getTranslate();
    }

    @TypeConverter
    public static EnglishWords toWord(String data) {
        String[] split = data.split(":");
        EnglishWords englishWords = new EnglishWords(split[0], split[1]);
        return englishWords;
    }
}
