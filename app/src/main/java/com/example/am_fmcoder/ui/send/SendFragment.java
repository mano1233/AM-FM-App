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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.am_fmcoder.R;
import com.example.am_fmcoder.ui.Dictonaries;
import com.example.am_fmcoder.ui.PlaySound;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class SendFragment extends Fragment implements View.OnClickListener{

    private Button[] letter;
    private Button[] bin;
    private String binkey = "";
    private String letterkey = "";
    private View root;
    TextView textView_fm;
    TextView textView_am;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_send, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab_send_fragment);
        fab.setOnClickListener(this);
        View letterlayout = root.findViewById(R.id.send_letter_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] letternames = letterdicts.keySet().toArray();
        letter = new Button[letterdicts.size()];
        View binarylayout = root.findViewById(R.id.send_bin_layout);
        Map<String, Map<String, String>> bindicts = Dictonaries.getBinDict();
        Object[] binnames = bindicts.keySet().toArray();
        bin = new Button[letterdicts.size()];
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
        letterkey = (String) letternames[0];
        letter[0].setBackgroundColor(Color.WHITE);
        binkey = (String) binnames[0];
        bin[0].setBackgroundColor(Color.WHITE);
        final SeekBar seekbar_am = root.findViewById(R.id.seekBar_am);
        textView_fm = root.findViewById(R.id.textView_fm);
        seekbar_am.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                                  @Override
                                                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                                      int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                                                      switch (progress){
                                                          case 0:
                                                              textView_fm.setText("440");
                                                              break;
                                                          case 1:
                                                              textView_fm.setText("660");
                                                              break;
                                                          case 2:
                                                              textView_fm.setText("880");
                                                              break;
                                                          case 3:
                                                              textView_fm.setText("1000");
                                                              break;
                                                          case 4:
                                                              textView_fm.setText("1220");
                                                              break;
                                                          case 5:
                                                              textView_fm.setText("1440");
                                                              break;
                                                      }
                                                      textView_fm.setX(seekBar.getX() + val  + seekBar.getThumbOffset() / 2);
                                                      float offset = seekbar_am.getY() - 100f;
                                                      textView_fm.setY(offset); //just added a value set this properly using screen with height aspect ratio , if you do not set it by default it will be there below seek bar

                                                  }

                                                  @Override
                                                  public void onStartTrackingTouch(SeekBar seekBar) {

                                                  }

                                                  @Override
                                                  public void onStopTrackingTouch(SeekBar seekBar) {

                                                  }
                                              });
        final SeekBar seekbar_fm = root.findViewById(R.id.seekBar_fm);
        textView_am = root.findViewById(R.id.textView_am);
        seekbar_fm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                switch (progress){
                    case 0:
                        textView_am.setText("0.5");
                        break;
                    case 1:
                        textView_am.setText("0.6");
                        break;
                    case 2:
                        textView_am.setText("0.7");
                        break;
                    case 3:
                        textView_am.setText("0.8");
                        break;
                    case 4:
                        textView_am.setText("0.9");
                        break;
                    case 5:
                        textView_am.setText("1");
                        break;
                }
                textView_am.setX(seekBar.getX() + val  + seekBar.getThumbOffset() / 2);
                float offset = seekbar_fm.getY() - 100f;
                textView_am.setY(offset); //just added a value set this properly using screen with height aspect ratio , if you do not set it by default it will be there below seek bar

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        intent.putExtra("freq", String.valueOf(textView_fm.getText()));
        Log.d("fick", "freq:" + String.valueOf(textView_fm.getText()));
        intent.putExtra("amp", String.valueOf(textView_am.getText()));
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