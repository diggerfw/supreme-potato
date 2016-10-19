package ua.org.klug.planerka.views.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import ua.org.klug.planerka.R;
import ua.org.klug.planerka.model.Meeting;
import ua.org.klug.planerka.views.adapters.MeetingsAdapter;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private final String KOLOSS_ID = "ChIJ-WsTpTWhJ0ERylq6-OKhL_4";
    private RecyclerView recyclerView;
    private MeetingsAdapter adapter;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final View coordinator = findViewById(R.id.coordinator);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_collapsing);
        collapsingToolbarLayout.setTitle("TITLE_TITLE-TITLE-TITLE");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        collapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);

        final ImageView image = (ImageView) findViewById(R.id.image);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int PLACE_PICKER_REQUEST = 1;

                Places.GeoDataApi.getPlaceById(mGoogleApiClient, KOLOSS_ID).setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        Place place = places.get(0);
                        String name = String.valueOf(place.getName());
                        collapsingToolbarLayout.setTitle(name);
                        String address = String.valueOf(place.getAddress());
                        String phoneNumber = String.valueOf(place.getPhoneNumber());
                        String locale = String.valueOf(place.getLocale());
                        places.release();
                    }
                });

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
        });


        recyclerView = (RecyclerView) findViewById(R.id.list_actions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MeetingsAdapter();
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));
        meetings.add(new Meeting("Pivo - soki", "v pyatnizy"));

        adapter.setMeetings(meetings);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "google service connection failed", Toast.LENGTH_LONG).show();
    }
}
