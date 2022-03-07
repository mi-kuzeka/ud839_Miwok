package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize an array of colors words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("red","weṭeṭṭi"),
                new Word("green", "chokokki"),
                new Word("brown","ṭakaakki"),
                new Word("gray", "ṭopoppi"),
                new Word("black", "kululli"),
                new Word("white","kelelli"),
                new Word("dusty yellow","ṭopiisә"),
                new Word("mustard yellow","chiwiiṭә"));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}