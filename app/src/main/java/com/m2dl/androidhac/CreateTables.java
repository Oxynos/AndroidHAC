package com.m2dl.androidhac;

import android.os.AsyncTask;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by manantsoa on 21/01/16.
 */
public class CreateTables extends AsyncTask {
    Connection connection;
    Statement statement;
    String request;

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            connection = DatabaseService.getConnection();
            statement = connection.createStatement();
            request = "CREATE TABLE users " +
                    "(ID serial PRIMARY KEY     NOT NULL," +
                    " PSEUDO           TEXT    NOT NULL)";
            statement.executeUpdate(request);

            //Creation table PHOTO
            request = "CREATE TABLE PHOTO " +
                    "(ID serial PRIMARY KEY NOT NULL, " +
                    " URL TEXT NOT NULL, " +
                    "TAG TEXT NOT NULL" +
                    "NOM TEXT, " +
                    "LAT REAL, " +
                    "LON REAL)";
            statement.executeUpdate(request);

            //Creation table TAG
            request = "CREATE TABLE TAG " +
                    "(ID serial PRIMARY KEY NOT NULL," +
                    "ID_P INT REFERENCES PHOTO," +
                    " TAG TEXT NOT NULL)";
            statement.executeUpdate(request);
            statement.close();
            connection.close();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
