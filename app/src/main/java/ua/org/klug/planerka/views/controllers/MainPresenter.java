package ua.org.klug.planerka.views.controllers;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;

import ua.org.klug.planerka.R;
import ua.org.klug.planerka.model.Meeting;
import ua.org.klug.planerka.repositories.PlaceRepository;

public class MainPresenter extends BaseObservable {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";


    private Bitmap mPlacePhoto;
    private Meeting mMeeting;
    private @DrawableRes int mPlacePhotoPlaceholder = R.mipmap.ic_launcher;
    private PlaceRepository mPlaceRepository;

    private GoogleApiClient mGoogleApiClient;

    public MainPresenter(GoogleApiClient googleApiClient, PlaceRepository placeRepository) {
        mGoogleApiClient = googleApiClient;
        mPlaceRepository.get
        receivePlacePhoto();
    }


    private void receivePlacePhoto() {
        Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, KOLOSS_ID).setResultCallback(new ResultCallback<PlacePhotoMetadataResult>() {
            @Override
            public void onResult(@NonNull PlacePhotoMetadataResult placePhotoMetadataResult) {
                PlacePhotoMetadata placePhotoMetadata = placePhotoMetadataResult.getPhotoMetadata().get(0);
                placePhotoMetadata.getPhoto(mGoogleApiClient).setResultCallback(new ResultCallback<PlacePhotoResult>() {
                    @Override
                    public void onResult(@NonNull PlacePhotoResult placePhotoResult) {
                        setPlacePhoto(placePhotoResult.getBitmap());
                    }
                });
                placePhotoMetadataResult.getPhotoMetadata().release();
            }
        });
    }

    @Bindable
    public Meeting getMeeting() {
        return mMeeting;
    }

    public void setMeeting(Meeting meeting) {
        mMeeting = meeting;
        notifyPropertyChanged(BR.meeting);
    }

    @Bindable
    public Bitmap getPlacePhoto() {
        return mPlacePhoto;
    }

    public void setPlacePhoto(Bitmap bitmap) {
        mPlacePhoto = bitmap;
        notifyPropertyChanged(BR.placePhoto);
    }

    @Bindable
    public int getPlaceholder() {
        return R.mipmap.ic_launcher;
    }

}
