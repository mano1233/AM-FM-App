package com.example.am_fmcoder.ui;

import android.os.Bundle;
import android.util.Log;

import com.example.am_fmcoder.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class Dictonaries {
    private static Map<String,Map<String, String>> letterdict = new HashMap<>();
    private static Map<String,Map<String, String>> bindict= new HashMap<>();
    public static void addLetterDict(Map<String,Map<String, String>> dict){
        String key = (String)dict.keySet().toArray()[0];
        if(!letterdict.containsKey(key)){
            letterdict.put(key,dict.get(key));
        }
    }
    public static void addbinDict(Map<String, Map<String, String>> dict){
        String key = (String)dict.keySet().toArray()[0];
        if(!bindict.containsKey(key)){
            bindict.put(key,dict.get(key));
        }
    }
    public static Map<String, String> getLetterDict(String key){
        if(letterdict.containsKey(key)){
            return letterdict.get(key);
        }
        return null;
    }
    public static Map<String, String> getBinDict(String key){
        if(bindict.containsKey(key)){
            return bindict.get(key);
        }
        return null;
    }
    public static Map<String,Map<String, String>> getLetterDict(){
        return letterdict;
    }
    public static Map<String,Map<String, String>> getBinDict(){
        return bindict;
    }

}
