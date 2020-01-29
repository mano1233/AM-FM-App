package com.coder.am_fmcoder.ui;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class Extended {
    public static final char[] EXTENDED = {0x05D0, 0x05D1, 0x05D2,
            0x05D3, 0x05D4, 0x05D5, 0x05D6, 0x05D7, 0x05D8, 0x05D9, 0x05DA,
            0x05DB, 0x05DC, 0x05DD, 0x05DE, 0x05DF, 0x05E0, 0x05E1, 0x05E2,
            0x05E3, 0x05E4, 0x05E5, 0x05E6, 0x05E7, 0x05E8, 0x05E9, 0x05EA};

    public static final char getAscii(int code) {
        return EXTENDED[code];
    }

    public static final char getChar(int code) {
        return getAscii(code);
    }

    public static final Map<String, String> buildMap(String[] keys, String[] values){
        Map<String, String> dict = new HashMap<String, String>();
        for (int i = 0; i < keys.length; i++){
            dict.put(keys[i], values[i]);
        }
        return dict;
    }
    // Checks if every letter key has a value
    public static final Boolean isAccepted(EditText[] dict){
        for (EditText text: dict) {
            if (text.getText().length() == 0){
                return false;
            }

        }
        return true;
    }
}