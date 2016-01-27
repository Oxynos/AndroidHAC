package com.m2dl.androidhac;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manantsoa on 22/01/16.
 */
public enum Tag {
    RECYCLAGE("Recyclage"),
    DEGRADATION("Dégradation"),
    FUITEDEAU("Fuite d'eau");

    private String name = "";
    public static Map<String, Tag> stringTagMap = new HashMap<>();
    static {
        for(Tag t : Tag.values()) {
            stringTagMap.put(t.toString(),t);
        }
    }
    Tag(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
