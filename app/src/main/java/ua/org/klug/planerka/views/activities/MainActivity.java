package ua.org.klug.planerka.views.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import ua.org.klug.planerka.R;
import ua.org.klug.planerka.model.Meeting;
import ua.org.klug.planerka.views.adapters.MeetingsAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MeetingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final View coordinator = findViewById(R.id.coordinator);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                coordinator.buildDrawingCache(true);
                Bitmap bitmap = coordinator.getDrawingCache(true).copy(Bitmap.Config.ARGB_8888, false);
                coordinator.destroyDrawingCache();
                try {
                    String path = getCacheDir() + "file1.jpeg";
                    FileOutputStream out = new FileOutputStream(path);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("image/*");
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "email subject");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "your opinion wery important to us");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content:///" + path));
                    emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    startActivityForResult(Intent.createChooser(emailIntent, "you must choose"), 1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_collapsing);
        collapsingToolbarLayout.setTitle("TITLE_TITLE-TITLE-TITLE");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        collapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);

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
}
