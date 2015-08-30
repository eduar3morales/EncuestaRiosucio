package com.example.roxed.encuestariosucio;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class ListActivityFrecuenciaViaje extends ListActivity {

    List<String> frecuenciaViajes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_frecuencia_viaje);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllFrecuenciaViaje();
        if (c.moveToFirst())
        {
            do{
                frecuenciaViajes.add("Id viaje:"+c.getString(0)+" Día(s):"+c.getString(1)+" Id viaje:"+c.getString(2));
                String str = c.getString(0)+","+c.getString(1)+","+c.getString(2);
                try
                {

                    FileOutputStream fOut = openFileOutput("frecuencia.txt", MODE_APPEND);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    osw.write(str+"\n");
                    osw.flush();
                    osw.close();

                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }

            }while (c.moveToNext());
        }
        db.close();

        String[] frecuenciaViaje = new String[frecuenciaViajes.size()];
        for (int i=0; i<frecuenciaViajes.size(); i++)
        {
            frecuenciaViaje[i] = frecuenciaViajes.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, frecuenciaViaje));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_frecuencia_viaje, menu);
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
