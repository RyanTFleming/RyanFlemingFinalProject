package edu.westga.ryanflemingfinalproject.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.Model.GoalCalculator;
import edu.westga.ryanflemingfinalproject.R;

public class ResultsActivity extends AppCompatActivity {

    private DBController controller;
    private GoalCalculator calc;
    private double totalExpenses;
    private double wage;
    private double hours;
    private double goalValue;
    private String goal;

    private double totalEarnings;
    private double netEarnings;
    private double savedPerHour;
    private double hoursToGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.controller = new DBController(this, DBController.DATABASE_NAME);


        Intent intent = this.getIntent();
        this.wage = intent.getDoubleExtra(CalculatorActivity.WAGE_SELECTION, 0.00);
        this.hours = intent.getDoubleExtra(CalculatorActivity.HOURS_SELECTION, 0.00);
        this.goal = intent.getStringExtra(CalculatorActivity.GOAL_SELECTION);
        this.goalValue = this.controller.getGoalValue(this.goal);
        this.totalExpenses = this.controller.getTotalExpenses();
        this.calc = new GoalCalculator(this.wage, this.hours);
        this.populateResultsFrame();

        TextView wageView = (TextView) this.findViewById(R.id.textViewWage);
        TextView hoursView = (TextView) this.findViewById(R.id.textViewHours);

        TextView goalValueView = (TextView) this.findViewById(R.id.textViewGoalValue);
        TextView totalExpenseView = (TextView) this.findViewById(R.id.textViewExpenseTotal);


        wageView.setText(String.format("$%.2f", this.wage));
        hoursView.setText(String.format("%.2f", this.hours));
        goalValueView.setText(String.format("%s:    $%.2f", this.goal, this.goalValue));
        totalExpenseView.setText(String.format("$%.2f", this.totalExpenses));

    }

    private void populateResultsFrame() {
        TextView totalIncomeView = (TextView) this.findViewById(R.id.totalIncomeTextView);
        TextView netIncomeView = (TextView) this.findViewById(R.id.netTextView);
        TextView dollarsPerHourSavedView = (TextView) this.findViewById(R.id.netPerHourTextView);
        TextView hoursToGoalView = (TextView) this.findViewById(R.id.hrsToGoalTextView);
        this.totalEarnings = this.calc.monthlyGrossEarning();
        this.netEarnings = this.calc.monthlyNetEarnings(this.totalExpenses);
        this.savedPerHour = this.calc.amountSavedPerHour(this.totalExpenses);
        this.hoursToGoal = this.calc.hoursNeededToGoal(this.totalExpenses, this.goalValue);



        totalIncomeView.setText(String.format("$%.2f", this.totalEarnings));


        netIncomeView.setText(String.format("$%.2f", this.netEarnings));
        if (this.netEarnings < 0) {
            netIncomeView.setTextColor(Color.RED);
        } else {
            netIncomeView.setTextColor(Color.BLACK);
        }

        dollarsPerHourSavedView.setText(String.format("$%.2f", this.savedPerHour));
        if (this.savedPerHour < 0) {
            dollarsPerHourSavedView.setTextColor(Color.RED);
        } else {
            dollarsPerHourSavedView.setTextColor(Color.BLACK);
        }

        if (this.hoursToGoal < 0) {
            hoursToGoalView.setText("Unachievable");
            hoursToGoalView.setTextColor(Color.RED);
        } else {
            hoursToGoalView.setTextColor(Color.BLACK);
            hoursToGoalView.setText(String.format("%.2f", this.hoursToGoal));
        }
    }
}
