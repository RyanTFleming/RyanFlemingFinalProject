package edu.westga.ryanflemingfinalproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.R;

public class ResultsActivity extends AppCompatActivity {

    private DBController controller;
    private double wage;
    private double hours;
    private String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Help", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.controller = new DBController(this, DBController.DATABASE_NAME);

        Intent intent = this.getIntent();
        this.wage = intent.getDoubleExtra(CalculatorActivity.WAGE_SELECTION, 0.00);
        this.hours = intent.getDoubleExtra(CalculatorActivity.HOURS_SELECTION, 0.00);
        this.goal = intent.getStringExtra(CalculatorActivity.GOAL_SELECTION);
        double goalValue = this.controller.getGoalValue(this.goal);
        double expenseTotal = this.controller.getTotalExpenses();

        TextView wageView = (TextView) this.findViewById(R.id.textViewWage);
        TextView hoursView = (TextView) this.findViewById(R.id.textViewHours);
        TextView goalView = (TextView) this.findViewById(R.id.textViewGoal);
        TextView goalValueView = (TextView) this.findViewById(R.id.textViewGoalValue);
        TextView totalExpenseView = (TextView) this.findViewById(R.id.textViewExpenseTotal);


        wageView.setText(String.format("$%.2f", this.wage));
        hoursView.setText(String.format("%.2f", this.hours));
        goalView.setText(this.goal);
        goalValueView.setText(String.format("$%.2f", goalValue));
        totalExpenseView.setText(String.format("Monthly Expenses:$%.2f", expenseTotal));
    }
}
