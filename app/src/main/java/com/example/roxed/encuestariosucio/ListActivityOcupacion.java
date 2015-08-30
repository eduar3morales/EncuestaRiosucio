package com.example.roxed.encuestariosucio;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class ListActivityOcupacion extends ListActivity {

    List<String> ocupaciones = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_ocupacion_principal);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllOcupacion();
        if (c.moveToFirst())
        {
            do{
                ocupaciones.add("Id:"+c.getString(0)+" Ocupación:"+c.getString(1)+" Lugar estudio:"+c.getString(2)+" Sector trabajo:"+c.getString(3)+" Labor desempeño:"+c.getString(4)+" Dirección actividad:"+
                        c.getString(5)+" Zat actividad:"+c.getString(6)+" Tipo ocupación:"+c.getString(7)+" Id persona:"+c.getString(8));
                String str = c.getString(0)+","+c.getString(1)+","+c.getString(2)+","+c.getString(3)+","+c.getString(4)+","+
                        c.getString(5)+","+c.getString(6)+","+c.getString(7)+","+c.getString(8);
                try
                {

                    FileOutputStream fOut = openFileOutput("ocupacion.txt", MODE_APPEND);
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

        String[] ocupacion = new String[ocupaciones.size()];
        for (int i=0; i<ocupaciones.size(); i++)
        {
            ocupacion[i] = ocupaciones.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ocupacion));
    }

    public void onListItemClick(ListView parent, View v, int position, long id)
    {
        Toast.makeText(this, "Ha seleccionado:"+ocupaciones.get(position), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_ocupacion_principal, menu);
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
