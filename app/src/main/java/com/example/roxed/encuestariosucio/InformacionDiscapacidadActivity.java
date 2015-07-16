package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class InformacionDiscapacidadActivity extends ActionBarActivity {

    private CheckBox checkBoxBicicleta;
    private CheckBox checkBoxTaxi;
    private CheckBox checkBoxBus;
    private CheckBox checkBoxAutomovil;
    private CheckBox checkBoxMoto;
    private CheckBox checkBoxOtro;
    private CheckBox checkBoxNingunos;
    private CheckBox checkBoxSillaRuedas;
    private CheckBox checkBoxMuleta;
    private CheckBox checkBoxBaston;
    private CheckBox checkBoxCaminador;
    private CheckBox checkBoxOtros;
    private CheckBox checkBoxNinguno;


    Spinner spinnerTipoDiscapacidad;
    Spinner spinnerDuracionDiscapacidad;

    String tipoDiscapacidad;
    String duracionDiscapacidad = "NO APLICA";
    //String[] herramientaApoyo;
    //String[] mediosTransporteDificilAcceso;
    List<String> herramientaApoyo = new ArrayList<String>();
    List <String> medioTransporteDificilAcceso = new ArrayList<String>();
    String idPersona;

    private List<String> listaTipoDiscapacidad = new ArrayList<String>();
    private List<String> listaDuracionDiscapacidad = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_discapacidad);


        spinnerTipoDiscapacidad = (Spinner) findViewById(R.id.spinnerTipoDiscapacidad);
        spinnerDuracionDiscapacidad = (Spinner) findViewById(R.id.spinnerDuracionDiscapacidad);

        checkBoxBicicleta = (CheckBox) findViewById(R.id.checkBoxBic);
        checkBoxTaxi = (CheckBox) findViewById(R.id.checkBoxTax);
        checkBoxBus = (CheckBox) findViewById(R.id.checkBoxBus);
        checkBoxAutomovil = (CheckBox) findViewById(R.id.checkBoxAut);
        checkBoxMoto = (CheckBox) findViewById(R.id.checkBoxMot);
        checkBoxOtro = (CheckBox) findViewById(R.id.checkBoxOtro);
        checkBoxNingunos = (CheckBox) findViewById(R.id.checkNing);
        checkBoxSillaRuedas = (CheckBox) findViewById(R.id.checkBoxSill);
        checkBoxMuleta = (CheckBox) findViewById(R.id.checkBoxMul);
        checkBoxBaston = (CheckBox) findViewById(R.id.checkBoxBas);
        checkBoxCaminador = (CheckBox) findViewById(R.id.checkBoxCam);
        checkBoxOtros = (CheckBox) findViewById(R.id.checkBoxOtros);
        checkBoxNinguno = (CheckBox) findViewById(R.id.checkBoxNin);

        checkBoxTaxi.setChecked(false);
        checkBoxMuleta.setChecked(false);
        checkBoxMoto.setChecked(false);
        checkBoxOtros.setChecked(false);


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

        spinnerTipoDiscapacidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).toString().equals("NINGUNA"))
                {
                    spinnerDuracionDiscapacidad.setEnabled(false);
                    checkBoxNinguno.setChecked(true);
                    checkBoxNingunos.setChecked(true);

                    checkBoxNinguno.setEnabled(false);
                    checkBoxNingunos.setEnabled(false);
                    checkBoxBicicleta.setEnabled(false);
                    checkBoxTaxi.setEnabled(false);
                    checkBoxBus.setEnabled(false);
                    checkBoxAutomovil.setEnabled(false);
                    checkBoxMoto.setEnabled(false);
                    checkBoxOtro.setEnabled(false);
                    checkBoxSillaRuedas.setEnabled(false);
                    checkBoxMuleta.setEnabled(false);
                    checkBoxBaston.setEnabled(false);
                    checkBoxCaminador.setEnabled(false);
                    checkBoxOtros.setEnabled(false);
                    Toast.makeText(getBaseContext(), "En este caso la duración de la discapacidad no aplica", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    spinnerDuracionDiscapacidad.setEnabled(true);
                    checkBoxNinguno.setChecked(false);
                    checkBoxNingunos.setChecked(false);

                    checkBoxNinguno.setEnabled(true);
                    checkBoxNingunos.setEnabled(true);
                    checkBoxBicicleta.setEnabled(true);
                    checkBoxTaxi.setEnabled(true);
                    checkBoxBus.setEnabled(true);
                    checkBoxAutomovil.setEnabled(true);
                    checkBoxMoto.setEnabled(true);
                    checkBoxOtro.setEnabled(true);
                    checkBoxSillaRuedas.setEnabled(true);
                    checkBoxMuleta.setEnabled(true);
                    checkBoxBaston.setEnabled(true);
                    checkBoxCaminador.setEnabled(true);
                    checkBoxOtros.setEnabled(true);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        idPersona = getIntent().getStringExtra("idPersona");
        //Toast.makeText(this, idPersona, Toast.LENGTH_SHORT).show();



    }

    public void onClickContinuarInformacionOcupacionSecundaria(View view)
    {
        tipoDiscapacidad = spinnerTipoDiscapacidad.getSelectedItem().toString();
        duracionDiscapacidad = spinnerDuracionDiscapacidad.getSelectedItem().toString();
        if (checkBoxBicicleta.isChecked())
            medioTransporteDificilAcceso.add("BICICLETA");

        if (checkBoxTaxi.isChecked())
            medioTransporteDificilAcceso.add("TAXI");

        if (checkBoxBus.isChecked())
            medioTransporteDificilAcceso.add("BUS");

        if (checkBoxAutomovil.isChecked())
            medioTransporteDificilAcceso.add("AUTOMOVIL");

        if (checkBoxMoto.isChecked())
            medioTransporteDificilAcceso.add("MOTO");

        if (checkBoxOtro.isChecked())
            medioTransporteDificilAcceso.add("OTROS");

        if (checkBoxSillaRuedas.isChecked())
            herramientaApoyo.add("SILLA DE RUEDAS");

        if (checkBoxMuleta.isChecked())
            herramientaApoyo.add("MULETA");

        if (checkBoxBaston.isChecked())
            herramientaApoyo.add("BASTON");

        if (checkBoxCaminador.isChecked())
            herramientaApoyo.add("CAMINADOR");

        if (checkBoxOtros.isChecked())
            herramientaApoyo.add("OTROS");


        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertDiscapacidad(tipoDiscapacidad, duracionDiscapacidad, idPersona);

        for (int i=0; i<herramientaApoyo.size(); i++)
            id = db.insertHerramientaApoyo(herramientaApoyo.get(i), idPersona);

        for (int i=0; i<medioTransporteDificilAcceso.size(); i++)
            id = db.insertModoTransporteDificilAcceso(medioTransporteDificilAcceso.get(i), idPersona);

        db.close();

        Intent intent = new Intent(this, InformacionOcupacionSecundariaActivity.class);
        //intent.putExtra("idPersona", idPersona);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
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
