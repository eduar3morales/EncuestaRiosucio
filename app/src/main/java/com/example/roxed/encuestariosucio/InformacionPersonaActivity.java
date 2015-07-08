package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class InformacionPersonaActivity extends ActionBarActivity {

    Spinner spinnerCodigoDeOrden;
    Spinner spinnerEdad;
    Spinner spinnerGenero;
    Spinner spinnerNivelDeEstudios;
    Spinner spinnerUsoCicloruta;
    EditText txtNombre;

    String codigoOrden;
    String nombre;
    String edad;
    String genero;
    String ultimoNivelEstudio;
    String usoRedCicloRuta;

    private List<String> listaCodigoDeOrden= new ArrayList<String>();
    private List<String> listaEdad= new ArrayList<String>();
    private List<String> listaGenero= new ArrayList<String>();
    private List<String> listaNivelDeEstudios= new ArrayList<String>();
    private List<String> listaUsoCicloruta = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_persona);

        spinnerCodigoDeOrden= (Spinner) findViewById(R.id.spinnerCodigoDeOrden);
        spinnerEdad= (Spinner) findViewById(R.id.spinnerEdad);
        spinnerGenero= (Spinner) findViewById(R.id.spinnerGenero);
        spinnerNivelDeEstudios= (Spinner) findViewById(R.id.spinnerNivelDeEstudios);
        spinnerUsoCicloruta= (Spinner) findViewById(R.id.spinnerUsoCicloruta);
        txtNombre = (EditText) findViewById(R.id.txtNombre);


        for (int i = 1; i < 30; i++)
            listaCodigoDeOrden.add("" + i);

        ArrayAdapter<String> adaptadorCodigoDeOrden = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCodigoDeOrden);
        adaptadorCodigoDeOrden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCodigoDeOrden.setAdapter(adaptadorCodigoDeOrden);

        for (int i = 1; i < 120; i++)
            listaEdad.add("" + i);

        ArrayAdapter<String> adaptadorEdad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaEdad);
        adaptadorEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adaptadorEdad);

        listaGenero.add("HOMBRE");
        listaGenero.add("MUJER");

        ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaGenero);
        adaptadorGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adaptadorGenero);

        listaNivelDeEstudios.add("NINGUNO");
        listaNivelDeEstudios.add("PRIMARIA");
        listaNivelDeEstudios.add("BACHILLERATO");
        listaNivelDeEstudios.add("EDU. NO. FORMAL");
        listaNivelDeEstudios.add("TECNICO");
        listaNivelDeEstudios.add("TECNOLOGICO");
        listaNivelDeEstudios.add("UNIVERSITARIO");
        listaNivelDeEstudios.add("POSGRADO");
        ArrayAdapter<String> adaptadorNivelDeEstudios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaNivelDeEstudios);
        adaptadorNivelDeEstudios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelDeEstudios.setAdapter(adaptadorNivelDeEstudios);

        listaUsoCicloruta.add("SI");
        listaUsoCicloruta.add("NO");
        ArrayAdapter<String> adaptadorUsoCicloruta = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaUsoCicloruta);
        adaptadorUsoCicloruta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsoCicloruta.setAdapter(adaptadorUsoCicloruta);



    }

    public void onClickContinuarOcupacionPrincipal(View view) {
        codigoOrden = spinnerCodigoDeOrden.getSelectedItem().toString();
        nombre = txtNombre.getText().toString();
        genero = spinnerGenero.getSelectedItem().toString();
        edad = spinnerEdad.getSelectedItem().toString();
        usoRedCicloRuta = spinnerUsoCicloruta.getSelectedItem().toString();
        ultimoNivelEstudio = spinnerNivelDeEstudios.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertPersona(codigoOrden, nombre, edad, genero, ultimoNivelEstudio, usoRedCicloRuta);
        db.close();

        Intent intent = new Intent(this,InformacionOcupacionPrincipalActivity. class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_persona, menu);
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
