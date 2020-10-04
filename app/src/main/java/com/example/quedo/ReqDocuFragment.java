package com.example.quedo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

import static com.basgeekball.awesomevalidation.utility.RegexTemplate.NOT_EMPTY;

public class ReqDocuFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private DatabaseReference myRef;
    private AwesomeValidation awesomeValidation;

    EditText mdocNameReq, mdocAuthReq;
    Spinner mdocTypeReq;
    Button btnSubmit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_req_docu, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        mdocTypeReq = rootView.findViewById(R.id.docTypeReq);
        mdocNameReq = rootView.findViewById(R.id.docNameReq);
        mdocAuthReq = rootView.findViewById(R.id.docAuthReq);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(ReqDocuFragment.this.getActivity(), R.array.docTypeSpin,
                        android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mdocTypeReq.setAdapter(staticAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validate = validateForm();
                if (validate) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.req_suc, container, false);
                    Toast toast = new Toast(ReqDocuFragment.this.getActivity());
                    toast.setGravity(Gravity.FILL, 0, 0);
                    toast.setView(layout);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new LibraryFragment()).addToBackStack(null).commit();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        awesomeValidation.addValidation(ReqDocuFragment.this.getActivity(), R.id.docNameReq, NOT_EMPTY, R.string.docNameError);
        awesomeValidation.addValidation(ReqDocuFragment.this.getActivity(), R.id.docAuthReq, NOT_EMPTY, R.string.docAuthError);
        awesomeValidation.addValidation(ReqDocuFragment.this.getActivity(), R.id.docTypeReq, NOT_EMPTY, R.string.docLinkError);
    }

    private boolean validateForm() {
        String docName = mdocNameReq.getText().toString();
        String docAuth = mdocAuthReq.getText().toString();
        String docType = mdocTypeReq.getSelectedItem().toString();
        final String userID = mAuth.getCurrentUser().getUid();

        Boolean validateStatus = false;

        RequestDocs requestDocs = new RequestDocs(docName, docAuth, docType, userID);

        if (awesomeValidation.validate()) {
                    myRef = FirebaseDatabase.getInstance().getReference();
                    myRef.child("RequestDocs").push().setValue(requestDocs);
                    validateStatus = true;
        }
        return validateStatus;
    }
}
