package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import static com.basgeekball.awesomevalidation.utility.RegexTemplate.*;

import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class AddNewTask extends Fragment {

    EditText mtask, mdescription, mdate;
    Button savebutton , cancelbutton;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private AwesomeValidation awesomeValidation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.todo_addnewtask, container, false);

       mtask = rootView.findViewById(R.id.task);
       mdescription = rootView.findViewById(R.id.description);
       mdate = rootView.findViewById(R.id.date);
       savebutton = rootView.findViewById(R.id.saveBtn);
       cancelbutton = rootView.findViewById(R.id.CancelBtn);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();

            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddNewTask.this.getActivity(), "Successfully Cancelled", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllTasks()).addToBackStack(null).commit();

            }
        });

        return rootView;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        //Validation 1st time
//        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.task,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",  R.string.thisFieldError);
//        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.description, NOT_EMPTY,  R.string.thisFieldError);
//        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.date, NOT_EMPTY,  R.string.thisFieldError);
//    }

    private void validateForm() {
        String ntask = mtask.getText().toString();
        String ndescription = mdescription.getText().toString();
        String ndate = mdate.getText().toString();

        //Validation
        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.task,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",  R.string.thisFieldError);
        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.description, NOT_EMPTY,  R.string.thisFieldError);
        awesomeValidation.addValidation(AddNewTask.this.getActivity(), R.id.date, NOT_EMPTY,  R.string.thisFieldError);

        Task task =  new Task(ntask, ndescription, ndate);
        final Random random = new Random();

        if (awesomeValidation.validate()) {
            //insert to Database
            reference.child("Tasks").child(Integer.toString(random.nextInt(100))).setValue(task);
            Toast.makeText(AddNewTask.this.getActivity(), "Task has been inserted successfully", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllTasks()).addToBackStack(null).commit();
        }
    }
}


