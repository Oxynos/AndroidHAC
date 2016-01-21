package com.m2dl.androidhac;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by manantsoa on 21/01/16.
 */
public class DatabaseService {

    public static Connection getConnection() throws URISyntaxException, SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Log.e("MESSAGE", e.getMessage());
        }

        String username = "zlmbfwhvoqeopc";
        String password = "vJDfm6heo2VA2FPlY5rBFf1rnV";
        Properties props = new Properties();
        props.setProperty("user",username);
        props.setProperty("password",password);
        props.setProperty("ssl","true");
        String dbUrl = "jdbc:postgresql://ec2-54-83-61-45.compute-1.amazonaws.com:5432/d7733f6ufgem1?sslmode=require";

        return DriverManager.getConnection(dbUrl, props);
    }
}
