package com.example.roxed.encuestariosucio;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListActivityHogar extends ListActivity{

    List<String> hogares = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_hogar);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllHogares();
        if (c.moveToFirst())
        {
            do{
                hogares.add("Id hogar: "+c.getString(0)+" Personas por hogar: "+c.getString(1)+" Personas en día típico: "+c.getString(2)+" Personas viajan día sabado: "+c.getString(3)+" Personas presentes: "+c.getString(4)+" Tipo propiedad: "+
                        c.getString(5)+" Ingresos mensuales "+c.getString(6)+" Número encuesta: "+c.getString(7)+" Id vivienda "+c.getString(8));
            }while(c.moveToNext());
        }
        db.close();

        String[] hogar = new String[hogares.size()];
        for (int i=0; i<hogares.size(); i++)
        {
            hogar[i] = hogares.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hogar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_hogar, menu);
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
