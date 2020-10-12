package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Timetables extends Fragment {
    private RecyclerView mRecyclerView;
    TimetableeAdapter timetableeAdapter;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    String userID;
    TextView tablename, timeTitle;
    CardView timetableLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_timetables, container, false);
        View rootView2 = inflater.inflate(R.layout.timetable_card, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("Timetables");

        Query query = myRef.child(userID);

        Button addTable = rootView.findViewById(R.id.addTable);
        mRecyclerView = rootView.findViewById(R.id.timetableRecycle);
        tablename = rootView2.findViewById(R.id.table_num);
        timeTitle = rootView.findViewById(R.id.timeTitle);
        timetableLayout = rootView2.findViewById(R.id.timetableLayout);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this.getActivity()));

        FirebaseRecyclerOptions<Timetable> options =
                new FirebaseRecyclerOptions.Builder<Timetable>()
                        .setQuery(query, Timetable.class)
                        .build();

        timetableeAdapter = new TimetableeAdapter(options);

        mRecyclerView.setAdapter(timetableeAdapter);


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String key = timetableeAdapter.getRef(position).getKey();

                        Bundle result = new Bundle();
                        result.putString("timetableID", key);
                        Subjects subjects = new Subjects();
                        subjects.setArguments(result);
                        getParentFragmentManager().setFragmentResult("requestKey", result);

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Subjects()).addToBackStack(null).commit();


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                })
        );

        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddNewTimetable()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        timetableeAdapter.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        timetableeAdapter.stopListening();
    }

    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);
    }

}