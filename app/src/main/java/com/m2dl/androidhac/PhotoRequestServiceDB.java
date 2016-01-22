package com.m2dl.androidhac;

/**
 * Created by manantsoa on 22/01/16.
 */
public class PhotoRequestServiceDB {

    public static String createPhoto(Photo photo) {
        return "INSERT INTO USERS (URL, NOM, LAT, LON) "
                + "VALUES ('" + photo.getUrl() + "', " +
                "'" + photo.getNom() + "','" + photo.getCoordonnees().latitude + "'," +
                "'" + photo.getCoordonnees().longitude + "');";
    }

    public static String deletePhoto(Photo photo) {
        return "DELETE from PHOTO where URL=" + photo.getUrl() +";";
    }
}
