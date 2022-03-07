package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class NumbersActvity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_actvity);

        //Initialize an array of words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("one","lutti"),
                new Word("two", "otiiko"),
                new Word("three","tolookosu"),
                new Word("four", "oyyisa"),
                new Word("five", "massokka"),
                new Word("six","temmokka"),
                new Word("seven","kenekaku"),
                new Word("eight","kawinta"),
                new Word("nine","wo’e"),
                new Word("ten","na’aacha"));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
     }
}