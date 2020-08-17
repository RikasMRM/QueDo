package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewSubject extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_newsub, container, false);

//        TextView timetable = (TextView) rootView.findViewById(R.id.table_num);
//
//        timetable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new Subjects()).addToBackStack(null).commit();
//            }
//        });

        Spinner classTypeSpin = (Spinner) rootView.findViewById(R.id.classType);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(NewSubject.this.getActivity(), R.array.classTypeSpin,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        classTypeSpin.setAdapter(staticAdapter);

        Button btnSubmit = (Button)rootView.findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.suc_toast, container, false);
                Toast toast = new Toast(NewSubject.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return rootView;
    }
}