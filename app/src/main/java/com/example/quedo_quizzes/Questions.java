package com.example.quedo_quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
    }

    public void results(View view) {
        Intent dsp = new Intent(Questions.this,Results.class);
        startActivity(dsp);
    }
}