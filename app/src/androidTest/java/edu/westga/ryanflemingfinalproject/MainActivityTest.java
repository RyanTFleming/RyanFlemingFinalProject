package edu.westga.ryanflemingfinalproject;

import android.inputmethodservice.Keyboard;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.test.TouchUtils;
import android.widget.EditText;

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

    /**
     * Test to make sure button started disableds
     */
    public void testInitialLoadScreenSubmitButtonDisabled() {
        MainActivity activity = getActivity();
        Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
        assertFalse(button.isEnabled());
    }

    /**
     * Test to make sure button enabled with text
     */
    public void testInitialLoadScreenSubmitButtonEnabledWithText() {
        MainActivity activity = getActivity();
        Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
        EditText addUserName = (EditText) activity.findViewById(R.id.add_user_text_edit);
        this.getFocus(addUserName);
        this.getInstrumentation().sendStringSync("Test");
        assertTrue(button.isEnabled());
        }

    /**
     * Test to make sure the button is disabled when text added then deleted
     */
    public void testInitialLoadScreenSubmitButtonDisabledWhenTextDeleted() {
        MainActivity activity = getActivity();
        Button button = (Button) activity.findViewById(R.id.button_submit_user_name);
        EditText addUserName = (EditText) activity.findViewById(R.id.add_user_text_edit);
        this.getFocus(addUserName);
        this.getInstrumentation().sendStringSync("T");
        this.getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);

        assertFalse(button.isEnabled());
    }

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
