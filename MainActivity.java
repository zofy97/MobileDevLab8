package com.example.d18123347.lab6;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    DatabaseExample db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        db = new DatabaseExample(this);
        db.open();
        addRows();
        Cursor c = db.getAllPeople();
        String[] columns = new String[] {"first_name", "surname", "city"};
        int[] rowIDs = new int[] {R.id.firstname_entry, R.id.surname_entry, R.id.city_entry};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, c, columns, rowIDs);

        setListAdapter(adapter);

        Log.i("test", "number of rows returned are" + c.getCount());

        db.close();

    }

    //---add some people---
    public void addRows()
    {
        long id;
        id = db.insertPerson(
                "Johny",
                "Fox",
                "Dublin");

        id = db.insertPerson(
                "Sarah",
                "Brown",
                "Monaghan");
    }


}


