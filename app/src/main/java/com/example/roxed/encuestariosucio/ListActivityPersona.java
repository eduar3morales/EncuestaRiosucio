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


public class ListActivityPersona extends ListActivity {
    List<String> personas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_activity_persona);

        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllPersonas();
        if (c.moveToFirst())
        {
            do {
                personas.add("Id persona: "+c.getString(0)+" Código orden: "+c.getString(1)+" Nombre: "+c.getString(2)+" Edad: "+c.getString(3)+" Genero: "+c.getString(4)+" Último nivel estudio: "+
                        c.getString(5)+" Uso red cicloruta: "+c.getString(6)+" Id hogar: "+c.getString(7));
            }while(c.moveToNext());
        }
        db.close();

        String[] persona = new String[personas.size()];
        for (int i = 0; i<personas.size(); i++)
        {
            persona[i] = personas.get(i);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, persona));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity_persona, menu);
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
