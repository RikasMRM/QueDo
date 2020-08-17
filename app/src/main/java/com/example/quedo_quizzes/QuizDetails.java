package com.example.quedo_quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);
    }

    public void startquiz(View view) {
        Intent dsp = new Intent(QuizDetails.this,Questions.class);
        startActivity(dsp);
    }
}