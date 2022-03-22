package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FamilyFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                pauseMediaPlayer();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                resumeMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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

        int backgroundColor = ContextCompat.getColor(getActivity(), R.color.category_family);
        WordAdapter adapter = new WordAdapter(getActivity(), words, backgroundColor);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) mMediaPlayer.release();
        mMediaPlayer = null;
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    private void resumeMediaPlayer() {
        if (mMediaPlayer != null) mMediaPlayer.start();
    }

    private void pauseMediaPlayer() {
        if (mMediaPlayer != null) mMediaPlayer.pause();
    }
}