package com.m2dl.androidhac;

/**
 * Created by manantsoa on 22/01/16.
 */
public enum Tag {
    RECYCLAGE("Recyclage"),
    DEGRADATION("Dégradation"),
    FUITEDEAU("Fuite d'eau");

    private String name = "";

    Tag(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
