package com.example.am_fmcoder.ui.bindict;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BinDictViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BinDictViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}