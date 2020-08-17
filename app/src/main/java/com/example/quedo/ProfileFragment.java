package com.example.quedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Button editPro= (Button) rootView.findViewById(R.id.btnEditPro);

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
