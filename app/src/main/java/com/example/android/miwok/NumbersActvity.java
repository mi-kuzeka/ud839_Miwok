package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class NumbersActvity extends AppCompatActivity {
    ArrayList<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_actvity);

        //Initialize an array of words
        words = new ArrayList();
        Collections.addAll(words,
                "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
     }
}