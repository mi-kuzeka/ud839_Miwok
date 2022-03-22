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
public class NumbersFragment extends Fragment {
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

    public NumbersFragment() {
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
                new Word("one", "lutti",
                        R.raw.number_one, R.drawable.number_one),
                new Word("two", "otiiko",
                        R.raw.number_two, R.drawable.number_two),
                new Word("three", "tolookosu",
                        R.raw.number_three, R.drawable.number_three),
                new Word("four", "oyyisa",
                        R.raw.number_four, R.drawable.number_four),
                new Word("five", "massokka",
                        R.raw.number_five, R.drawable.number_five),
                new Word("six", "temmokka",
                        R.raw.number_six, R.drawable.number_six),
                new Word("seven", "kenekaku",
                        R.raw.number_seven, R.drawable.number_seven),
                new Word("eight", "kawinta",
                        R.raw.number_eight, R.drawable.number_eight),
                new Word("nine", "wo’e",
                        R.raw.number_nine, R.drawable.number_nine),
                new Word("ten", "na’aacha",
                        R.raw.number_ten, R.drawable.number_ten));

        int backgroundColor = ContextCompat.getColor(getActivity(), R.color.category_numbers);
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
        pauseMediaPlayer();
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