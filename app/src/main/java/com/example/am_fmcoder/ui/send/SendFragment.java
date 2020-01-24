package com.example.am_fmcoder.ui.send;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.am_fmcoder.R;
import com.example.am_fmcoder.ui.Dictonaries;

import java.util.Map;

public class SendFragment extends Fragment {

    private Button[] letter;
    private Button[] bin;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        View letterlayout = root.findViewById(R.id.send_letter_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] letternames = letterdicts.keySet().toArray();
        letter = new Button[letterdicts.size()];
        for (int i = 0; i < letter.length; i++) {
            letter[i] = new Button(getActivity());
            letter[i].setText((String) letternames[i]);
            letter[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letter[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            letter[i].setLayoutParams(lp);
            ((LinearLayout) letterlayout).addView(letter[i]);

        }
        View binarylayout = root.findViewById(R.id.send_bin_layout);
        Map<String, Map<String, String>> bindicts = Dictonaries.getBinDict();
        Object[] binnames = letterdicts.keySet().toArray();
        bin = new Button[letterdicts.size()];
        for (int i = 0; i < bin.length; i++) {
            bin[i] = new Button(getActivity());
            bin[i].setText((String) binnames[i]);
            bin[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bin[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bin[i].setLayoutParams(lp);
            ((LinearLayout) binarylayout).addView(bin[i]);

        }
        return root;
    }
}