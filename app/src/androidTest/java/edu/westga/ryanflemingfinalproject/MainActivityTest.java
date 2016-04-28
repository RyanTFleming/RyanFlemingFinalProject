package edu.westga.ryanflemingfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.GradientDrawable;
import android.inputmethodservice.Keyboard;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.test.TouchUtils;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.View.AddExpenseActivity;
import edu.westga.ryanflemingfinalproject.View.MainActivity;

/**
 * Created by RyanT on 4/18/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private DBController controller;

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

    /**
     * Test to make sure button started disableds
     */
    public void testInitialLoadScreenSubmitButtonDisabled() {
        final MainActivity activity = getActivity();
        this.controller = new DBController(activity, DBController.DATABASE_NAME);
        String userName = this.controller.getUserName();
        if (userName != null) {
            this.controller.deleteUsers();
            this.getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    activity.recreate();
                }
            });
            Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
            assertFalse(button.isEnabled());
        } else {
            Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
            assertFalse(button.isEnabled());

        }

        if (userName != null) {
            this.controller.insertUserName(userName);
        }
    }

    /**
     * Test to make sure button enabled with text
     */
    public void testInitialLoadScreenSubmitButtonEnabledWithText() {
            final MainActivity activity = getActivity();
            this.controller = new DBController(activity, DBController.DATABASE_NAME);
            String userName = this.controller.getUserName();
            if (userName != null) {
                this.controller.deleteUsers();
                this.getInstrumentation().runOnMainSync(new Runnable() {
                    @Override
                    public void run() {
                        activity.recreate();
                    }
                });
            } else {
                Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
                EditText addUserName = (EditText) activity.findViewById(R.id.add_user_text_edit);
                this.getFocus(addUserName);
                this.getInstrumentation().sendStringSync("Test");
                assertTrue(button.isEnabled());
            }
            if (userName != null) {
                this.controller.insertUserName(userName);
            }
        }

    /**
     * Test to make sure the button is disabled when text added then deleted
     */
    public void testInitialLoadScreenSubmitButtonDisabledWhenTextDeleted() {
        final MainActivity activity = getActivity();
        this.controller = new DBController(activity, DBController.DATABASE_NAME);
        String userName = this.controller.getUserName();
        if (userName != null) {
            this.controller.deleteUsers();
            this.getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    activity.recreate();
                }
            });
        } else {
            Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
            EditText addUserName = (EditText) activity.findViewById(R.id.add_user_text_edit);
            this.getFocus(addUserName);
            this.getInstrumentation().sendStringSync("T");
            SystemClock.sleep(500);
            this.getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
            SystemClock.sleep(500);
            assertFalse(button.isEnabled());
        }
        if (userName != null) {
            this.controller.insertUserName(userName);
        }

    }



    public void testWelcomeMessage() {
        final MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.DATABASE_NAME);
        String userName = this.controller.getUserName();
        if (userName == null) {
            this.controller.insertUserName("Test");
            getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                   activity.recreate();
                }
            });
        } else {
            TextView txvWelcome = (TextView) activity.findViewById(R.id.welcome_text_view);
            assertEquals("Welcome\n\n" + this.controller.getUserName(), txvWelcome.getText().toString());
        }

        if (userName == null) {
            this.controller.deleteUsers();
        }

    }

    /* ******************************************* private methods ********************* */
    /**
     * Gets the focus of an edit text.
     * @param editText
     */
    private void getFocus(EditText editText) {
        final EditText focusedEditText = editText;
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                focusedEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        SystemClock.sleep(500);
    }
}
