package ua.org.klug.planerka.model;

import com.google.android.gms.location.places.Place;

public class Meeting {
    private String mWere;

    public Meeting(Place place) {
        mWere = String.valueOf(place.getName());
    }

    public String getWere() {
        return mWere;
    }
}
