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

public class FamilyActivity extends AppCompatActivity {
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

        //Initialize an array of words
        ArrayList<Word> words = new ArrayList();
        Collections.addAll(words,
                new Word("father", "әpә", R.raw.family_father, R.drawable.family_father),
                new Word("mother", "әṭa", R.raw.family_mother, R.drawable.family_mother),
                new Word("son", "angsi", R.raw.family_son, R.drawable.family_son),
                new Word("daughter", "tune", R.raw.family_daughter, R.drawable.family_daughter),
                new Word("older brother", "taachi", R.raw.family_older_brother, R.drawable.family_older_brother),
                new Word("younger brother", "chalitti", R.raw.family_younger_brother, R.drawable.family_younger_brother),
                new Word("older sister", "teṭe", R.raw.family_older_sister, R.drawable.family_older_sister),
                new Word("younger sister", "kolliti", R.raw.family_younger_sister, R.drawable.family_younger_sister),
                new Word("grandmother", "ama", R.raw.family_grandmother, R.drawable.family_grandmother),
                new Word("grandfather", "paapa", R.raw.family_grandfather, R.drawable.family_grandfather));

        int backgroundColor = ContextCompat.getColor(this, R.color.category_family);
        WordAdapter adapter = new WordAdapter(this, words, backgroundColor);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
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