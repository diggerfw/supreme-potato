package ua.org.klug.planerka.repositories;

import com.android.annotations.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import ua.org.klug.planerka.model.Meeting;

public class PlaceRepository {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";

    private GoogleApiClient mGoogleApiClient;

    public PlaceRepository(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    public void getPlaceInfo() {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, KOLOSS_ID).
                setResultCallback(new ResultCallback<PlaceBuffer>() {
                                      @Override
                                      public void onResult(@NonNull PlaceBuffer places) {
                                          Meeting meeting = new Meeting(places.get(0));
                                          //setMeeting(meeting);
                                          places.release();
                                      }
                                  }

                );
    }
}
