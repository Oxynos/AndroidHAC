package com.m2dl.androidhac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedricrohaut on 1/15/16.
 */
public class Photo {
    private List<Tag> tags;
    private String nom;

    public Photo(List<Tag> tags, String nom) {
        this.tags = tags;
        this.nom = nom;
    }

    public Photo(String nom) {
        this.tags = new ArrayList<>();
        this.nom = nom;
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
}
