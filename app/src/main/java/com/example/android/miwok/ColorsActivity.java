package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Initialize an array of colors words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("red", "weṭeṭṭi", R.raw.color_red, R.drawable.color_red),
                new Word("green", "chokokki", R.raw.color_green, R.drawable.color_green),
                new Word("brown", "ṭakaakki", R.raw.color_brown, R.drawable.color_brown),
                new Word("gray", "ṭopoppi", R.raw.color_gray, R.drawable.color_gray),
                new Word("black", "kululli", R.raw.color_black, R.drawable.color_black),
                new Word("white", "kelelli", R.raw.color_white, R.drawable.color_white),
                new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow),
                new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        int backgroundColor = ContextCompat.getColor(this, R.color.category_colors);
        WordAdapter adapter = new WordAdapter(this, words, backgroundColor);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}