package edu.westga.ryanflemingfinalproject.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.Model.Expense;
import edu.westga.ryanflemingfinalproject.Model.CustomArrayAdapter;
import edu.westga.ryanflemingfinalproject.R;

public class ViewExpensesActivity extends AppCompatActivity {

    private DBController controller;
    private ArrayList<Expense> expenseList;
    private ArrayList<String> expenseNames;
    private ArrayList<String> expenseValues;
    private CustomArrayAdapter expenseArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);
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

        this.expenseList = this.controller.getAllExpenses();
        this.expenseNames = new ArrayList<>();
        this.expenseValues = new ArrayList<>();
        this.createArrayLists();
        final ListView expenseListView = (ListView) this.findViewById(R.id.listViewExpenses);
        this.expenseArrayAdapter = new CustomArrayAdapter(this, expenseNames, this.expenseValues);
        expenseListView.setAdapter(expenseArrayAdapter);
        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(ViewExpensesActivity.this, "Hold to Delete " + expenseNames.get(position), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        expenseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deletedName = expenseNames.get(position);
                boolean isDeleted = ViewExpensesActivity.this.controller.deleteExpense(expenseNames.get(position));

                if (isDeleted) {
                    expenseList = controller.getAllExpenses();
                    expenseArrayAdapter.clear();
                    ViewExpensesActivity.this.createArrayLists();
                    expenseArrayAdapter = new CustomArrayAdapter(ViewExpensesActivity.this, expenseNames, expenseValues);
                    expenseListView.setAdapter(expenseArrayAdapter);
                    Toast toast = Toast.makeText(ViewExpensesActivity.this, "Successfully Deleted " + deletedName , Toast.LENGTH_LONG);
                    toast.show();
                }
                return isDeleted;
            }
        });


    }

    private void createArrayLists() {

        for (Expense expense : this.expenseList) {
            String name = expense.getName();
            String value = String.format("%.2f", expense.getValue());
            this.expenseNames.add(name);
            this.expenseValues.add(value);
        }
    }
}
