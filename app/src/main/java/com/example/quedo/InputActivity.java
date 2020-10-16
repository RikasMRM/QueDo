package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout titleLayout, descLayout;
    Button saveBtn;
    String title, desc;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        titleLayout = findViewById(R.id.textInputLayout);
        descLayout = findViewById(R.id.textInputLayout2);
        saveBtn = findViewById(R.id.saveTextBtnId);

        myRef = FirebaseDatabase.getInstance().getReference("QuickNote");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("QuickNote");

        saveBtn.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.saveTextBtnId:

                title = titleLayout.getEditText().getText().toString();
                desc = descLayout.getEditText().getText().toString();


                Map<String, String> quickNote = new HashMap<>();
                quickNote.put("title", title);
                quickNote.put("desc", desc);

                myRef.push().setValue(quickNote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (title.matches("")) {
                            Toast.makeText(InputActivity.this, "Title Required", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            if (task.isSuccessful()) {
                                Toast.makeText(InputActivity.this, "Note Added", Toast.LENGTH_SHORT).show();

                                titleLayout.getEditText().setText("");
                                descLayout.getEditText().setText("");
                            } else {
                                Toast.makeText(InputActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                break;
        }
    }
}