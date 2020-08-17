package com.example.quedo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homew);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        HomeW.this.finish();
        System.exit(0);
    }
}