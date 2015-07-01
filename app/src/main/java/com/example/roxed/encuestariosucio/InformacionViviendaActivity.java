package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class InformacionViviendaActivity extends ActionBarActivity {

    Spinner spinnerZat;
    Spinner spinnerTipoVivienda;
    Spinner spinnerEstrato;
    private List<String> listaZatVivienda = new ArrayList<String>();
    private List<String> listaTipoVivienda = new ArrayList<String>();
    private List<String> listaEstrato = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_vivienda);


        spinnerZat = (Spinner) findViewById(R.id.spinnerZATvivienda);
        spinnerTipoVivienda = (Spinner) findViewById(R.id.spinnerTipoVivienda);
        spinnerEstrato = (Spinner) findViewById(R.id.spinnerEstrato);

        for (int i = 0; i< 14; i++)
            listaZatVivienda.add(String.valueOf((i+1)));

        ArrayAdapter<String> adaptadorZat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatVivienda);
        adaptadorZat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZat.setAdapter(adaptadorZat);

        listaTipoVivienda.add("CASA");
        listaTipoVivienda.add("APARTAMENTO");
        listaTipoVivienda.add("OTRO");
        ArrayAdapter<String> adaptadorTipoVivienda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoVivienda);
        adaptadorTipoVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoVivienda.setAdapter(adaptadorTipoVivienda);


        listaEstrato.add("1");
        listaEstrato.add("2");
        listaEstrato.add("3");
        ArrayAdapter<String> adaptadorEstrato = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaEstrato);
        adaptadorEstrato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstrato.setAdapter(adaptadorEstrato);


    }

    public void onClickContinuarVivienda(View view)
    {
        Intent intent = new Intent(this, InformacionHogarActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_vivienda, menu);
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
