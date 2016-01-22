package com.m2dl.androidhac;

import android.os.AsyncTask;

import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by manantsoa on 22/01/16.
 */
public class InsertOrUpdateRequestDBService extends AsyncTask {
    String request;


    InsertOrUpdateRequestDBService(String request) {
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
