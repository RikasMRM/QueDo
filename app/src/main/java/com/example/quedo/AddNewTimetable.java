package com.example.quedo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.basgeekball.awesomevalidation.utility.RegexTemplate.*;
import static com.example.quedo.SignUp.TAG;

public class AddNewTimetable extends Fragment {

    EditText timetableNameEdit;
    Button btnsubmit;

    FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private AwesomeValidation awesomeValidation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.add_new_time_table_activity, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        timetableNameEdit = rootView.findViewById(R.id.timetableNameEdit);
        btnsubmit = rootView.findViewById(R.id.btnsubmit);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validate = validateForm();
                if (validate) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.suc_toast, container, false);
                    Toast toast = new Toast(AddNewTimetable.this.getActivity());
                    toast.setGravity(Gravity.FILL, 0, 0);
                    toast.setView(layout);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Timetables()).addToBackStack(null).commit();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        awesomeValidation.addValidation(AddNewTimetable.this.getActivity(), R.id.timetableNameEdit, NOT_EMPTY, R.string.timeNameError);
    }

    private Boolean validateForm() {
        String timetableName = timetableNameEdit.getText().toString();

        final String userID = mAuth.getCurrentUser().getUid();
        Boolean validateStatus = false;

        Timetable timetable = new Timetable(timetableName);

        if (awesomeValidation.validate()) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("Timetables").child(userID).push().setValue(timetable);

            validateStatus = true;
        }
        return validateStatus;
    }

}
