package edu.westga.ryanflemingfinalproject;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import edu.westga.ryanflemingfinalproject.View.AddGoalActivity;

/**
 * Activity test for the AddGoalActivity
 *
 * @author Ryan T Fleming
 */
public class AddGoalActivityTest extends ActivityInstrumentationTestCase2<AddGoalActivity> {

    /**
     * Required constructor
     */
    public AddGoalActivityTest() {
        super(AddGoalActivity.class);
    }


    /**
     * Test to ensure the activity exists
     */
    public void testShouldGetActivity() {
        AddGoalActivity activity = this.getActivity();
        assertNotNull(activity);
    }

    /**
     * Test to make sure button starts out disabled
     */
    public void testButtonShouldBeDisabledWhenTextEditsEmpty() {
        AddGoalActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnAddGoal);
        assertFalse(button.isEnabled());
    }

    /**
     * Test to make sure button is still disabled if only the name is filled in
     *
     */
    public void testButtonShouldBeDisabledIfOnlyNameEditTextPopulated() {
        final AddGoalActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnAddGoal);
        final EditText nameEditText = (EditText) activity.findViewById(R.id.editTextGoalName);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("Test");
        getInstrumentation().waitForIdleSync();
        assertFalse(button.isEnabled());
    }

    /**
     * Test to make sure the button is still disabled if only the value is
     * filled in
     */
    public void testButtonShouldBeDisabledIfOnlyValueEditTextPopulated() {
        final AddGoalActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnAddGoal);
        final EditText valueEditText = (EditText) activity.findViewById(R.id.editTextGoalValue);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                valueEditText.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("10");
        getInstrumentation().waitForIdleSync();
        assertFalse(button.isEnabled());
    }

    /**
     * Test to make sure the button is enabled if both fields are filled in
     */
    public void testButtonShouldBeEnabledWhenTextEditsArePopulated() {
        final AddGoalActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnAddGoal);
        final EditText nameEditText = (EditText) activity.findViewById(R.id.editTextGoalName);
        final EditText valueEditText = (EditText) activity.findViewById(R.id.editTextGoalValue);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("Test");
        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                valueEditText.requestFocus();
            }
        });
        getInstrumentation().sendStringSync("10");
        getInstrumentation().waitForIdleSync();

        assertTrue(button.isEnabled());
    }

}
