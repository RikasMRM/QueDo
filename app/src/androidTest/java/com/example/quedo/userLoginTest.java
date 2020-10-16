package com.example.quedo;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.test.rule.ActivityTestRule;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class userLoginTest {

    FirebaseAuth mAuth;
    AuthResult task;
    boolean response;

    @Rule
    public ActivityTestRule<userLogin> userLoginRules = new ActivityTestRule<userLogin>(userLogin.class);

    private userLogin userLogin = null;

    @Before
    public void setUp() throws Exception {
        userLogin = userLoginRules.getActivity();
        mAuth = FirebaseAuth.getInstance();
    }

    @Test
    public void testCorrectLogins() throws Exception {
        String email = "h.g.d.sandakalum@gmail.com";
        String password = "12345678";
//        if(mAuth.getCurrentUser() != null) {
//            response = true;
//        }

       response = mAuth.signInWithEmailAndPassword(email, password).isSuccessful();
//        boolean response = mAuth.signInWithEmailAndPassword(email, password).isSuccessful();
        Assert.assertEquals(true, response);
    }

//    @Test
//    public void testCorrectLogins() throws Exception {
//        String email = "dananjayasadakalum@ymail.com";
//        String password = "12345678";
//        boolean response = true;
//
//        mAuth = FirebaseAuth.getInstance();
////        View view = userLogin.findViewById(R.id.btnLogin);
//
////        Assert.assertEquals(true, false);
//
////        view.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//                response = mAuth.signInWithEmailAndPassword(email, password).isSuccessful();
//                Assert.assertEquals(true, response);
////            }
////        });
//    }
}

