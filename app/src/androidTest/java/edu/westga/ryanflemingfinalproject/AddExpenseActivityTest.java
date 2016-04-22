package edu.westga.ryanflemingfinalproject;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import edu.westga.ryanflemingfinalproject.View.AddExpenseActivity;

/**
 * Created by RyanT on 4/21/2016.
 */
public class AddExpenseActivityTest extends ActivityInstrumentationTestCase2<AddExpenseActivity> {

    public AddExpenseActivityTest() {
        super(AddExpenseActivity.class);
    }

    public void testActivityExists() {
        AddExpenseActivity activity = new AddExpenseActivity();
        assertNotNull(activity);
    }
}
