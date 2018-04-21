package com.example.pulkit.conveyor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Handler mHandler;

    private ConveyorAdapter mAdapter;

    private static final String REQUEST_URL = "https://api.thingspeak.com/channels/467288" +
                                                "/feeds.json?api_key=YDONQTWH17M2TP68&results=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView conveyorListView = findViewById(R.id.list);

        mAdapter = new ConveyorAdapter(this, new ArrayList<Conveyor>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interfaces
        conveyorListView.setAdapter(mAdapter);

        HttpGetRequest task = new HttpGetRequest();
        task.execute(REQUEST_URL);

        this.mHandler = new Handler();
        m_Runnable.run();
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();

            MainActivity.this.mHandler.postDelayed(m_Runnable,5000);
        }

    };

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    public class HttpGetRequest extends AsyncTask<String, Void, List<Conveyor>> {

        @Override
        protected List<Conveyor> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String number = sharedPrefs.getString("setting_number_of_values", "12");

            List<Conveyor> result = QueryUtils.fetchConveyorData(urls[0]+number);
            return result;
        }


        protected void onPostExecute(List<Conveyor> result) {
            mAdapter.clear();

            if (result != null && !result.isEmpty()) {
                mAdapter.addAll(result);
            }
        }
    }
}
