package com.example.quedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizzesHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_home);
    }

    public void quizdetails(View view) {
        Intent dsp = new Intent(QuizzesHome.this,QuizzesDetails.class);
        startActivity(dsp);
    }
}