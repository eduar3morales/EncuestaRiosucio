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


public class ListActivityVivienda extends ListActivity {
    List<String> viviendas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_vivienda);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllViviendas();
        if (c.moveToFirst())
        {
            do{
                viviendas.add("Id vivienda:"+c.getString(0)+" Barrio:"+c.getString(1)+" Estrato:"+c.getString(2)+" Dirección:"+c.getString(3)+" ZAT:"+c.getString(4)+" Teléfono:"+
                c.getString(5)+" Celular:"+c.getString(6)+" Tipo vivienda:"+c.getString(7)+" Cantidad hogares en vivienda:"+c.getString(8));
                String str = c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+c.getString(4)+","+
                        c.getString(5)+","+c.getString(6)+","+c.getString(7)+","+c.getString(8);
                try
                {

                    FileOutputStream fOut = openFileOutput("vivienda.txt", MODE_APPEND);
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

        String[] vivienda = new String[viviendas.size()];
        for (int i=0; i < viviendas.size(); i++)
        {
            vivienda[i]= viviendas.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vivienda));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_vivienda, menu);
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
