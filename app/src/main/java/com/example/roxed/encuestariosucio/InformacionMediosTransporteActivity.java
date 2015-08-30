package com.example.roxed.encuestariosucio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    /**
     * Lista donde se almacenan los valores de tipo vehículo que se muestran en el Spinner tipo vehículo
     */
    private List<String> listaTipoVehiculo = new ArrayList<String>();

    /**
     * Lista donde se almacenan los valores de Modelo Vehículo que se muestran en el Spinner modelo vehículo
     */
    private List<String> listaModeloVehiculo = new ArrayList<String>();

    /**
     * Lista donde se almacenan los valores de sitio estacionamiento que se muestran en el Spinner sitio de estacionamiento
     */
    private List<String> listaSitioEstacionamiento = new ArrayList<String>();


    /**
     * Variable donde se guarda el tipo de vehículo obtenido del Spinner tipo vehículo
     */
    String tipoVehiculo;

    /**
     * Variable donde se guarda el modelo del vehículo obtenido del Spinner modelo vehículo
     */
    String modeloVehiculo;

    /**
     * Variable donde se guardan los kilometros del último año
     */
    String kmUltimoA;

    /**
     * Variable donde se guarda el Lugar de Matrigula del medio de transporte
     */
    String lugarMatricula;

    /**
     * Variable donde se guarda el sitio de estacionamiento
     */
    String sitioEstacionamiento;

    /**
     * Valor que contiene el id de hogar proveniente de la actividad hogar
     */
    String idHogar; //Valor proveniente del Intent

    /**
     * Valor que contiene la Zat de la vivienda proveniente de la actividad vivienda
     */
    String zatVivienda;

    /**
     * Valor que contiene el número de encuesta proveniente de la actividad principal
     */
    String numeroEncuesta;

    String codigoOrden = "0";
    String viajeSabado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_medios_transporte);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Es necesario agregar todos lo vehículos pertenecientes al hogar, solo cuando este seguro de ésto, continue" +
                " con las siguientes preguntas")
                .setTitle("Recuerde!")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();


        spinnerTipoVehiculo = (Spinner) findViewById(R.id.spinnerTipoVehiculo);
        spinnerModeloVehiculo = (Spinner) findViewById(R.id.spinnerModeloVehiculo);
        spinnerSitioEstacionamiento = (Spinner) findViewById(R.id.spinnerSitioEstacionamiento);
        txtKilometro = (EditText) findViewById(R.id.txtKmUltimoAnio);
        txtLugarMatricula = (EditText) findViewById(R.id.txtLugarMatricula);

        listaTipoVehiculo.add("NO TIENE VEHÍCULO");
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


        spinnerTipoVehiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerTipoVehiculo.getSelectedItem().toString().equals("NO TIENE VEHÍCULO")) {
                    spinnerModeloVehiculo.setEnabled(false);
                    txtLugarMatricula.setEnabled(false);
                    spinnerSitioEstacionamiento.setEnabled(false);
                    txtKilometro.setEnabled(false);
                } else {
                    spinnerModeloVehiculo.setEnabled(true);
                    txtLugarMatricula.setEnabled(true);
                    spinnerSitioEstacionamiento.setEnabled(true);
                    txtKilometro.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        idHogar = getIntent().getStringExtra("idHogar");
        //Toast.makeText(this, "Id Hogar: "+idHogar, Toast.LENGTH_SHORT).show();
        zatVivienda = getIntent().getStringExtra("zatVivienda");
        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");
        viajeSabado = getIntent().getStringExtra("sabado");
        //Toast.makeText(this, "Viaje sabado: "+viajeSabado, Toast.LENGTH_SHORT).show();

    }

    /**
     *Función que permite hacer el registro de los Medios de Transporte y lleva a la siguiente actividad
     * donde se registran lo datos de persona
     */
    public void onClickContinuarMediosTransporte(View view) {


        tipoVehiculo = spinnerTipoVehiculo.getSelectedItem().toString();
        modeloVehiculo = spinnerModeloVehiculo.getSelectedItem().toString();
        kmUltimoA = txtKilometro.getText().toString();
        lugarMatricula = txtLugarMatricula.getText().toString();
        sitioEstacionamiento = spinnerSitioEstacionamiento.getSelectedItem().toString();

        if (tipoVehiculo.equals("NO TIENE VEHÍCULO"))
        {
            modeloVehiculo = "";
            lugarMatricula = "";
            sitioEstacionamiento = "";
            kmUltimoA = "";
        }

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento, idHogar);
        db.close();

        Intent intent = new Intent(this,InformacionPersonaActivity. class);
        intent.putExtra("idHogar", idHogar);
        intent.putExtra("zatVivienda", zatVivienda);
        intent.putExtra("nroEncuesta", numeroEncuesta);
        intent.putExtra("codigoOrden", codigoOrden);
        intent.putExtra("vehiculo", tipoVehiculo);
        intent.putExtra("sabado", viajeSabado);
        startActivity(intent);
        finish();
    }


    /**
     * Función que permite hacer el registro de los Medios de Transporte y permite agregar otro medio de transporte
     */
    public void onClickContinuarAgregarMedioTransporte(View view){

        tipoVehiculo = spinnerTipoVehiculo.getSelectedItem().toString();
        modeloVehiculo = spinnerModeloVehiculo.getSelectedItem().toString();
        kmUltimoA = txtKilometro.getText().toString();
        lugarMatricula = txtLugarMatricula.getText().toString();
        sitioEstacionamiento = spinnerSitioEstacionamiento.getSelectedItem().toString();

        if (tipoVehiculo.equals("NO TIENE VEHÍCULO"))
        {
            modeloVehiculo = "";
            lugarMatricula = "";
            sitioEstacionamiento = "";
            kmUltimoA = "";

            /*DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento, idHogar);
            db.close();*/

            Toast.makeText(this, "Esta opción no esta disponible si no se tiene vehículo, por favor presione el botón continuar", Toast.LENGTH_LONG).show();
        }
        else
        {
            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertMediosTransporte(tipoVehiculo, modeloVehiculo, kmUltimoA, lugarMatricula, sitioEstacionamiento, idHogar);
            db.close();

            Intent intent = new Intent(this,InformacionMediosTransporteActivity. class);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("zatVivienda", zatVivienda);
            intent.putExtra("sabado", viajeSabado);

            startActivity(intent);
            finish();
        }


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
