package com.m2dl.androidhac;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.text.BreakIterator;
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

    public float getColor() {
        switch (this) {
            case RECYCLAGE:
                return BitmapDescriptorFactory.HUE_GREEN;
            case DEGRADATION:
                return BitmapDescriptorFactory.HUE_YELLOW;
            case FUITEDEAU:
                return BitmapDescriptorFactory.HUE_BLUE;
        }

        return BitmapDescriptorFactory.HUE_BLUE;
    }
}
