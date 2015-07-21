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


public class ListActivityMediosTransporteDificilAcceso extends ListActivity {

    List<String> mediosTransporte = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_medios_transporte_dificil_acceso);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllModoTransporteDificilAcceso();
        if (c.moveToFirst())
        {
            do{
                mediosTransporte.add("Id:"+c.getString(0)+" Modo transporte:"+c.getString(1)+" Id persona:"+c.getString(2));
            }while (c.moveToNext());
        }
        db.close();

        String[] modoTransporte = new String[mediosTransporte.size()];
        for (int i=0; i<mediosTransporte.size(); i++)
        {
            modoTransporte[i] = mediosTransporte.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modoTransporte));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_medios_transporte_dificil_acceso, menu);
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
