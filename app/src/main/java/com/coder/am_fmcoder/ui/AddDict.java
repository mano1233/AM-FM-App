package com.coder.am_fmcoder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import com.coder.am_fmcoder.MainActivity;
import com.coder.am_fmcoder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.coder.am_fmcoder.ui.Extended.isAccepted;

public class AddDict extends AppCompatActivity {
    private EditText[] letters = null;
    private Map<String, String> letterdict = null;
    Boolean is_standalone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dict);
        Intent intent = getIntent();
        letters = new EditText[27];
        View letterlayout = findViewById(R.id.letterlayout);
        for(int i = 0; i < letters.length; i++) {
            letters[i] = new EditText(this);
            letters[i].setHint(Extended.getChar(i) + " " );
            letters[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letters[i].setId(i);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            letters[i].setLayoutParams(lp);
            ((LinearLayout) letterlayout).addView(letters[i]);
        }
        FloatingActionButton fab = findViewById(R.id.fabletter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButton();

            }
        });



    }
    private void onButton(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        letterdict = new HashMap<>();
        Map<String, Map<String, String>> dictfin = new HashMap<>();
        String dictname;
        if (isAccepted(letters)) {
            for (EditText text : letters) {
                letterdict.put(String.valueOf(text.getHint()),text.getText().toString());
            }
            EditText dictName = findViewById(R.id.dictname);
            dictname = String.valueOf(dictName.getText());
            dictfin.put(dictname,letterdict);
            Log.d("fuck", dictfin.toString());
            Dictonaries.addLetterDict(dictfin);
        }
        else {
            for (int i = 0; i < 27; i++) {
                EditText text = letters[i];
                if (text.getText().toString().equals("")) {
                    String str = String.valueOf(text.getHint());
                    letterdict.put(String.valueOf(text.getHint()), str.substring(0,str.length()-1));
                }
                else {
                    letterdict.put(String.valueOf(text.getHint()), text.getText().toString());
                }
            }
            EditText dictName = findViewById(R.id.dictname);
            dictname = String.valueOf(dictName.getText());
            dictfin.put(dictname,letterdict);
            Log.d("fuck", dictfin.toString());
            Dictonaries.addLetterDict(dictfin);
        }
        startActivity(intent);

    }


}
