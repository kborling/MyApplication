package com.example.kevin.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private  DBHelper db;

    ListView listViewTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up List View
        listViewTables = (ListView)findViewById(R.id.list);

        // Initialize database
        db = new DBHelper(this);
        // Retrieve data
        db.insertNotesRecord(new Notes(1, "10:00", "Some note", "12:50"));
        db.insertSettingsRecord(new Settings("Some key", "Some value"));
        db.insertActionLogRecord(new ActionLog(1, "Some action", "11:00", "12:52"));

        // Read from each Table for testing
        for(Notes notes : db.getNotesRecord()) {
            Log.i("Table Name " , "notes");
            Log.i("Videold " , notes.getVideold() + "");
            Log.i("Time " , notes.getTime());
            Log.i("Note " , notes.getNote());
            Log.i("Sent " , notes.getSent());
        }

        for(Settings settings : db.getSettingsRecord()) {
            Log.i("Table Name " , "settings");
            Log.i("Key " , settings.getKey());
            Log.i("Value " , settings.getValue());
        }

        for(ActionLog actionlog : db.getActionLogRecord()) {
            Log.i("Table Name " , "action log");
            Log.i("Videold " , actionlog.getVideold() + "");
            Log.i("ActionType " , actionlog.getActiontype());
            Log.i("Time " , actionlog.getTime());
            Log.i("Sent " , actionlog.getSent());
        }

        } catch(Exception e){
            Log.d("SQLiteActivity", Log.getStackTraceString(e));
        }

        // TODO: Create appropriate layout to display data via ArrayAdapter

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    } // End onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // End onCreateOptionsMenu

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
    } // End onOptionsItemSelected

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        } // End onCreateView
    } // End Placeholder Fragment


} // End Main Activity
