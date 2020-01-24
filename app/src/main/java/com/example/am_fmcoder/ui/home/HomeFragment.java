package com.example.am_fmcoder.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.am_fmcoder.MainActivity;
import com.example.am_fmcoder.R;
import com.example.am_fmcoder.ui.AddBinDict;
import com.example.am_fmcoder.ui.AddDict;
import com.example.am_fmcoder.ui.Extended;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        fab = root.findViewById(R.id.fab);fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddDict.class);
                startActivity(intent);
            }
            }
        );
        return root;
    }


}