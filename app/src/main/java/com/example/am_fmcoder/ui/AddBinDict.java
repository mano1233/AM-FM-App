package com.example.am_fmcoder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.am_fmcoder.MainActivity;
import com.example.am_fmcoder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.example.am_fmcoder.ui.Extended.isAccepted;
import static java.lang.Integer.toBinaryString;

public class AddBinDict extends AppCompatActivity {
    private EditText[] bin = null;
    private Map<String, String> bindict = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bin_dict);
        bin = new EditText[27];
        View binlayout = findViewById(R.id.binlayout);
        for(int i = 0; i < bin.length; i++) {
            bin[i] = new EditText(this);
            bin[i].setHint(Extended.getChar(i) + " " );
            bin[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bin[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bin[i].setLayoutParams(lp);
            ((LinearLayout) binlayout).addView(bin[i]);
        }
        FloatingActionButton fab = findViewById(R.id.fabbin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButton();

            }
        });
    }
    private void onButton(){
        Intent intent = new Intent(this, MainActivity.class);
        bindict = new HashMap<>();
        Map<String, Map<String, String>> dictfin = new HashMap<>();
        String dictname = "מילון בינארי דיפולטי";
        if (isAccepted(bin)) {
            for (int i=0; i<27; i++) {
                String temp = bin[i].getText().toString();
                String binary = new String(new char[5-temp.length()]).replace("\0", "0");
                bindict.put(String.valueOf(bin[i].getHint()), binary);
            }
            EditText dictName = findViewById(R.id.dictname);
            dictname = String.valueOf(dictName.getText());
        }
        else {
            for(int i=0; i<27; i++){
                String temp = toBinaryString(i+1);
                String binary = temp + new String(new char[5-temp.length()]).replace("\0", "0");
                bindict.put(String.valueOf(bin[i].getHint()), binary);
            }
        }
        dictfin.put(dictname, bindict);
        Dictonaries.addbinDict(dictfin);
        startActivity(intent);
    }
}
