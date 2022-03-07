package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize an array of words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("father","әpә"),
                new Word("mother", "әṭa"),
                new Word("son","angsi"),
                new Word("daughter", "tune"),
                new Word("older brother", "taachi"),
                new Word("younger brother","chalitti"),
                new Word("older sister","teṭe"),
                new Word("younger sister","kolliti"),
                new Word("grandmother","ama"),
                new Word("grandfather","paapa"));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}