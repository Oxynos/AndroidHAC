package com.m2dl.androidhac;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
        String dbUrl = "jdbc:postgresql://ec2-54-83-61-45.compute-1.amazonaws.com:5432/d7733f6ufgem1?" + "ssl=true&"+ "sslfactory=org.postgresql.ssl.NonValidatingFactory";

        return DriverManager.getConnection(dbUrl, props);
    }

    public static Boolean insertUpdateOrDeleteRequest(String sqlRequest) throws URISyntaxException, SQLException {
        Connection c;
        Statement s;

        c = getConnection();
        s = c.createStatement();
        s.executeUpdate(sqlRequest);
        s.close();
        c.close();

        return true;
    }


    public static List<Photo> getAllPhoto(String sqlRequest) throws URISyntaxException, SQLException {
        Connection c;
        Statement s;
        ResultSet resultSet;

        c = getConnection();
        s = c.createStatement();
        resultSet = s.executeQuery(sqlRequest);

        List<Photo> photos = new ArrayList<Photo>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String url = resultSet.getString("URL");
                    String nom = resultSet.getString("NOM");
                    double latitude = resultSet.getDouble("LAT");
                    double longitude = resultSet.getDouble("LON");
                    String tag = resultSet.getString("TAG");
                    Tag t = Tag.stringTagMap.get(tag);

                    Photo photo =  new Photo(nom, url, new LatLng(latitude, longitude), t);
                    photos.add(photo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        s.close();
        c.close();

        return photos;
    }
}
