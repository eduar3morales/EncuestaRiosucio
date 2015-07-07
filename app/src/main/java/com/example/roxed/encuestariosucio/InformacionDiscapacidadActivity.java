package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class InformacionDiscapacidadActivity extends ActionBarActivity {


    Spinner spinnerTipoDiscapacidad;
    Spinner spinnerDuracionDiscapacidad;

    private List<String> listaTipoDiscapacidad = new ArrayList<String>();
    private List<String> listaDuracionDiscapacidad = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_discapacidad);


        spinnerTipoDiscapacidad = (Spinner) findViewById(R.id.spinnerTipoDiscapacidad);
        spinnerDuracionDiscapacidad = (Spinner) findViewById(R.id.spinnerDuracionDiscapacidad);
        




        listaTipoDiscapacidad.add("NINGUNA");
        listaTipoDiscapacidad.add("VER");
        listaTipoDiscapacidad.add("OIR");
        listaTipoDiscapacidad.add("HABLAR");
        listaTipoDiscapacidad.add("MOVERSE");
        listaTipoDiscapacidad.add("AGARRAR");
        listaTipoDiscapacidad.add("MENTAL");
        listaTipoDiscapacidad.add("OIR Y HABLAR");

        ArrayAdapter<String> adaptadorTipoDiscapacidad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoDiscapacidad);
        adaptadorTipoDiscapacidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDiscapacidad.setAdapter(adaptadorTipoDiscapacidad);


        listaDuracionDiscapacidad.add("PERMANENTE");
        listaDuracionDiscapacidad.add("TEMPORAL");

        ArrayAdapter<String> adaptadorDuracionDiscapacidad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDuracionDiscapacidad);
        adaptadorDuracionDiscapacidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDuracionDiscapacidad.setAdapter(adaptadorDuracionDiscapacidad);



    }

    public void onClickContinuarInformacionOcupacionSecundaria(View view)
    {
        Intent intent = new Intent(this, InformacionOcupacionSecundariaActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_discapacidad, menu);
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
