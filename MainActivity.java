package com.example.d18123347.lab8;

import android.database.Cursor;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    DatabaseExample db;
    ListView list;
    SimpleCursorAdapter myAdapter;
    Cursor cursor;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseExample(this);

        db.open();

        addRows();

        cursor = db.getCityPerson("Dublin");
        Log.i("test", "number of rows returned are" + cursor.getCount());
        String[] col = new String[] {"first_name","surname","city"};
        int[] id = new int[] {R.id.first_name,R.id.surname,R.id.city};
        myAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, col, id);
        setListAdapter(myAdapter);

        db.close();

    }

    public void onListItemClick(ListView list, View v, int position, long id){
        super.onListItemClick(list,v,position,id);
        Cursor cursor = (Cursor) myAdapter.getItem(position);
        //int columnIndex = cursor.getColumnIndex("first_name");
        String myName = cursor.getString(1);
        //Log.i("test", "the value of column index is " + columnIndex );
        Log.i("test", "the value of mystring is " + myName);
        Toast.makeText(this, myName, Toast.LENGTH_LONG).show();
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

        id = db.insertPerson(
                "Marc",
                "Mulligan",
                "Dublin");

        id = db.insertPerson(
                "Patrick",
                "Bezy",
                "Limerick");
    }
}


