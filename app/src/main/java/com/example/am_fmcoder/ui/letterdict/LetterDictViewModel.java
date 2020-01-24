package com.example.am_fmcoder.ui.letterdict;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LetterDictViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LetterDictViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}