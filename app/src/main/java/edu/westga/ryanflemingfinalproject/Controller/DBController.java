package edu.westga.ryanflemingfinalproject.Controller;

import android.content.Context;

import edu.westga.ryanflemingfinalproject.Model.DBHandler;

/**
 * Controller for accessing the DAL
 *
 * @author Ryan Fleming
 * @version 4/17/2016
 */
public class DBController {

    private DBHandler handler;

    public DBController(Context context) {
        this.handler = new DBHandler(context, null, null, 1);
    }


    public void insertUserName(String userName) {
        this.handler.insertUserName(userName);
    }

    public String getUserName() {
        return this.handler.getUserName();
    }

}
