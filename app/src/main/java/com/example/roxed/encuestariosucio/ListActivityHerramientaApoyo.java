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


public class ListActivityHerramientaApoyo extends ListActivity {

    List<String> herramientasApoyo = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_herramienta_apoyo);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllHerramientaApoyo();
        if(c.moveToFirst())
        {
            do{
                herramientasApoyo.add("Id:"+c.getString(0)+" Herramienta:"+c.getString(1)+" Id persona:"+c.getString(2));
            }while(c.moveToNext());
        }
        db.close();

        String[] herramientaApoyo = new String[herramientasApoyo.size()];
        for (int i=0; i<herramientasApoyo.size(); i++)
        {
            herramientaApoyo[i] = herramientasApoyo.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, herramientaApoyo));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_herramienta_apoyo, menu);
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
