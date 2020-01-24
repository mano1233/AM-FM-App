package com.example.am_fmcoder.ui.send;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.am_fmcoder.R;
import com.example.am_fmcoder.ui.Dictonaries;
import com.example.am_fmcoder.ui.PlaySound;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class SendFragment extends Fragment implements View.OnClickListener {

    private Button[] letter;
    private Button[] bin;
    private String binkey = "";
    private String letterkey = "";
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_send, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab_send_fragment);
        fab.setOnClickListener(this);
        View letterlayout = root.findViewById(R.id.send_letter_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] letternames = letterdicts.keySet().toArray();
        letter = new Button[letterdicts.size()];
        for (int i = 0; i < letter.length; i++) {
            letter[i] = new Button(getActivity());
            letter[i].setText((String) letternames[i]);
            letter[i].setBackgroundColor(Color.LTGRAY);
            letter[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letter[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            letter[i].setLayoutParams(lp);
            ((LinearLayout) letterlayout).addView(letter[i]);
            letter[i].setOnClickListener(this);

        }
        View binarylayout = root.findViewById(R.id.send_bin_layout);
        Map<String, Map<String, String>> bindicts = Dictonaries.getBinDict();
        Object[] binnames = bindicts.keySet().toArray();
        bin = new Button[letterdicts.size()];
        for (int i = 0; i < bin.length; i++) {
            bin[i] = new Button(getActivity());
            bin[i].setText((String) binnames[i]);
            bin[i].setBackgroundColor(Color.LTGRAY);
            bin[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bin[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bin[i].setLayoutParams(lp);
            ((LinearLayout) binarylayout).addView(bin[i]);
            bin[i].setOnClickListener(this);

        }
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof FloatingActionButton) {
            sendMessage();
        } else {
            setMessage();
        }
    }

    private void sendMessage() {
        String mod = String.valueOf(((ToggleButton) root.findViewById(R.id.AM_FM_fragment)).getText());
        String massage = ((EditText) root.findViewById(R.id.send_info_fragment)).getText().toString();
        Intent intent = new Intent(getActivity(), PlaySound.class);
        intent.putExtra("mod", mod);
        intent.putExtra("message", massage);
        intent.putExtra("letterkey", letterkey);
        intent.putExtra("binkey", binkey);
        startActivity(intent);

    }

    private void setMessage() {
        boolean is_letter = true;
        for (Button b : bin) {
            if (b.isPressed()) {
                is_letter = false;
                binkey = String.valueOf(b.getText());
                b.setBackgroundColor(Color.WHITE);
            }
        }
        if (is_letter) {
            for (Button b : letter) {
                if (b.isPressed()) {
                    is_letter = true;
                    letterkey = String.valueOf(b.getText());
                    b.setBackgroundColor(Color.WHITE);
                } else {
                    b.setBackgroundColor(Color.LTGRAY);
                }
            }
        } else {
            for (Button b : bin) {
                if (!b.isPressed()) {
                    b.setBackgroundColor(Color.LTGRAY);
                }
            }
        }
    }
}