package com.example.lw1;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lw1.DAO.ArchiveDAO;
import com.example.lw1.DAO.EnglishWordsDAO;
import com.example.lw1.database.AppDatabase;
import com.example.lw1.entities.Archive;
import com.example.lw1.entities.EnglishWords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordList extends AppCompatActivity {
    private AppDatabase db;
    private EnglishWordsDAO englishWordsDAO;
    private ArchiveDAO archiveDAO;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        db = App.getInstance().getDatabase();
        englishWordsDAO = db.englishWordsDAO();
        archiveDAO = db.archiveDAO();

        List<EnglishWords> all = englishWordsDAO.getAll();
        List<String> collect = new ArrayList<>();


        for(EnglishWords e: all){
            collect.add(" " + e.getEngWord() + " : " + e.getTranslate());
        }

        List<Archive> all2 = archiveDAO.getAll();
        collect.add("Архив");

        for(Archive a: all2){
            EnglishWords e = a.getWord();
            collect.add(" " + e.getEngWord() + " : " + e.getTranslate());
        }


        ListView listView = findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collect);


        listView.setAdapter(adapter);

    }
}
