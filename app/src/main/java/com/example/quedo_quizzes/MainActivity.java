package com.example.quedo_quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quizdetails(View view) {
        Intent dsp = new Intent(MainActivity.this,QuizDetails.class);
        startActivity(dsp);
    }




}