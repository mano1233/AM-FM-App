package com.coder.am_fmcoder.ui.letterdict;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.coder.am_fmcoder.R;
import com.coder.am_fmcoder.ui.AddDict;
import com.coder.am_fmcoder.ui.Dictonaries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class LetterFragment extends Fragment {

    private Button[] letter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_letterdict, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab_create_letter);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddDict.class);
                intent.putExtra("is_standalone", true);
                startActivity(intent);
            }
        });
        View letterlayout = root.findViewById(R.id.letterdict_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] names = letterdicts.keySet().toArray();
        letter = new Button[letterdicts.size()];
        for (int i = 0; i < letter.length; i++) {
            letter[i] = new Button(getActivity());
            letter[i].setText((String) names[i]);
            letter[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letter[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            letter[i].setLayoutParams(lp);
            ((LinearLayout) letterlayout).addView(letter[i]);

        }
        return root;
    }
}