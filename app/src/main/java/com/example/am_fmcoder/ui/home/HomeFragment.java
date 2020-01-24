package com.example.am_fmcoder.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.am_fmcoder.R;
import com.example.am_fmcoder.ui.SendMessage;
import com.example.am_fmcoder.ui.AddDict;
import com.example.am_fmcoder.ui.Dictonaries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    View root;
    FloatingActionButton fab;
    private Button[] letters;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        View homelayout = root.findViewById(R.id.home_layout);
        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] names = letterdicts.keySet().toArray();
        letters = new Button[letterdicts.size()];
        for(int i = 0; i < letters.length; i++) {
            letters[i] = new Button(getActivity());
            letters[i].setText((String)names[i]);
            letters[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letters[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            letters[i].setLayoutParams(lp);
            ((LinearLayout) homelayout).addView(letters[i]);
            letters[i].setOnClickListener(this);

        }
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab)
            createLetter();
        else {
            sendMessage();
        }

    }

    private  void createLetter(){
        Intent intent = new Intent(getActivity(), AddDict.class);
        intent.putExtra("is_standalone", false);
        startActivity(intent);
    }
    private void sendMessage(){
        Intent intent = new Intent(getActivity(), SendMessage.class);
        for (Button b : letters){
            if (b.isPressed()){
                intent.putExtra("dictname", String.valueOf(b.getText()));
                startActivity(intent);
            }
        }
    }


}