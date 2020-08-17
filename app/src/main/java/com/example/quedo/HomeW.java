package com.example.quedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homew);

        Button loginBtn = (Button)findViewById(R.id.btnhomelogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeW.this, userLogin.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        HomeW.this.finish();
        System.exit(0);
    }
}