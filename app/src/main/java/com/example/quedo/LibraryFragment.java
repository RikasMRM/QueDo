package com.example.quedo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class LibraryFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        Button btnBooks= (Button) rootView.findViewById(R.id.booksbtn);
        Button btnAddDocu = (Button) rootView.findViewById(R.id.add_docbtn);
        Button btnReqDocu = (Button) rootView.findViewById(R.id.req_docbtn);
        Button btnPapers = (Button) rootView.findViewById(R.id.pastpapersbtn);
        Button btnResearch = (Button) rootView.findViewById(R.id.researchbtn);

        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BooksFragment()).addToBackStack(null).commit();
            }

        });

        btnAddDocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddDocuFragment()).addToBackStack(null).commit();
            }

        });

        btnReqDocu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReqDocuFragment()).addToBackStack(null).commit();
            }

        });

        btnPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.comingsoon, container, false);
                Toast toast = new Toast(LibraryFragment.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }

        });

        btnResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.comingsoon, container, false);
                Toast toast = new Toast(LibraryFragment.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }

        });

        return rootView;
    }
}
