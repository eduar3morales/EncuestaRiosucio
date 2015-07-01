package com.example.roxed.encuestariosucio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class InformacionHogarActivity extends ActionBarActivity {

    Spinner spinnerTipoPropiedad;
    Spinner spinnerRangoIngresosMensuales;
    private List<String> listaTipoPropiedad = new ArrayList<String>();
    private List<String> listaRangoIngresosMensuales = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_hogar);
        spinnerTipoPropiedad = (Spinner) findViewById(R.id.spinnerTipoPropiedad);
        spinnerRangoIngresosMensuales = (Spinner) findViewById(R.id.spinnerRangoIngresosMensuales);

        listaTipoPropiedad.add("PROPIA");
        listaTipoPropiedad.add("ALQUILADA POR USTED");
        listaTipoPropiedad.add("OTRO");
        ArrayAdapter<String> adaptadorTipoPropiedad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoPropiedad);
        adaptadorTipoPropiedad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPropiedad.setAdapter(adaptadorTipoPropiedad);

        listaRangoIngresosMensuales.add("$0 - $644.350");
        listaRangoIngresosMensuales.add("$644.351 - $1.300.000");
        listaRangoIngresosMensuales.add("$1.300.001 - $2.300.000");
        listaRangoIngresosMensuales.add("$2.300.001 - $3.200.000");
        listaRangoIngresosMensuales.add("$3.200.001 - $4.500.000");
        listaRangoIngresosMensuales.add("$4.500.001 - $6.300.000");
        listaRangoIngresosMensuales.add("$6.300.001 - $9.000.000");
        listaRangoIngresosMensuales.add("MAYOR A - $9.000.000");
        ArrayAdapter<String> adaptadorRangoIngresosMensuales = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaRangoIngresosMensuales);
        adaptadorRangoIngresosMensuales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRangoIngresosMensuales.setAdapter(adaptadorRangoIngresosMensuales);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_hogar, menu);
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