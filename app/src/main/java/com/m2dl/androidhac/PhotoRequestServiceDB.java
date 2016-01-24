package com.m2dl.androidhac;

/**
 * Created by manantsoa on 22/01/16.
 */
public class PhotoRequestServiceDB {

    public static String createPhoto(Photo photo) {
        return "INSERT INTO PHOTO (URL, NOM, LAT, LON, TAG) "
                + "VALUES ('" + photo.getUrl() + "', " +
                "'" + photo.getNom() + "','" + photo.getCoordonnees().latitude + "'," +
                "'" + photo.getCoordonnees().longitude + "'," +
                "'" + photo.getTag() +"');";
    }

    public static String deletePhoto(Photo photo) {
        return "DELETE from PHOTO where URL=" + photo.getUrl() +";";
    }

    public static String deleteAllPhoto(Photo photo) {
        return "DELETE from PHOTO;";
    }
}
