package edu.westga.ryanflemingfinalproject;

import android.test.ActivityInstrumentationTestCase2;

import edu.westga.ryanflemingfinalproject.View.MainActivity;

/**
 * Created by RyanT on 4/18/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * Test to make sure the activity exists
     */
    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testInitialLoadScreen() {

    }
}
