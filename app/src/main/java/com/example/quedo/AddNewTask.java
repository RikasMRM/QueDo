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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddNewTask extends Fragment {

    EditText mtask, description, date;
    Button savebutton , cancelbutton;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.todo_addnewtask, container, false);

       mtask = rootView.findViewById(R.id.task);
       description = rootView.findViewById(R.id.description);
       date = rootView.findViewById(R.id.date);
       savebutton = rootView.findViewById(R.id.saveBtn);
       cancelbutton = rootView.findViewById(R.id.CancelBtn);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        final Random random = new Random();



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ntask = mtask.getText().toString();
                String ndescription = description.getText().toString();
                String ndate = date.getText().toString();

                Task task =  new Task(ntask, ndescription, ndate);
                reference.child("Tasks").child(Integer.toString(random.nextInt(100))).setValue(task);
                Toast.makeText(AddNewTask.this.getActivity(), "Task has been inserted successfully", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllTasks()).addToBackStack(null).commit();

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


}