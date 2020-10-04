package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AllTasks extends Fragment {

    private RecyclerView recyclerView;
    taskAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    private FloatingActionButton addnewBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.todo_alltasks, container, false);

        addnewBtn = rootView.findViewById(R.id.fab);

        addnewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddNewTask()).addToBackStack(null).commit();
            }
        });

        mbase
                = FirebaseDatabase.getInstance().getReference().child("Tasks");

        Query query = mbase.orderByChild("task");

        recyclerView = rootView.findViewById(R.id.recyclerView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this.getActivity()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Task> options
                = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(query, Task.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new taskAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
    }
}
