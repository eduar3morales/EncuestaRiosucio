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


public class ListActivityViaje extends ListActivity {

    List<String> viajes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_viaje);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllViajes();
        if (c.moveToFirst())
        {
            do{
                viajes.add("Id Viaje"+c.getString(0)+" No viaje:"+c.getString(1)+" Lugar origen:"+c.getString(2)+" Zat origen:"+c.getString(3)+" Hora salida:"+c.getString(4)+" Lugar destino:"+c.getString(5)+" Zat destino:"+
                        c.getString(6)+" Hora llegada"+c.getString(7)+" Motivo viaje:"+c.getString(8)+" Modo viaje:"+c.getString(9)+" Id persona:"+c.getString(10));
                String str = c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+c.getString(4)+","+c.getString(5)+","+
                        c.getString(6)+","+c.getString(7)+","+c.getString(8)+","+c.getString(9)+","+c.getString(10);
                try
                {

                    FileOutputStream fOut = openFileOutput("viaje.txt", MODE_APPEND);
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

        String[] viaje = new String[viajes.size()];

        for (int i=0; i<viajes.size(); i++)
        {
            viaje[i] = viajes.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, viaje));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_viaje, menu);
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
