package edu.westga.ryanflemingfinalproject;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

import edu.westga.ryanflemingfinalproject.Controller.DBController;
import edu.westga.ryanflemingfinalproject.Model.Expense;
import edu.westga.ryanflemingfinalproject.Model.Goal;
import edu.westga.ryanflemingfinalproject.View.MainActivity;

/**
 * Test case for the Database classes. DBController calls the DBHandler methods, so
 * this method should test both classes.
 *
 * @author Ryan T Fleming
 */
public class DBControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private DBController controller;

    /**
     * Required constructor
     */
    public DBControllerTest() {
        super(MainActivity.class);
    }

    /**
     * Test to make sure a user can be added and retrieved from the DB
     */
    public void testShouldAddAndRetrieveUserNameToDB() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.deleteUsers();
        this.controller.insertUserName("TestUser");
        assertEquals("TestUser", this.controller.getUserName());
    }

    /**
     * Test to make sure the db deletes the user.
     */
    public void testShouldReturnNullUserAfterDeleted() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.insertUserName("TestUser");
        this.controller.deleteUsers();
        assertNull(this.controller.getUserName());
    }

    /**
     * Test to make sure the db adds expenses
     */
    public void testShouldAddExpensesToDB() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.deleteAllExpenses();
        this.controller.insertExpense("Ex1", 10.0);
        this.controller.insertExpense("Ex2", 10.0);
        this.controller.insertExpense("Ex3", 10.0);
        ArrayList<Expense> testList = this.controller.getAllExpenses();

        assertEquals("Ex1", testList.get(0).getName());
        assertEquals("Ex2", testList.get(1).getName());
        assertEquals("Ex3", testList.get(2).getName());
    }

    /**
     * Test to make sure the db deletes all expenses
     */
    public void testShouldDeleteAllExpensesFromDB() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.insertExpense("Ex1", 10.0);
        this.controller.insertExpense("Ex2", 10.0);
        this.controller.insertExpense("Ex3", 10.0);
        this.controller.deleteAllExpenses();
        ArrayList<Expense> testList = this.controller.getAllExpenses();
        assertEquals(0, testList.size());
    }

    /**
     * Test to make sure the activity deletes the named expense.
     */
    public void testShouldDeleteOnlyExpenseNamed() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.insertExpense("Ex1", 10.0);
        this.controller.insertExpense("Ex2", 10.0);
        this.controller.insertExpense("Ex3", 10.0);
        this.controller.deleteExpense("Ex2");
        ArrayList<Expense> testList = this.controller.getAllExpenses();
        assertEquals(2, testList.size());
        assertEquals("Ex1", testList.get(0).getName());
        assertEquals("Ex3", testList.get(1).getName());
    }

    /**
     * Test to make sure the DB returns the correct sum of expenses
     */
    public void testShouldReturnExpenseTotal() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.deleteAllExpenses();
        this.controller.insertExpense("Ex1", 10.0);
        this.controller.insertExpense("Ex2", 15.0);
        this.controller.insertExpense("Ex3", 5.0);
        assertEquals(30.00, this.controller.getTotalExpenses(), .00);
    }

    /**
     * Test to make sure the activity deletes the named goal.
     */
    public void testShouldDeleteAllGoals() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.insertGoal("goal1", 10.0);
        this.controller.insertGoal("goal2", 10.0);
        this.controller.insertGoal("Goal3", 10.0);
        this.controller.deleteAllGoals();
        ArrayList<Goal> testList = this.controller.getAllGoals();
        assertEquals(0, testList.size());
    }

    /**
     * Test to make sure the activity deletes the named goal.
     */
    public void testShouldDeleteOnlyGoalNamed() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.deleteAllGoals();
        this.controller.insertGoal("goal1", 10.0);
        this.controller.insertGoal("goal2", 10.0);
        this.controller.insertGoal("Goal3", 10.0);
        this.controller.deleteGoal("goal2");
        ArrayList<Goal> testList = this.controller.getAllGoals();
        assertEquals(2, testList.size());
        assertEquals("goal1", testList.get(0).getName());
        assertEquals("Goal3", testList.get(1).getName());
    }

    /**
     * Test to make sure the activity adds goals to DB.
     */
    public void testShouldAddGoalsToDB() {
        MainActivity activity = this.getActivity();
        this.controller = new DBController(activity, DBController.TEST_DATABASE);
        this.controller.deleteAllGoals();
        this.controller.insertGoal("goal1", 10.0);
        this.controller.insertGoal("goal2", 10.0);
        this.controller.insertGoal("Goal3", 10.0);
        ArrayList<Goal> testList = this.controller.getAllGoals();
        assertEquals("goal1", testList.get(0).getName());
        assertEquals("goal2", testList.get(1).getName());
        assertEquals("Goal3", testList.get(2).getName());
    }
}
