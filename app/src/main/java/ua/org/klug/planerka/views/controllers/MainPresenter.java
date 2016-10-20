package ua.org.klug.planerka.views.controllers;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
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

public class MainPresenter extends BaseObservable {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";


    private String mTitle;

    private GoogleApiClient mGoogleApiClient;

    public MainPresenter(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
        getPlaceInfo();
    }

    private void getPlaceInfo() {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, KOLOSS_ID).
                setResultCallback(new ResultCallback<PlaceBuffer>() {
                                      @Override
                                      public void onResult(@NonNull PlaceBuffer places) {
                                          Place place = places.get(0);
                                          setTitle(String.valueOf(place.getName()));
                                          places.release();
                                      }
                                  }

                );
    }

    private void getPlacePhoto() {
                Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, KOLOSS_ID).setResultCallback(new ResultCallback<PlacePhotoMetadataResult>() {
                    @Override
                    public void onResult(@NonNull PlacePhotoMetadataResult placePhotoMetadataResult) {
                        PlacePhotoMetadata placePhotoMetadata = placePhotoMetadataResult.getPhotoMetadata().get(0);
                        placePhotoMetadata.getPhoto(mGoogleApiClient).setResultCallback(new ResultCallback<PlacePhotoResult>() {
                            @Override
                            public void onResult(@NonNull PlacePhotoResult placePhotoResult) {
                                image.setImageBitmap(placePhotoResult.getBitmap());
                            }
                        });

                        placePhotoMetadataResult.getPhotoMetadata().release();;
                    }
                });
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }
}
