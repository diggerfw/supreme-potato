package ua.org.klug.planerka.repositories;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.Places;

import rx.Observable;
import rx.Subscriber;
import ua.org.klug.planerka.model.Meeting;

public class PlaceRepository {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";

    private GoogleApiClient mGoogleApiClient;

    public PlaceRepository(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    private Meeting fetchMeeting() {
        PlaceBuffer places = Places.GeoDataApi.getPlaceById(mGoogleApiClient, KOLOSS_ID).await();
        Meeting meeting = new Meeting(places.get(0));
        places.release();
        return meeting;
    }

    public Observable<Meeting> getMeeting() {
        return Observable.create(new Observable.OnSubscribe<Meeting>() {
            @Override
            public void call(Subscriber<? super Meeting> subscriber) {
                subscriber.onNext(fetchMeeting());
            }
        });
    }

    @Nullable
    private Bitmap receivePlacePhoto() {
        PlacePhotoMetadataResult photoResult = Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, KOLOSS_ID).await();
        PlacePhotoMetadataBuffer metadataBuffer = photoResult.getPhotoMetadata();
        if (metadataBuffer == null) {
            return null;
        }

        Bitmap result = metadataBuffer.get(0).getPhoto(mGoogleApiClient).await().getBitmap();
        photoResult.getPhotoMetadata().release();
        return result;
    }

    public Observable<Bitmap> getPlaceBitmap() {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                subscriber.onNext(receivePlacePhoto());
            }
        });
    }
}
