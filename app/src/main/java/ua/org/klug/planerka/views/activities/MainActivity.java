package ua.org.klug.planerka.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
