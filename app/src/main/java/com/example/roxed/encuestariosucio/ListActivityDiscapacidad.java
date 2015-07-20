package com.example.roxed.encuestariosucio;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListActivityDiscapacidad extends ListActivity {

    List<String> discapacidades = new ArrayList<String >();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_discapacidad);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllDiscapacidad();
        if (c.moveToFirst())
        {
            do{
                discapacidades.add(c.getString(0)+" "+c.getString(1)+" "+c.getString(3));
            }while (c.moveToNext());
        }
        db.close();

        String[] discapacidad = new String[discapacidades.size()];
        for (int i=0; i<discapacidades.size(); i++)
        {
            discapacidad[i] = discapacidades.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, discapacidad));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_discapacidad, menu);
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
