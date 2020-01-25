package com.example.am_fmcoder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.am_fmcoder.R;
import com.example.am_fmcoder.Transmitter;
import com.example.am_fmcoder.ui.Dictonaries;

import java.util.Map;

public class PlaySound extends AppCompatActivity implements View.OnClickListener{

    private String mod;
    private String message;
    private Map<String, String> letterdict;
    private Map<String, String> bindict;
    private String amp;
    private String freq;
    private Transmitter transmit;
    private static Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
        Intent intent = getIntent();
        mod = intent.getStringExtra("mod");
        message = intent.getStringExtra("message");
        amp = intent.getStringExtra("amp");
        freq = intent.getStringExtra("freq");
        letterdict = Dictonaries.getLetterDict(intent.getStringExtra("letterkey"));
        bindict = Dictonaries.getBinDict(intent.getStringExtra("binkey")) ;
        play = findViewById(R.id.play_sound);
        play.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                play();
            }
        }).start();
        TextView t1 = findViewById(R.id.textViewloading);
        t1.setText("טוען");

    }
    protected void play(){
        transmit = new Transmitter(this.getApplicationContext(), letterdict, bindict, mod);
        transmit.Transmit(message, freq, amp);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (transmit != null){
            transmit.Release();
        }

    }
    public static void setPause(){
        play.setText("השהה");
    }
}
