package com.m2dl.androidhac;

import java.util.List;

/**
 * Created by cedricrohaut on 1/15/16.
 */
public class Photo {
    private List<Tag> tags;
    private String nom;
    private String url;
    private LatLng coordonnees;


    public Photo(List<Tag> tags, String nom, String url, LatLng coordonnees) {
        this.tags = tags;
        this.nom = nom;
        this.url = url;
        this.coordonnees = coordonnees;
    }

    public Photo(String nom) {
        this.tags = new ArrayList<>();
        this.nom = nom;
        this.url = "";
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
}
