package com.m2dl.androidhac;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by cedricrohaut on 1/15/16.
 */
public class Photo {
    private String nom;
    private String url;
    private LatLng coordonnees;
    private Tag tag;


    public Photo(String nom, String url, LatLng coordonnees, Tag tag) {
        this.nom = nom;
        this.url = url;
        this.coordonnees = coordonnees;
        this.tag = tag;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCoordonnees(LatLng coordonnees) {
        this.coordonnees = coordonnees;
    }

    public LatLng getCoordonnees() {
        return coordonnees;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
