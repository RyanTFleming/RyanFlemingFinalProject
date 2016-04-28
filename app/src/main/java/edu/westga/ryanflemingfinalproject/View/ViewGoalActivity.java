package edu.westga.ryanflemingfinalproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.Model.CustomArrayAdapter;
import edu.westga.ryanflemingfinalproject.Model.Expense;
import edu.westga.ryanflemingfinalproject.Model.Goal;
import edu.westga.ryanflemingfinalproject.R;

public class ViewGoalActivity extends AppCompatActivity {
    private DBController controller;
    private ArrayList<Goal> goalList;
    private ArrayList<String> goalNames;
    private ArrayList<String> goalValues;
    private CustomArrayAdapter goalArrayAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "1. Press and hold to delete Item, or\n2. Hit toggle to activate 'Delete All' button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.controller = new DBController(this, DBController.DATABASE_NAME);

        this.goalList = this.controller.getAllGoals();
        this.goalNames = new ArrayList<>();
        this.goalValues = new ArrayList<>();
        this.createArrayLists();
        final ListView goalListView = (ListView) this.findViewById(R.id.listViewGoals);
        this.goalArrayAdapter = new CustomArrayAdapter(this, goalNames, this.goalValues);
        goalListView.setAdapter(goalArrayAdapter);
        goalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(ViewGoalActivity.this, "Hold to Delete " + goalNames.get(position), Toast.LENGTH_LONG);
                toast.show();
            }
        });

       goalListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deletedName = goalNames.get(position);
                boolean isDeleted = ViewGoalActivity.this.controller.deleteGoal(goalNames.get(position));

                if (isDeleted) {
                    goalList = controller.getAllGoals();
                    goalArrayAdapter.clear();
                    ViewGoalActivity.this.createArrayLists();
                    goalArrayAdapter = new CustomArrayAdapter(ViewGoalActivity.this, goalNames, goalValues);
                    goalListView.setAdapter(goalArrayAdapter);
                    Toast toast = Toast.makeText(ViewGoalActivity.this, "Successfully Deleted " + deletedName , Toast.LENGTH_LONG);
                    toast.show();
                }
                return isDeleted;
            }
        });
        final Button deleteButton = (Button) this.findViewById(R.id.buttonDeleteAllGoals);
        final Switch deleteToggle = (Switch) this.findViewById(R.id.deleteGoalToggle);
        deleteToggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!deleteToggle.isChecked()) {
                    deleteToggle.setChecked(true);
                    deleteButton.setEnabled(false);
                } else {
                    deleteToggle.setChecked(false);
                    deleteButton.setEnabled(true);
                }

                return false;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(ViewGoalActivity.this, "Hold to Delete All Goals", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean success = ViewGoalActivity.this.controller.deleteAllGoals();
                if (success) {
                    goalList = controller.getAllGoals();
                    goalArrayAdapter.clear();
                    ViewGoalActivity.this.createArrayLists();
                    goalArrayAdapter = new CustomArrayAdapter(ViewGoalActivity.this, goalNames, goalValues);
                    goalListView.setAdapter(goalArrayAdapter);
                    Toast toast = Toast.makeText(ViewGoalActivity.this, "Successfully Deleted All Goals" , Toast.LENGTH_LONG);
                    toast.show();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void createArrayLists() {

        for (Goal goal : this.goalList) {
            String name = goal.getName();
            String value = String.format("%.2f", goal.getCost());
            this.goalNames.add(name);
            this.goalValues.add(value);
        }
    }
}
