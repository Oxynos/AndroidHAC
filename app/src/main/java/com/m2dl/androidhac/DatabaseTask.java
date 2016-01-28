package com.m2dl.androidhac;

import android.os.AsyncTask;

import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by manantsoa on 24/01/16.
 */
public class DatabaseTask extends AsyncTask {
    String request;

    public DatabaseTask(String request) {
        this.request = request;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {
            DatabaseService.insertUpdateOrDeleteRequest(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
