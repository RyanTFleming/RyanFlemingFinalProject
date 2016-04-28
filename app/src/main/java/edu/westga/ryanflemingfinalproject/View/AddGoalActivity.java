package edu.westga.ryanflemingfinalproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.R;

/**
 * Activity that allows the user to add a goal to the database.
 */
public class AddGoalActivity extends AppCompatActivity {

    private DBController controller;
    private EditText nameEditText;
    private EditText valueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.controller = new DBController(this, DBController.DATABASE_NAME);
        this.nameEditText = (EditText) this.findViewById(R.id.editTextGoalName);
        this.nameEditText.addTextChangedListener(new GoalTextWatcher());
        this.valueEditText = (EditText) this.findViewById(R.id.editTextGoalValue);
        this.valueEditText.addTextChangedListener(new GoalTextWatcher());
    }

    /**
     * Adds a goal to the database
     * @param v the add button.
     */
    public void onAddGoalClick(View v) {
        String goalName = this.nameEditText.getText().toString();

        try {
            double goalValue = Double.parseDouble(this.valueEditText.getText().toString());
            boolean success = this.controller.insertGoal(goalName, goalValue);
            if (success) {
                Toast toast = Toast.makeText(this, "Goal successfully added", Toast.LENGTH_LONG);
                toast.show();
                this.valueEditText.setText("");
                this.nameEditText.setText("");

            } else {
                Toast toast = Toast.makeText(this, "Failed to add goal", Toast.LENGTH_LONG);
                toast.show();
            }

        } catch (NumberFormatException nfe) {
            Toast toast = Toast.makeText(this, "Cost must be a valid number", Toast.LENGTH_LONG);
            toast.show();
        } catch (Exception ex) {
            //TODO
        }
    }

    /**
     * Private inner class that implements TextWatcher so the required fields must be filled out
     * or the button is disabled.
     */
    private class GoalTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // NOT USED
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText nameEditText = (EditText) AddGoalActivity.this.findViewById(R.id.editTextGoalName);
            EditText valueEditText = (EditText) AddGoalActivity.this.findViewById(R.id.editTextGoalValue);
            Button addButton = (Button) AddGoalActivity.this.findViewById(R.id.btnAddGoal);
            if (!nameEditText.getText().toString().equals("") && !valueEditText.getText().toString().equals("")) {
                addButton.setEnabled(true);
            } else {
                addButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //NOT USED
        }
    }
}
