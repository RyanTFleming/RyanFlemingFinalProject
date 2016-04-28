package edu.westga.ryanflemingfinalproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.R;

public class CalculatorActivity extends AppCompatActivity {

    public static String WAGE_SELECTION = "edu.westga.ryanflemingfinalactivity.view.calculatoractivity.WAGE";
    public static String HOURS_SELECTION = "edu.westga.ryanflemingfinalactivity.view.calculatoractivity.HOURS";
    public static String GOAL_SELECTION = "edu.westga.ryanflemingfinalactivity.view.calculatoractivity.GOAL";

    private DBController controller;
    private Spinner wageSpinner;
    private Spinner goalSpinner;
    private Spinner hoursSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
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
        this.populateSpinners();
    }

    public void onCalculateButtonClick(View v) {
        if (this.goalSpinner.getChildCount() <= 0) {
            Toast toast = Toast.makeText(this, "You must add at least one Goal", Toast.LENGTH_LONG);
            toast.show();
        } else {
            this.startResultsActivity();
        }


    }


    private void populateSpinners() {
        List<String> wages = new ArrayList<String>();
        double value = 5.0;
        for(int count = 0; count <= 100; count++) {
            wages.add(String.format("%.2f", value));
            value += .25;
        }
        ArrayAdapter<String> wageAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, wages);
        this.wageSpinner = (Spinner) this.findViewById(R.id.wageSpinner);
        this.wageSpinner.setAdapter(wageAdapter);

        List<String> hours = new ArrayList<String>();
        double hoursValue = 1.0;
        for(int count = 0; count <= 59; count++) {
            hours.add(String.format("%.2f", hoursValue));
            hoursValue += 1;
        }
        ArrayAdapter<String> hoursAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, hours);
        this.hoursSpinner = (Spinner) this.findViewById(R.id.hoursSpinner);
        this.hoursSpinner.setAdapter(hoursAdapter);

        List<String> goals = this.controller.getGoals();
        ArrayAdapter<String> goalsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, goals);
        this.goalSpinner = (Spinner) this.findViewById(R.id.goalsSpinner);
        this.goalSpinner.setAdapter(goalsAdapter);
    }

    private void startResultsActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        double wage = 0.0;
        double hours = 0.0;

        try {
            wage = Double.parseDouble(this.wageSpinner.getSelectedItem().toString());
            hours = Double.parseDouble(this.hoursSpinner.getSelectedItem().toString());
        } catch (NumberFormatException nfe) {
            Toast toast = Toast.makeText(this, "An error has occurred", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        intent.putExtra(this.GOAL_SELECTION, this.goalSpinner.getSelectedItem().toString());
        intent.putExtra(this.WAGE_SELECTION, wage);
        intent.putExtra(this.HOURS_SELECTION, hours);
        this.startActivity(intent);


    }
}
