package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class NumbersActvity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize an array of words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("one", "lutti", R.drawable.number_one),
                new Word("two", "otiiko", R.drawable.number_two),
                new Word("three", "tolookosu", R.drawable.number_three),
                new Word("four", "oyyisa", R.drawable.number_four),
                new Word("five", "massokka", R.drawable.number_five),
                new Word("six", "temmokka", R.drawable.number_six),
                new Word("seven", "kenekaku", R.drawable.number_seven),
                new Word("eight", "kawinta", R.drawable.number_eight),
                new Word("nine", "wo’e", R.drawable.number_nine),
                new Word("ten", "na’aacha", R.drawable.number_ten));

        int backgroundColor = ContextCompat.getColor(this, R.color.category_numbers);
        WordAdapter adapter = new WordAdapter(this, words, backgroundColor);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}