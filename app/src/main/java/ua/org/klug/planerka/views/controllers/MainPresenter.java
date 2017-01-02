package ua.org.klug.planerka.views.controllers;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnViewAttachedToWindow;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.common.api.GoogleApiClient;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import ua.org.klug.planerka.R;
import ua.org.klug.planerka.model.Meeting;
import ua.org.klug.planerka.repositories.PlaceRepository;
import ua.org.klug.planerka.utils.Toasts;

public class MainPresenter extends BaseObservable {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";


    private Bitmap mPlacePhoto;
    private Meeting mMeeting;
    private @DrawableRes int mPlacePhotoPlaceholder = R.mipmap.ic_launcher;
    private PlaceRepository mPlaceRepository;

    private GoogleApiClient mGoogleApiClient;

    public MainPresenter(GoogleApiClient googleApiClient, PlaceRepository placeRepository) {
        mGoogleApiClient = googleApiClient;
        mPlaceRepository = placeRepository;

        Action1<Throwable> errorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d("tag", "ERROR");
            }
        };

        mPlaceRepository
                .getMeeting()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Meeting>() {
                    @Override
                    public void call(Meeting meeting) {
                        setMeeting(meeting);
                    }

                }, errorAction);
        mPlaceRepository
                .getPlaceBitmap()
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        setPlacePhoto(bitmap);
                    }
                });
    }

    public void onFloatClick() {
        Log.d("Click", "FLOAT CLICK");
    }

    public void onAttached(OnViewAttachedToWindow listener) {
        Log.d("Click", "Attached");
    }

    @Bindable
    public OnViewAttachedToWindow getAttachedListener() {
        return new OnViewAttachedToWindow();
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
