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


public class InformacionMediosTransporteActivity extends ActionBarActivity {


    Spinner spinnerTipoVehiculo;
    Spinner spinnerModeloVehiculo;
    Spinner spinnerSitioEstacionamiento;
    EditText txtKilometro;
    EditText txtLugarMatricula;

    private List<String> listaTipoVehiculo = new ArrayList<String>();
    private List<String> listaModeloVehiculo = new ArrayList<String>();
    private List<String> listaSitioEstacionamiento = new ArrayList<String>();


    String tipoVehiculo;
    String modeloVehiculo;
    String kmUltimoA;
    String lugarMatricula;
    String sitioEstacionamiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_medios_transporte);


        spinnerTipoVehiculo = (Spinner) findViewById(R.id.spinnerTipoVehiculo);
        spinnerModeloVehiculo = (Spinner) findViewById(R.id.spinnerModeloVehiculo);
        spinnerSitioEstacionamiento = (Spinner) findViewById(R.id.spinnerSitioEstacionamiento);
        txtKilometro = (EditText) findViewById(R.id.txtKmUltimoAnio);
        txtLugarMatricula = (EditText) findViewById(R.id.txtLugarMatricula);


        listaTipoVehiculo.add("AUTOMOVIL");
        listaTipoVehiculo.add("CAMPERO O CAMIONETA");
        listaTipoVehiculo.add("CAMION PEQUEÑO (1,5 - 3,5 Ton)");
        listaTipoVehiculo.add("MOTO");
        listaTipoVehiculo.add("MOTOCARRO");
        listaTipoVehiculo.add("MOTOTAXI");
        listaTipoVehiculo.add("TAXI");
        listaTipoVehiculo.add("TAXI NO PROPIO");
        listaTipoVehiculo.add("BICICLETA");
        listaTipoVehiculo.add("OTRO");
        ArrayAdapter<String> adaptadorTipoVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoVehiculo);
        adaptadorTipoVehiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoVehiculo.setAdapter(adaptadorTipoVehiculo);

        for (int i = 1980; i < 2016; i++)
            listaModeloVehiculo.add("" + i);
        ArrayAdapter<String> adaptadorModeloVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaModeloVehiculo);
        adaptadorModeloVehiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModeloVehiculo.setAdapter(adaptadorModeloVehiculo);

        listaSitioEstacionamiento.add("PROPIO");
        listaSitioEstacionamiento.add("SOBRE LA VIA");
        listaSitioEstacionamiento.add("PARQUEADERO PAGADO");
        listaSitioEstacionamiento.add("PARQUEADERO GRATIS");


    }

    public void onClickContinuarMediosTransporte(View view) {

        tipoVehiculo = spinnerTipoVehiculo.getSelectedItem().toString();
        modeloVehiculo = spinnerModeloVehiculo.getSelectedItem().toString();
        kmUltimoA = txtKilometro.getText().toString();
        lugarMatricula = txtLugarMatricula.getText().toString();
        sitioEstacionamiento = spinnerSitioEstacionamiento.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento);
        db.close();

        Intent intent = new Intent(this,InformacionPersonaActivity. class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_medios_transporte, menu);
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
