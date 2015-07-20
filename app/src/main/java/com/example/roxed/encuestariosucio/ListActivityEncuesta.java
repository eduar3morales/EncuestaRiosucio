package com.example.roxed.encuestariosucio;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListActivityEncuesta extends ListActivity {
    List<String> encuestas = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_encuesta);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c =  db.getAllEncuestas();
        if (c.moveToFirst())
        {
            do{
                encuestas.add("Número Encuesta: "+c.getString(0)+" Coordinador: "+c.getString(1)+" Encuestador: "+c.getString(2)+" "+c.getString(3)+" Fecha: "+ c.getString(4));

            }while(c.moveToNext());
        }
        db.close();
        String[] encuesta = new  String[encuestas.size()];

        for (int i=0; i<encuestas.size(); i++ )
        {
            encuesta[i] = encuestas.get(i);
        }

        Toast.makeText(this, "N: "+ encuestas.size(), Toast.LENGTH_SHORT).show();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, encuesta));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_encuesta, menu);
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
