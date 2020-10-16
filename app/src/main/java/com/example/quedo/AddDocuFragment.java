package com.example.quedo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.basgeekball.awesomevalidation.utility.RegexTemplate.*;
import static com.example.quedo.SignUp.TAG;

public class AddDocuFragment extends Fragment {

    EditText mdocName, mdocAuth, mdocLink;
    Spinner mdocType;
    CheckBox mcheckBox;
    RadioButton mchild;
    RadioGroup mradioGroup;
    TextView mchildRestText;
    Button btnSubmit;
    String rest;
    int mchildID;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private DatabaseReference myRef;
    private AwesomeValidation awesomeValidation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_add_docu, container, false);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        mdocName = rootView.findViewById(R.id.docNameEdit);
        mdocAuth = rootView.findViewById(R.id.docAuthEdit);
        mdocLink = rootView.findViewById(R.id.docuLink);
        mcheckBox = rootView.findViewById(R.id.checkBox);
        mradioGroup = rootView.findViewById(R.id.radioChild);
        mchild = rootView.findViewById(mradioGroup.getCheckedRadioButtonId());
        mchildID = mradioGroup.getCheckedRadioButtonId();
        mdocType =  rootView.findViewById(R.id.docTypeEdit);
        mchildRestText = rootView.findViewById(R.id.childRestText);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(AddDocuFragment.this.getActivity(), R.array.docTypeSpin,
                        android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mdocType.setAdapter(staticAdapter);

        mradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.yesRadioBtn:
                        rest = "Yes";
                        break;
                    case R.id.noRadioBtn:
                        rest = "No";
                        break;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validate = validateForm();
                if (validate) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.upload_suc, container, false);
                    Toast toast = new Toast(AddDocuFragment.this.getActivity());
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

        awesomeValidation.addValidation(AddDocuFragment.this.getActivity(), R.id.docNameEdit, NOT_EMPTY, R.string.docNameError);
        awesomeValidation.addValidation(AddDocuFragment.this.getActivity(), R.id.docAuthEdit, NOT_EMPTY, R.string.docAuthError);
        awesomeValidation.addValidation(AddDocuFragment.this.getActivity(), R.id.docuLink, NOT_EMPTY, R.string.docLinkError);
        awesomeValidation.addValidation(AddDocuFragment.this.getActivity(), R.id.checkBox, NOT_EMPTY, R.string.docAuthError);
    }

    private boolean validateForm() {
        String docName = mdocName.getText().toString();
        String docAuth = mdocAuth.getText().toString();
        String docType = mdocType.getSelectedItem().toString();
        String childRes = rest;
        String docuLink = mdocLink.getText().toString();
        final String userID = mAuth.getCurrentUser().getUid();

        Random random = new Random();
        Boolean validateStatus = false;

        Document document = new Document(docName, docAuth, docType, childRes, userID, docuLink);

        if (awesomeValidation.validate()) {
            if(mcheckBox.isChecked()) {
                mcheckBox.setBackgroundColor(Color.TRANSPARENT);
                if ( childRes == "Yes" | childRes == "No") {
                    mchildRestText.setVisibility(View.GONE);
                    myRef = FirebaseDatabase.getInstance().getReference();
//                    myRef.child("Documents").child(Integer.toString(random.nextInt(100))).setValue(document);
                    myRef.child("Documents").push().setValue(document);

                    validateStatus = true;
                }
                else {
                   mchildRestText.setVisibility(View.VISIBLE);
                   validateStatus = false;
                }
            }
            else {
                mcheckBox.setBackgroundColor(Color.RED);
            }
        }
        return validateStatus;
    }

}
