package com.example.quedo;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

public class ExampleItem {

    private String mText1;
    private String mText2;

    public ExampleItem(String text1, String text2) {
        mText1 = text1;
        mText2 = text2;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

}

