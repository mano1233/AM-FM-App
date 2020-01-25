package com.example.am_fmcoder.ui.quiz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.am_fmcoder.R;

public class QuizFragment extends Fragment implements View.OnClickListener {

    private QuizViewModel quizViewModel;
    MediaPlayer player;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        Button play = root.findViewById(R.id.play_Riddle);
        play.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        player = MediaPlayer.create(getActivity(), R.raw.riddle);
        player.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        player.pause();
        player.stop();
    }
}