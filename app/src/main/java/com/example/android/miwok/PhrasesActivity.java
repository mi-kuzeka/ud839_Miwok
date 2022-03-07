package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize an array of words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("Where are you going?","minto wuksus"),
                new Word("What is your name?", "tinnә oyaase'nә"),
                new Word("My name is...","oyaaset..."),
                new Word("How are you feeling?", "michәksәs?"),
                new Word("I’m feeling good.", "kuchi achit"),
                new Word("Are you coming?","әәnәs'aa?"),
                new Word("Yes, I’m coming.","hәә’ әәnәm"),
                new Word("I’m coming.","әәnәm"),
                new Word("Let’s go.","yoowutis"),
                new Word("Come here.","әnni'nem"));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}