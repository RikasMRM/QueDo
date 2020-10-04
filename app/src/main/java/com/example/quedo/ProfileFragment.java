package com.example.quedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {

    TextView mUserName, mEmail;
    String userID;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button editPro= (Button) rootView.findViewById(R.id.btnEditPro);
        mUserName = rootView.findViewById(R.id.userName);
        mEmail = rootView.findViewById(R.id.email);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }


        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(ProfileFragment.this.getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                mUserName.setText(documentSnapshot.getString("userName"));
                mEmail.setText(documentSnapshot.getString("email"));
            }
        });

        editPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dsp = new Intent(ProfileFragment.this.getActivity(), EditProfile.class);
                startActivity(dsp);
            }
        });

        return rootView;
    }

}

