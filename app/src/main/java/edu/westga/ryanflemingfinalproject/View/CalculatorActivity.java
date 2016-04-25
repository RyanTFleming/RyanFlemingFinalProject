package edu.westga.ryanflemingfinalproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.R;

public class CalculatorActivity extends AppCompatActivity {

    private DBController controller;
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

    private void populateSpinners() {
        List<String> wages = new ArrayList<String>();
        double value = 5.0;
        for(int count = 0; count <= 100; count++) {
            wages.add(String.format("%.2f", value));
            value += .25;
        }
        ArrayAdapter<String> wageAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, wages);
        Spinner wageSpinner = (Spinner) this.findViewById(R.id.wageSpinner);
        wageSpinner.setAdapter(wageAdapter);

        List<String> hours = new ArrayList<String>();
        double hoursValue = 1.0;
        for(int count = 0; count <= 59; count++) {
            hours.add(String.format("%.2f", hoursValue));
            hoursValue += 1;
        }
        ArrayAdapter<String> hoursAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, hours);
        Spinner hoursSpinner = (Spinner) this.findViewById(R.id.hoursSpinner);
        hoursSpinner.setAdapter(hoursAdapter);

        List<String> goals = this.controller.getGoals();
        ArrayAdapter<String> goalsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, goals);
        Spinner goalsSpinner = (Spinner) this.findViewById(R.id.goalsSpinner);
        goalsSpinner.setAdapter(goalsAdapter);
    }
}
