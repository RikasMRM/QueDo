package com.example.quedo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DeleteAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deleteacc, container, false);

        Button btnDlt = (Button)rootView.findViewById(R.id.btndltacc);

        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.suc_toast, container, false);
                Toast toast = new Toast(DeleteAccount.this.getActivity());
                toast.setGravity(Gravity.FILL, 0, 0);
                toast.setView(layout);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return rootView;
    }

}