package com.m2dl.androidhac;

/**
 * Created by cedricrohaut on 1/15/16.
 */
public class User {
    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public User(String pseudo) {

        this.pseudo = pseudo;
    }
}
