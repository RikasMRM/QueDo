package com.example.quedo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton btnReminder= (ImageButton) rootView.findViewById(R.id.reminderbtn);
        ImageButton btnLibrary= (ImageButton) rootView.findViewById(R.id.librarybtn);
        ImageButton btnTable= (ImageButton) rootView.findViewById(R.id.timetablebtn);
        ImageButton btnQuiz= (ImageButton) rootView.findViewById(R.id.quizzesbtn);

        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LibraryFragment()).addToBackStack(null).commit();
            }

        });

        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllTasks()).addToBackStack(null).commit();
            }

        });

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Timetables()).addToBackStack(null).commit();
            }

        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsp = new Intent(HomeFragment.this.getActivity(), Questions.class);
                startActivity(dsp);
            }

        });

        return rootView;
    }
}
