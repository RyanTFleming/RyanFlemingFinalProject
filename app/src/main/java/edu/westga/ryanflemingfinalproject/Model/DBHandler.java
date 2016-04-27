package edu.westga.ryanflemingfinalproject.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * Class for working with the SQLite database.
 *
 * @author Ryan T. Fleming
 * @version 4/17/2016
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_USERNAME = "userName";

    public static final String TABLE_EXPENSE = "expenses";
    public static final String COLUMN_NAME = "expenseName";
    public static final String COLUMN_VALUE = "expenseValue";

    public static final String TABLE_GOAL = "goals";
    public static final String COLUMN_GOAL_NAME = "goalName";
    public static final String COLUMN_GOAL_VALUE = "goalValue";




    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " +
                TABLE_USER + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USERNAME + " TEXT)";
        String createGoalsTable = "CREATE TABLE " +
                TABLE_GOAL + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_GOAL_NAME + " TEXT, " +
                    COLUMN_GOAL_VALUE + " REAL)";
        String createExpenseTable = "CREATE TABLE " +
                TABLE_EXPENSE + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_VALUE + " REAL)";
        db.execSQL(createUserTable);
        db.execSQL(createGoalsTable);
        db.execSQL(createExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL);
        this.onCreate(db);
    }

    public void insertUserName(String userName) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userName);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER, null, values);
    }

    public String getUserName() {
        String query = "SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        String userName = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            userName = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return userName;
    }

    public boolean deleteUser() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_USER, "1", null) > 0;
    }

    /**
     * Gets the value of the goal that was passed.
     * @param goalName - the goal to get the value of
     * @return goalValue - the value of the goal
     */
    public double getGoalValue(String goalName) {
        String query = "SELECT " + COLUMN_GOAL_VALUE + " FROM " + TABLE_GOAL +
                " WHERE " + COLUMN_GOAL_NAME + "=" + "\"" + goalName + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        double goalValue = 0.0;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            goalValue = cursor.getDouble(0);
            cursor.close();
        }
        return goalValue;
    }

    /**
     * Gets the list off all the goal names from the database and returns
     * them as a string array
     * @return goals - the arraylist of goals
     */
    public ArrayList<String> getGoals() {
        String query = "SELECT " + COLUMN_GOAL_NAME + " FROM " + TABLE_GOAL;
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> goals = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        int index;
        if (cursor != null) {
            cursor.moveToFirst();
            for  (index = 0; index < cursor.getCount(); index++ ) {
                goals.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return goals;
    }

    public ArrayList<Expense> getAllExpenses() {
        String query = "SELECT * FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int index;
        ArrayList<Expense> expenseList = new ArrayList<>();
        Expense expense;
        if (cursor!= null) {
            cursor.moveToFirst();
            for (index = 0; index < cursor.getCount(); index++) {
                String expenseName = cursor.getString(1);
                double expenseValue = cursor.getDouble(2);
                expense = new Expense(expenseName, expenseValue);
                expenseList.add(expense);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return expenseList;
    }

    public boolean deleteExpense(String expenseName) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_EXPENSE, COLUMN_NAME + "=" + "\"" + expenseName + "\"", null) > 0;
    }

    public boolean deleteAllExpenses() {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_EXPENSE, "1", null) > 0;
    }

    public double getTotalExpenses() {
        String query = "SELECT SUM(" + COLUMN_VALUE + ") FROM " + TABLE_EXPENSE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        double total = 0.0;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            total = cursor.getDouble(0);
            cursor.close();
        }
        return total;
    }
    public boolean insertExpense(String name, double value) {
        if (name == null) {
            throw new IllegalArgumentException("You must have a way to identify the expense");
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_VALUE, value);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowID = db.insert(TABLE_EXPENSE, null, values);

        if (rowID < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertGoal(String name, double value) {
        if (name == null) {
            throw new IllegalArgumentException("You must have a way to identify the Goal");
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_GOAL_NAME, name);
        values.put(COLUMN_GOAL_VALUE, value);
        SQLiteDatabase db = this.getWritableDatabase();
        long rowID = db.insert(TABLE_GOAL, null, values);

        if (rowID < 0) {
            return false;
        } else {
            return true;
        }
    }
}
