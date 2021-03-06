package com.coder.am_fmcoder.ui.bindict;

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
import com.coder.am_fmcoder.ui.AddBinDict;
import com.coder.am_fmcoder.ui.Dictonaries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class BinFragment extends Fragment{

    private Button[] bin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bindict, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab_create_bin);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddBinDict.class);
                startActivity(intent);
            }
        });
        View binarylayout = root.findViewById(R.id.binarydict_layout);
        Map<String, Map<String, String>> bindicts = Dictonaries.getBinDict();
        Object[] names = bindicts.keySet().toArray();
        bin = new Button[bindicts.size()];
        for (int i = 0; i < bin.length; i++) {
            bin[i] = new Button(getActivity());
            bin[i].setText((String) names[i]);
            bin[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bin[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bin[i].setLayoutParams(lp);
            ((LinearLayout) binarylayout).addView(bin[i]);

        }
        return root;
    }
}

