package com.example.am_fmcoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.am_fmcoder.ui.Dictonaries;

import java.util.Map;

public class PlaySound extends AppCompatActivity implements View.OnClickListener{

    private String mod;
    private String message;
    private Map<String, String> letterdict;
    private Map<String, String> bindict;
    Transmitter transmit;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
        Intent intent = getIntent();
        mod = intent.getStringExtra("mod");
        message =intent.getStringExtra("message");
        letterdict = Dictonaries.getLetterDict(intent.getStringExtra("letterkey"));
        bindict = Dictonaries.getBinDict(intent.getStringExtra("binkey")) ;

        System.out.println(bindict);
        System.out.println(letterdict);
        System.out.println(mod);


        Button play = findViewById(R.id.play_sound);
        play.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        transmit = new Transmitter(this.getApplicationContext(), letterdict, bindict, mod);
        Log.d("message = ", message);
        player =  transmit.Transmit(message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        transmit.Release(player);
    }
}
