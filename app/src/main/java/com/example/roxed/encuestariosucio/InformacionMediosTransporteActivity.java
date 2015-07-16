package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    String idHogar;


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

        for (int i = 1980; i < 2017; i++)
            listaModeloVehiculo.add("" + i);
        ArrayAdapter<String> adaptadorModeloVehiculo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaModeloVehiculo);
        adaptadorModeloVehiculo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModeloVehiculo.setAdapter(adaptadorModeloVehiculo);

        listaSitioEstacionamiento.add("PROPIO");
        listaSitioEstacionamiento.add("SOBRE LA VIA");
        listaSitioEstacionamiento.add("PARQUEADERO PAGADO");
        listaSitioEstacionamiento.add("PARQUEADERO GRATIS");
        ArrayAdapter<String> adaptadorSitioEstacionamiento = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSitioEstacionamiento);
        adaptadorSitioEstacionamiento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSitioEstacionamiento.setAdapter(adaptadorSitioEstacionamiento);

        /*DBAdapter db = new DBAdapter(this);
        Cursor c = db.getAllHogares();
        if (c.moveToFirst())
        {
            do{
                idHogar = c.getString(0);
                Toast.makeText(this,
                        "Id: " +c.getString(0)+ "\n"+
                                "Personas: " +c.getString(1)+ "\n"+
                                "Tipico: "+c.getString(2)+ "\n"+
                                "Sabado: "+c.getString(3)+"\n"+
                                "Presentes: "+c.getString(4)+"\n"+
                                "Tipo: "+c.getString(5)+"\n"+
                                "Ingresos: "+c.getString(6)+"\n"+
                                "Encuesta: "+c.getString(7)+"\n"+
                                "Vivienda: "+c.getString(8), Toast.LENGTH_LONG).show();
            }while (c.moveToNext());
        }
        db.close();*/

        idHogar = getIntent().getStringExtra("idHogar");
        //Toast.makeText(this, "Id Hogar: "+idHogar, Toast.LENGTH_SHORT).show();


    }

    public void onClickContinuarMediosTransporte(View view) {

        tipoVehiculo = spinnerTipoVehiculo.getSelectedItem().toString();
        modeloVehiculo = spinnerModeloVehiculo.getSelectedItem().toString();
        kmUltimoA = txtKilometro.getText().toString();
        lugarMatricula = txtLugarMatricula.getText().toString();
        sitioEstacionamiento = spinnerSitioEstacionamiento.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento, idHogar);

        Cursor c = db.getAllMediosTransporte();
        if (c.moveToFirst())
        {
            do{
                /*Toast.makeText(this,
                        "id: " + c.getString(0) + "\n" +
                                "Tipo vehículo: " + c.getString(1) + "\n" +
                                "Km: " + c.getString(2) + "\n" +
                                "Lugar MAtricula: " + c.getString(3) + "\n" +
                                "Sitio estacionamiento: " + c.getString(4)+"\n"+
                        "Id hogar: "+c.getString(5), Toast.LENGTH_LONG).show();*/
            }while (c.moveToNext());
        }

        db.close();


        Intent intent = new Intent(this,InformacionPersonaActivity. class);
        intent.putExtra("idHogar", idHogar);
        startActivity(intent);
        finish();
    }

    public void onClickContinuarAgregarMedioTransporte(View view){

        tipoVehiculo = spinnerTipoVehiculo.getSelectedItem().toString();
        modeloVehiculo = spinnerModeloVehiculo.getSelectedItem().toString();
        kmUltimoA = txtKilometro.getText().toString();
        lugarMatricula = txtLugarMatricula.getText().toString();
        sitioEstacionamiento = spinnerSitioEstacionamiento.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento, idHogar);
        db.close();
        Intent intent = new Intent(this,InformacionMediosTransporteActivity. class);
        intent.putExtra("idHogar", idHogar);
        startActivity(intent);
        finish();
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

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
