package ua.org.klug.planerka.repositories;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import ua.org.klug.planerka.model.Meeting;

public class PlaceRepository {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";

    private GoogleApiClient mGoogleApiClient;
    private Observable<Meeting> mMeetingObservable;

    public PlaceRepository(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
        mMeetingObservable = Observable.just(fetchMeeting());
    }

    private Meeting fetchMeeting() {
        PlaceBuffer places = Places.GeoDataApi.getPlaceById(mGoogleApiClient, KOLOSS_ID).await();
        Meeting meeting = new Meeting(places.get(0));
        places.release();

        return meeting;
    }

    public void  getMeeting(Subscriber<Meeting> subscriber) {
        mMeetingObservable
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

}
