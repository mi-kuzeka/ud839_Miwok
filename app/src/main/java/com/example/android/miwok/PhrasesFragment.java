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
public class PhrasesFragment extends Fragment {
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

    public PhrasesFragment() {
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
                new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going),
                new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name),
                new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is),
                new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling),
                new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good),
                new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming),
                new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming),
                new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming),
                new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go),
                new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        int backgroundColor = ContextCompat.getColor(getActivity(), R.color.category_phrases);
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