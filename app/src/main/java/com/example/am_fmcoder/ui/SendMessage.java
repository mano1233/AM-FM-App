package com.example.am_fmcoder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.example.am_fmcoder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class SendMessage extends AppCompatActivity implements View.OnClickListener {

    private Button[] bins;
    private String binkey = "";
    private String letterkey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        FloatingActionButton fab = findViewById(R.id.fabsend);
        fab.setOnClickListener(this);
        Intent intent = getIntent();
        letterkey = intent.getStringExtra("dictname");
        View homelayout = findViewById(R.id.send_layout);
        Map<String, Map<String, String>> bindicts = Dictonaries.getBinDict();
        Object[] names = bindicts.keySet().toArray();
        bins = new Button[bindicts.size()];
        for (int i = 0; i < bins.length; i++) {
            bins[i] = new Button(this);
            bins[i].setBackgroundColor(Color.LTGRAY);
            bins[i].setText((String) names[i]);
            bins[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bins[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bins[i].setLayoutParams(lp);
            ((LinearLayout) homelayout).addView(bins[i]);
            bins[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof FloatingActionButton){
            sendMessage();
        }
        else {
            setMessage();
        }
    }

    private void sendMessage(){
        String  mod = String.valueOf(((ToggleButton)findViewById(R.id.AM_FM)).getText());
        String massage = ((EditText)findViewById(R.id.send_info)).getText().toString();
        Intent intent = new Intent(this, PlaySound.class);
        intent.putExtra("mod", mod);
        intent.putExtra("message", massage);
        intent.putExtra("letterkey", letterkey);
        intent.putExtra("binkey", binkey);
        startActivity(intent);

    }

    private void setMessage(){
        for (Button b : bins){
            if (b.isPressed()){
                binkey = String.valueOf(b.getText());
                b.setBackgroundColor(Color.WHITE);
            }
            else{
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
