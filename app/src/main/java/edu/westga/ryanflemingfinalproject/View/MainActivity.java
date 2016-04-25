package edu.westga.ryanflemingfinalproject.View;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.Model.DBHandler;
import edu.westga.ryanflemingfinalproject.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout mainActivityLayout;

    private DBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        this.mainActivityLayout = (LinearLayout) this.findViewById(R.id.mainLayout);
        this.controller = new DBController(this, this.controller.DATABASE_NAME);
        this.loadScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadScreen() {

        if (this.controller.getUserName() == null) {



            TextView addText = new TextView(this);
            addText.setGravity(Gravity.CENTER_HORIZONTAL);
            addText.setText("Enter Your Name: ");
            addText.setTextSize(30);
            addText.setPadding(0, 300, 0, 50);
            addText.setTextColor(Color.BLACK);
            addText.setId(R.id.add_text_view);
            final EditText etxUser = new EditText(this);
            etxUser.setId(R.id.add_user_text_edit);
            final Button btnSubmit = new Button(this);
            btnSubmit.setEnabled(false);
            btnSubmit.setText("Submit");
            btnSubmit.setId(R.id.button_submit_user_name);
            etxUser.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().equals("")) {
                        btnSubmit.setEnabled(false);
                    } else {
                        btnSubmit.setEnabled(true);
                    }
                }
            });



            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.controller.insertUserName(etxUser.getText().toString());
                    MainActivity.this.mainActivityLayout.removeAllViews();
                    TextView txvUserName = new TextView(MainActivity.this);
                    txvUserName.setText(MainActivity.this.controller.getUserName());
                    MainActivity.this.makeUI();
                }
            });

            this.mainActivityLayout.addView(addText);
            this.mainActivityLayout.addView(etxUser);
            this.mainActivityLayout.addView(btnSubmit);
        } else {
           this.makeUI();
        }
    }

    public void makeUI() {
        TextView txvWelcome = new TextView(this);
        txvWelcome.setText("Welcome, " + this.controller.getUserName());
        Button btnAddExpense = new Button(this);
        btnAddExpense.setText(R.string.button_expense);
        btnAddExpense.setId(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startExpenseActivity();
            }
        });

        Button btnAddGoal = new Button(this);
        btnAddGoal.setText(R.string.button_goal);
        btnAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startGoalActivity();
            }
        });

        Button btnCalculate = new Button(this);
        btnCalculate.setText(R.string.button_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startCalculatorActivity();
            }
        });

        txvWelcome.setGravity(Gravity.CENTER_HORIZONTAL);
        txvWelcome.setPadding(0, 300, 0, 15);
        txvWelcome.setTextSize(30);
        txvWelcome.setTextColor(Color.BLACK);
        this.mainActivityLayout.addView(txvWelcome);
        this.mainActivityLayout.addView(btnAddExpense);
        this.mainActivityLayout.addView(btnAddGoal);
        this.mainActivityLayout.addView(btnCalculate);
    }

    private void startGoalActivity() {
        Intent intent = new Intent(this, AddGoalActivity.class);
        this.startActivity(intent);
    }

    private void startExpenseActivity() {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        this.startActivity(intent);
    }

    private void startCalculatorActivity() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        this.startActivity(intent);
    }
}
