package edu.westga.ryanflemingfinalproject.Controller;

import android.content.Context;

import java.util.ArrayList;

import edu.westga.ryanflemingfinalproject.Model.DBHandler;
import edu.westga.ryanflemingfinalproject.Model.Expense;

/**
 * Controller for accessing the DAL
 *
 * @author Ryan Fleming
 * @version 4/17/2016
 */
public class DBController {

    public static final String DATABASE_NAME = "KeepTheChangeDB.db";
    public static final String TEST_DATABASE = "TestDB.db";

    private DBHandler handler;

    public DBController(Context context, String name) {
        this.handler = new DBHandler(context, name, null, 1);
    }


    public void insertUserName(String userName) {
        this.handler.insertUserName(userName);
    }

    public String getUserName() {
        return this.handler.getUserName();
    }

    public boolean insertExpense(String name, double user) {
        return this.handler.insertExpense(name, user);
    }

    public boolean insertGoal(String name, double user) {
        return this.handler.insertGoal(name, user);
    }

    public ArrayList<String> getGoals() {
        return this.handler.getGoals();
    }

    public double getGoalValue(String goalName) {
        return this.handler.getGoalValue(goalName);
    }

    public double getTotalExpenses() {
        return this.handler.getTotalExpenses();
    }

    public ArrayList<Expense> getAllExpenses() {
        return this.handler.getAllExpenses();
    }

    public boolean deleteAllExpenses() {
        return this.handler.deleteAllExpenses();
    }

    public boolean deleteExpense(String expenseName) {
        return this.handler.deleteExpense(expenseName);
    }

    public boolean deleteUsers() {
        return this.handler.deleteUser();
    }




}
