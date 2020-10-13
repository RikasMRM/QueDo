package com.example.quedo;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class HomeWTest {

    @Rule
    public ActivityTestRule<HomeW> homeWActivityTestRules = new ActivityTestRule<HomeW>(HomeW.class);

    private HomeW homeW = null;

    Instrumentation.ActivityMonitor monitorLogin = getInstrumentation().addMonitor(userLogin.class.getName(), null, false);
    Instrumentation.ActivityMonitor monitorReg = getInstrumentation().addMonitor(SignUp.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        homeW = homeWActivityTestRules.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = homeW.findViewById(R.id.layoutLanding);

        assertNotNull(view);
//       assertNull(view);

    }

    @Test
    public void launchingLogin() {
        assertNotNull(homeW.findViewById(R.id.btnhomelogin));

        onView(withId(R.id.btnhomelogin));
        Activity login = getInstrumentation().waitForMonitorWithTimeout(monitorLogin, 15000);
        assertNotNull(login);
    }

    @Test
    public void launchingReg() {
        assertNotNull(homeW.findViewById(R.id.btncreateacc));

        onView(withId(R.id.btncreateacc));
        Activity reg = getInstrumentation().waitForMonitorWithTimeout(monitorReg, 15000);
        assertNotNull(reg);
    }

    @After
    public void tearDown() throws Exception {
        homeW = null;
    }
}