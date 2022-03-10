package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
                new Word("red", "weṭeṭṭi", R.drawable.color_red),
                new Word("green", "chokokki", R.drawable.color_green),
                new Word("brown", "ṭakaakki", R.drawable.color_brown),
                new Word("gray", "ṭopoppi", R.drawable.color_gray),
                new Word("black", "kululli", R.drawable.color_black),
                new Word("white", "kelelli", R.drawable.color_white),
                new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow),
                new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));

        int backgroundColor = ContextCompat.getColor(this, R.color.category_colors);
        WordAdapter adapter = new WordAdapter(this, words, backgroundColor);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}