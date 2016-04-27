package edu.westga.ryanflemingfinalproject;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import edu.westga.ryanflemingfinalproject.View.AddExpenseActivity;

/**
 * Test for the AddExpenseActivity.
 *
 * @author  Ryan T. Fleming
 * @version 4/21/2016
 */
public class AddExpenseActivityTest extends ActivityInstrumentationTestCase2<AddExpenseActivity> {

    public AddExpenseActivityTest() {
        super(AddExpenseActivity.class);
    }

    /**
     * Test to ensure the activity exists.
     */
    public void testActivityExists() {
        AddExpenseActivity activity = this.getActivity();
        assertNotNull(activity);
    }

    /**
     * Test to make sure button starts out disabled
     */
    public void testButtonShouldBeDisabledWhenTextEditsEmpty() {
        AddExpenseActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnExpense);
        assertFalse(button.isEnabled());
    }

    /**
     * Test to make sure button is still disabled if only the name is filled in
     *
     */
    public void testButtonShouldBeDisabledIfOnlyNameEditTextPopulated() {
        final AddExpenseActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnExpense);
        final EditText nameEditText = (EditText) activity.findViewById(R.id.expenseNameTextEdit);

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
        final AddExpenseActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnExpense);
        final EditText valueEditText = (EditText) activity.findViewById(R.id.expenseValueTextEdit);

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
        final AddExpenseActivity activity = this.getActivity();
        Button button = (Button) activity.findViewById(R.id.btnExpense);
        final EditText nameEditText = (EditText) activity.findViewById(R.id.expenseNameTextEdit);
        final EditText valueEditText = (EditText) activity.findViewById(R.id.expenseValueTextEdit);

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
