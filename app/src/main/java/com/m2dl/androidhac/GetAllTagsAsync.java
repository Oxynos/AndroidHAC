package com.m2dl.androidhac;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugues on 27/01/2016.
 */
public class GetAllTagsAsync  extends AsyncTask {
    String request;

    public GetAllTagsAsync(String request) {
        this.request = request;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        ResultSet rs = null;

        try {
            rs = DatabaseService.getAllPhoto(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Photo> photos = new ArrayList<Photo>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String url = rs.getString("URL");
                    String nom = rs.getString("NOM");
                    double latitude = rs.getDouble("LAT");
                    double longitude = rs.getDouble("LON");
                    String tag = rs.getString("TAG");
                    Tag t = Tag.stringTagMap.get(tag);

                    Photo photo =  new Photo(nom, url, new LatLng(latitude, longitude), t);
                    photos.add(photo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
