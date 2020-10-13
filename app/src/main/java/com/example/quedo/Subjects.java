package com.example.quedo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Subjects extends Fragment {
    private RecyclerView mRecyclerView;
    SubjectAdapter subjectAdapter;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    TextView SubjectsTitle;
    ProgressBar progressBar;
    String userID;
    String result2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_subjects, container, false);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference();

        Button btnAddSub = rootView.findViewById(R.id.addSub);
        SubjectsTitle = rootView.findViewById(R.id.SubjectsTitle);
        progressBar = rootView.findViewById(R.id.progressBarTable);
        mRecyclerView = rootView.findViewById(R.id.subRecycle);


//        Query query2 = myRef.child("Subjects").child("-MIo4jk0qE5lE1GhXI0r").orderByChild("subName");
//
//        mRecyclerView.setLayoutManager(
//                new LinearLayoutManager(getActivity()));
//
//                FirebaseRecyclerOptions<Subject> options =
//                        new FirebaseRecyclerOptions.Builder<Subject>()
//                                .setQuery(query2, Subject.class)
//                                .build();
//
//                subjectAdapter = new SubjectAdapter(options);
//
//                mRecyclerView.setAdapter(subjectAdapter);



        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewSubject()).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {

                result2 = bundle.getString("timetableID");

                Query query = myRef.child("Timetables").child(userID).child(result2).child("timetableName");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String tableTitle = dataSnapshot.getValue(String.class);
                        SubjectsTitle.setText(tableTitle);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

                Query query2 = myRef.child("Subjects").child(result2).orderByChild("subName");

                mRecyclerView.setLayoutManager(
                        new LinearLayoutManager(getActivity()));

                FirebaseRecyclerOptions<Subject> options =
                        new FirebaseRecyclerOptions.Builder<Subject>()
                                .setQuery(query2, Subject.class)
                                .build();

                subjectAdapter = new SubjectAdapter(options);

                mRecyclerView.setAdapter(subjectAdapter);

                subjectAdapter.startListening();


                Bundle keytime = new Bundle();
                keytime.putString("timetableID", result2);
                getParentFragmentManager().setFragmentResult("requestKey2", keytime);
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        subjectAdapter.startListening();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStop()
    {
        super.onStop();
//        subjectAdapter.stopListening();
    }

    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);
    }

}
