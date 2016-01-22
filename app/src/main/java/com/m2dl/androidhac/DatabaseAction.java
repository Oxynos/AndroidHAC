package com.m2dl.androidhac;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by manantsoa on 21/01/16.
 */
public class DatabaseAction extends AsyncTask<Connection,Connection, Connection> {

    @Override
    protected Connection doInBackground(Connection... params) {
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Log.e("MESSAGE", e.getMessage());
        }

        try {
            c = DatabaseService.getConnection();
        } catch (URISyntaxException e) {
            Log.e("ERREUR", e.getInput());
        } catch (SQLException e) {
            Log.e("ERREUR 2", "erreur", e);
        }
        return c;
    }

    @Override
    protected void onPostExecute(Connection result) {

    }
}
