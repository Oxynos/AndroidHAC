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
public class GetAllTagsAsync  extends AsyncTask<Object, Object, List<Photo>> {
    String request;
    public AsyncResponcePhotos delegate = null;

    public GetAllTagsAsync(String request) {
        this.request = request;
    }

    @Override
    protected List<Photo> doInBackground(Object[] params) {

        List<Photo> photos = null;

        try {
            photos = DatabaseService.getAllPhoto(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return photos;
    }

    @Override
    protected void onPostExecute(List<Photo> result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }
}
