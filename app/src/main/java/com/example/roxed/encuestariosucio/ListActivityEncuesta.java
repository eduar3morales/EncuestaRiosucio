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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


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
                //encuestas.add("Número Encuesta: "+c.getString(0)+" Coordinador: "+c.getString(1)+" Encuestador: "+c.getString(2)+" "+c.getString(3)+" Fecha: "+ c.getString(4));
                encuestas.add("Número encuesta:"+c.getString(0)+" Coordinador:"+c.getString(1)+" Encuestador:"+c.getString(2)+" "+c.getString(3)+" "+ c.getString(4));
                String str = c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+ c.getString(4);
                try
                {

                    FileOutputStream fOut = openFileOutput("encuesta.txt", MODE_APPEND);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    osw.write(str+"\n");
                    osw.flush();
                    osw.close();

                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }

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
