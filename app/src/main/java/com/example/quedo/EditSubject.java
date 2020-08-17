package com.example.quedo;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditSubject extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_editsub, container, false);

        Spinner classTypeSpin = (Spinner) rootView.findViewById(R.id.classType);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(EditSubject.this.getActivity(), R.array.classTypeSpin,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        classTypeSpin.setAdapter(staticAdapter);

        Button btnUpdate = (Button)rootView.findViewById(R.id.update);
        Button btnDelete = (Button)rootView.findViewById(R.id.delete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.suc_toast, container, false);
                Toast toast = new Toast(EditSubject.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.suc_toast, container, false);
                Toast toast = new Toast(EditSubject.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return rootView;
    }
}