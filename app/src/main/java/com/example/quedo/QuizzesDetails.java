package com.example.quedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizzesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_details);
    }

    public void startquiz(View view) {
        Intent dsp = new Intent(QuizzesDetails.this, Questions.class);
        startActivity(dsp);
    }
}