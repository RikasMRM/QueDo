package com.example.quedo;

import android.content.Intent;
import android.os.Bundle;
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

        ImageButton btnLibrary= (ImageButton) rootView.findViewById(R.id.reminderbtn);
        ImageButton btnLibrary= (ImageButton) rootView.findViewById(R.id.librarybtn);
        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HomeFragment.this.getActivity(), LibraryFragment.class);
//                HomeFragment.this.startActivity(intent);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllTasks()).addToBackStack(null).commit();
                        new LibraryFragment()).addToBackStack(null).commit();
            }

        });

        return rootView;
    }
}
