package com.example.am_fmcoder.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.am_fmcoder.MainActivity;
import com.example.am_fmcoder.R;
import com.example.am_fmcoder.SendMessage;
import com.example.am_fmcoder.ui.AddBinDict;
import com.example.am_fmcoder.ui.AddDict;
import com.example.am_fmcoder.ui.Dictonaries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class GalleryFragment extends Fragment{

    private GalleryViewModel galleryViewModel;
    private Button[] bin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
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
        View binarylayout = root.findViewById(R.id.binary_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getBinDict();
        Object[] names = letterdicts.keySet().toArray();
        bin = new Button[letterdicts.size()];
        Log.d("fuck", "onCreateView: ");
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

