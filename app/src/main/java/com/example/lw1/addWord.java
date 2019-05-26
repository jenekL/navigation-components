package com.example.lw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lw1.DAO.EnglishWordsDAO;
import com.example.lw1.database.AppDatabase;
import com.example.lw1.entities.EnglishWords;

public class addWord extends AppCompatActivity {
    EditText eng;
    EditText ru;
    Button button;

    private AppDatabase db;
    private EnglishWordsDAO englishWordsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        eng = findViewById(R.id.engEnter);
        ru = findViewById(R.id.rusEnter);
        button = findViewById(R.id.addbtn);

        db = App.getInstance().getDatabase();
        englishWordsDAO = db.englishWordsDAO();


        button.setOnClickListener(v-> {
            englishWordsDAO.insert(new EnglishWords(eng.getText().toString(), ru.getText().toString()));
            Toast.makeText(addWord.this, "Перевод добавлен", Toast.LENGTH_SHORT).show();
        });



    }
}
