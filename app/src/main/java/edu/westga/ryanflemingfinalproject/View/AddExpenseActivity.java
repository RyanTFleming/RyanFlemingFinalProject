package edu.westga.ryanflemingfinalproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.R;

public class AddExpenseActivity extends AppCompatActivity {

    DBController controller;
    private EditText nameEditText;
    private EditText valueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.controller = new DBController(this, DBController.DATABASE_NAME);
        this.nameEditText = (EditText) findViewById(R.id.expenseNameTextEdit);
        this.valueEditText = (EditText) findViewById(R.id.expenseValueTextEdit);


        this.nameEditText.addTextChangedListener(new ExpenseTextWatch());
        this.valueEditText.addTextChangedListener(new ExpenseTextWatch());
    }

    /**
     * Adds an expense to the database when the button is clicked
     * @param v the buttons
     */
    public void onAddExpenseButtonClick(View v) {


        String name = this.nameEditText.getText().toString();
        String valueString = this.valueEditText.getText().toString();
        try {
            double value = Double.parseDouble(valueString);
            boolean isSuccess = this.controller.insertExpense(name, value);
            if (isSuccess) {
                Toast toast = Toast.makeText(this, "Expense Successfully Added", Toast.LENGTH_LONG);
                toast.show();
                this.nameEditText.setText("");
                this.valueEditText.setText("");
            } else {
                Toast toast = Toast.makeText(this, "Insert Failed", Toast.LENGTH_LONG);
                toast.show();
            }

        } catch (NumberFormatException nfe) {
            Toast toast = Toast.makeText(this, "Not a valid number", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    /**
     * Private inner class that implements TextWatcher so the required fields must be filled out
     * or the button is disabled.
     */
    private class ExpenseTextWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText nameEditText = (EditText) AddExpenseActivity.this.findViewById(R.id.expenseNameTextEdit);
            EditText valueEditText = (EditText) AddExpenseActivity.this.findViewById(R.id.expenseValueTextEdit);
            Button addButton = (Button) AddExpenseActivity.this.findViewById(R.id.btnExpense);
            if (!nameEditText.getText().toString().equals("") && !valueEditText.getText().toString().equals("")) {
                addButton.setEnabled(true);
            } else {
                addButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
