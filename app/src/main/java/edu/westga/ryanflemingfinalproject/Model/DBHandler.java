package edu.westga.ryanflemingfinalproject.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class for working with the SQLite database.
 *
 * @author Ryan T. Fleming
 * @version 4/17/2016
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KeepTheChangeDB.db";

    public static final String TABLE_USER = "users";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_USERNAME = "userName";

    public static final String TABLE_WAGE = "timelog";
    public static final String COLUMN_STARTTIME = "startTime";
    public static final String COLUMN_ENDTIME = "endTime";
    public static final String COLUMN_TIMEWORKED = "timeWorked";

    public static final String TABLE_TIMELOG = "wage";
    public static final String COLUMN_STARTDATE = "startTime";




    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " +
                TABLE_USER + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_USERNAME + " TEXT)";
        String createWageTable = "CREATE TABLE " +
                TABLE_WAGE + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_STARTDATE + " TEXT)";
        String createTimeLogTable = "CREATE TABLE " +
                TABLE_TIMELOG + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_STARTTIME + " TEXT, " +
                    COLUMN_ENDTIME + " TEXT, " +
                    COLUMN_TIMEWORKED + " TEXT";
        db.execSQL(createUserTable);
        db.execSQL(createWageTable);
        db.execSQL(createTimeLogTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMELOG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAGE);
        this.onCreate(db);
    }
}
