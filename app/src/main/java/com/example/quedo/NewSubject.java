package com.example.quedo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.basgeekball.awesomevalidation.utility.RegexTemplate.NOT_EMPTY;

public class NewSubject extends Fragment {

    FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private AwesomeValidation awesomeValidation;

    EditText sub, hall, day, time;
    Button btnSubmit;
    Spinner classTypeSpin;
    String timetableID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_newsub, container, false);

        mAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        sub = rootView.findViewById(R.id.subName);
        hall = rootView.findViewById(R.id.hallName);
        day = rootView.findViewById(R.id.subDate);
        time = rootView.findViewById(R.id.subTime);
        btnSubmit = rootView.findViewById(R.id.subSubmit);
        classTypeSpin = (Spinner) rootView.findViewById(R.id.classType);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(NewSubject.this.getActivity(), R.array.classTypeSpin,
                        android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        classTypeSpin.setAdapter(staticAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validate = validateForm();
                if (validate) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.suc_toast, container, false);
                    Toast toast = new Toast(NewSubject.this.getActivity());
                    toast.setGravity(Gravity.FILL, 0, 0);
                    toast.setView(layout);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new Timetables()).addToBackStack(null).commit();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getParentFragmentManager().setFragmentResultListener("requestKey2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                timetableID = bundle.getString("timetableID");

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        awesomeValidation.addValidation(NewSubject.this.getActivity(), R.id.subName, NOT_EMPTY, R.string.thisFieldError);
        awesomeValidation.addValidation(NewSubject.this.getActivity(), R.id.hallName, NOT_EMPTY, R.string.thisFieldError);
        awesomeValidation.addValidation(NewSubject.this.getActivity(), R.id.subDate, NOT_EMPTY, R.string.thisFieldError);
        awesomeValidation.addValidation(NewSubject.this.getActivity(), R.id.subTime, NOT_EMPTY, R.string.thisFieldError);
    }

    private Boolean validateForm() {
        String subName = sub.getText().toString();
        String subHall = hall.getText().toString();
        String subDate = day.getText().toString();
        String subTime = time.getText().toString();
        String subType = classTypeSpin.getSelectedItem().toString();

        final String userID = mAuth.getCurrentUser().getUid();
        Boolean validateStatus = false;

        Subject subject = new Subject(subName, subHall, subDate, subTime, subType);

        if (awesomeValidation.validate()) {
            myRef = FirebaseDatabase.getInstance().getReference();
            myRef.child("Subjects").child(timetableID).push().setValue(subject);

            validateStatus = true;
        }
        return validateStatus;
    }
}