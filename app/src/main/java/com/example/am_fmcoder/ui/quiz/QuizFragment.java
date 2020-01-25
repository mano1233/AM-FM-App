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

public class QuizFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private QuizViewModel quizViewModel;
    MediaPlayer player;
    Button play;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        play = root.findViewById(R.id.play_Riddle);
        play.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (String.valueOf(play.getText())){
            case "השהה":
                Pause();
                break;
            case "המשך":
                unPause();
                break;
            case "נגן חידה":
                player = MediaPlayer.create(getActivity(), R.raw.riddle);
                player.setOnPreparedListener(this);
                player.start();
                break;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (player == null){
            return;
        }
        player.pause();
        player.stop();
    }
    protected void unPause(){
        play.setText("השהה");
        player.start();
    }
    protected void Pause(){
        play.setText("המשך");
        player.pause();
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        play.setText("השהה");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        play.setText("נגן מחדש");
        player.stop();
        player.release();
    }
}