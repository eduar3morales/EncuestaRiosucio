package com.example.roxed.encuestariosucio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Path;
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


public class InformacionOcupacionSecundariaActivity extends ActionBarActivity {

    Spinner spinnerOcupacionSecundaria;
    Spinner spinnerLugarEstudioSecundaria;
    Spinner spinnerSectorTrabajoSecundaria;
    Spinner spinnerLaborDesempeñaSecundaria;
    Spinner spinnerZatActividadSecundaria;
    EditText txtDireccionActividadSecundaria;

    String ocupacion;
    String lugarEstudio;
    String sectorTrabajo;
    String laborDesempeño;

    String direccionActividadSecundaria;
    String zatActividadSecundaria;

    String idPersona; //Valor proveniente del Intent
    String zatVivienda;
    //String numeroViaje = "0";
    //int numeroViaje = 0;
    String numeroEncuesta;
    String idHogar;
    String cdOrden;
    String nViaje = "0";
    String ocupacionP;
    String tipoVehiculo;
    String viajeSabado;
    String hayMotivoViaje = "No";
    String zatActividad;
    static final String TIPO_OCUPACION = "Secundaria";




    private List<String> listaOcupacionSecundaria= new ArrayList<String>();
    private List<String> listaLugarEstudioSecundaria= new ArrayList<String>();
    private List<String> listaSectorTrabajoSecundaria= new ArrayList<String>();
    private List<String> listaLaborDesempeñaSecundaria= new ArrayList<String>();

    private List<String> listaZatActividadSecundaria = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_ocupacion_secundaria);

        spinnerOcupacionSecundaria= (Spinner) findViewById(R.id.spinnerOcupacionSecundaria);
        spinnerLugarEstudioSecundaria= (Spinner) findViewById(R.id.spinnerLugarEstudioSecundaria);
        spinnerSectorTrabajoSecundaria= (Spinner) findViewById(R.id.spinnerSectorTrabajoSecundaria);
        spinnerLaborDesempeñaSecundaria= (Spinner) findViewById(R.id.spinnerLaborDesempeñaSecundaria);
        //spinnerLugarTrabajoSecundaria=(Spinner) findViewById(R.id.spinnerLugarTrabajoSecundaria);
        spinnerZatActividadSecundaria=(Spinner) findViewById(R.id.spinnerZATActividadSecundaria);
        txtDireccionActividadSecundaria = (EditText) findViewById (R.id.txtDireccionActividadSecundaria);

        listaOcupacionSecundaria.add("NINGUNA");
        listaOcupacionSecundaria.add("ESTUDIAR");
        listaOcupacionSecundaria.add("TRABAJAR");
        listaOcupacionSecundaria.add("OFICIOS DEL HOGAR");
        listaOcupacionSecundaria.add("JUBILADO");
        listaOcupacionSecundaria.add("RENTISTA");
        listaOcupacionSecundaria.add("BUSCAR TRABAJO");
        listaOcupacionSecundaria.add("INCAPACIDAD PERMANENTE PARA TRABAJAR");
        listaOcupacionSecundaria.add("OTRA ACTIVIDAD");

        ArrayAdapter<String> adaptadorOcupacionSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaOcupacionSecundaria);
        adaptadorOcupacionSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOcupacionSecundaria.setAdapter(adaptadorOcupacionSecundaria);

        listaLugarEstudioSecundaria.add("COLEGIO - ESCUELA");
        listaLugarEstudioSecundaria.add("UNIVERSIDAD");
        listaLugarEstudioSecundaria.add("INSTITUTO TECNICO O TECNOLOGICO");
        listaLugarEstudioSecundaria.add("INSTITUTO DE EDUCACION NO FOMRAL");
        listaLugarEstudioSecundaria.add("OTRO");

        ArrayAdapter<String> adaptadorEstudioSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLugarEstudioSecundaria);
        adaptadorEstudioSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLugarEstudioSecundaria.setAdapter(adaptadorEstudioSecundaria);

        listaSectorTrabajoSecundaria.add("AGRICULTURA");
        listaSectorTrabajoSecundaria.add("MANTENIMIENTO Y REPARACION");
        listaSectorTrabajoSecundaria.add("MINERIA");
        listaSectorTrabajoSecundaria.add("MANUFACTURA");
        listaSectorTrabajoSecundaria.add("CONSTRUCCION");
        listaSectorTrabajoSecundaria.add("EDUCACION");
        listaSectorTrabajoSecundaria.add("FINANCIERO");
        listaSectorTrabajoSecundaria.add("SALUD");
        listaSectorTrabajoSecundaria.add("TRANSPORTE");
        listaSectorTrabajoSecundaria.add("HOTELES Y RESTAURANTES");
        listaSectorTrabajoSecundaria.add("COMERCIO");
        listaSectorTrabajoSecundaria.add("GOBIERNO");
        listaSectorTrabajoSecundaria.add("OTROS SERVICIOS");
        ArrayAdapter<String> adaptadorSectorTrabajoSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSectorTrabajoSecundaria);
        adaptadorSectorTrabajoSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSectorTrabajoSecundaria.setAdapter(adaptadorSectorTrabajoSecundaria);

        listaLaborDesempeñaSecundaria.add("OBRERO O EMPLEADO");
        listaLaborDesempeñaSecundaria.add("EMPLEADO DOMESTICO");
        listaLaborDesempeñaSecundaria.add("TRABAJADOR INDEPENDIENTE O CUENTA PROPIA");
        listaLaborDesempeñaSecundaria.add("PATRON O EMPLEADOR");
        listaLaborDesempeñaSecundaria.add("TRABAJADOR FAMILAIR SIN REMUNERACION");
        listaLaborDesempeñaSecundaria.add("OTRO");
        ArrayAdapter<String> adaptadorLaborDesempeñaSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLaborDesempeñaSecundaria);
        adaptadorLaborDesempeñaSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLaborDesempeñaSecundaria.setAdapter(adaptadorLaborDesempeñaSecundaria);

        for (int i = -1; i< 17; i++)
            listaZatActividadSecundaria.add(""+(i+1));

        ArrayAdapter<String> adaptadorZatActividadSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatActividadSecundaria);
        adaptadorZatActividadSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZatActividadSecundaria.setAdapter(adaptadorZatActividadSecundaria);


        //Toast.makeText(this, idPersona, Toast.LENGTH_SHORT).show();


        spinnerOcupacionSecundaria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerOcupacionSecundaria.getSelectedItem().toString().equals("ESTUDIAR"))
                {
                    spinnerLugarEstudioSecundaria.setEnabled(true);
                    spinnerSectorTrabajoSecundaria.setEnabled(false);
                    spinnerLaborDesempeñaSecundaria.setEnabled(false);
                    txtDireccionActividadSecundaria.setEnabled(true);
                    spinnerZatActividadSecundaria.setEnabled(true);


                }
                else if (spinnerOcupacionSecundaria.getSelectedItem().toString().equals("TRABAJAR"))
                {
                    spinnerLugarEstudioSecundaria.setEnabled(false);
                    spinnerSectorTrabajoSecundaria.setEnabled(true);
                    spinnerLaborDesempeñaSecundaria.setEnabled(true);
                    txtDireccionActividadSecundaria.setEnabled(true);
                    spinnerZatActividadSecundaria.setEnabled(true);
                }
                else if (spinnerOcupacionSecundaria.getSelectedItem().toString().equals("NINGUNA"))
                {
                    spinnerLugarEstudioSecundaria.setEnabled(false);
                    spinnerSectorTrabajoSecundaria.setEnabled(false);
                    spinnerLaborDesempeñaSecundaria.setEnabled(false);
                    txtDireccionActividadSecundaria.setEnabled(false);
                    spinnerZatActividadSecundaria.setEnabled(false);
                }
                else
                {
                    spinnerLugarEstudioSecundaria.setEnabled(false);
                    spinnerSectorTrabajoSecundaria.setEnabled(false);
                    spinnerLaborDesempeñaSecundaria.setEnabled(false);
                    txtDireccionActividadSecundaria.setEnabled(false);
                    spinnerZatActividadSecundaria.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        idPersona = getIntent().getStringExtra("idPersona");

        zatVivienda = getIntent().getStringExtra("zatVivienda");

        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");

        idHogar = getIntent().getStringExtra("idHogar");

        cdOrden = getIntent().getStringExtra("codigoOrden");

        ocupacionP = getIntent().getStringExtra("ocupacion");

        tipoVehiculo = getIntent().getStringExtra("vehiculo");

        viajeSabado = getIntent().getStringExtra("sabado");

        zatActividad = getIntent().getStringExtra("zatActividad");

        //Toast.makeText(this, "Id persona: "+ idPersona, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Codigo ordne: "+cdOrden, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Tipo vehiculo: "+tipoVehiculo, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Viaje sabado: "+viajeSabado, Toast.LENGTH_SHORT).show();
    }

    public void onClickContinuarInformacionViajes(View view) {
        if (txtDireccionActividadSecundaria.isEnabled()) {
            if (txtDireccionActividadSecundaria.getText().toString().equals("")) {
                Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
                lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
                sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
                laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
                direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
                zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

                if (ocupacion.equals("ESTUDIAR"))
                {
                    sectorTrabajo = "";
                    laborDesempeño = "";
                }
                else if (ocupacion.equals("TRABAJAR"))
                {
                    lugarEstudio = "";
                }


                DBAdapter db = new DBAdapter(this);
                db.open();
                long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
                db.close();

                Intent intent = new Intent(this, InformacionViajesActivity.class);
                intent.putExtra("idPersona", idPersona);
                intent.putExtra("zatVivienda", zatVivienda);
                intent.putExtra("nroEncuesta", numeroEncuesta);
                intent.putExtra("idHogar", idHogar);
                intent.putExtra("codigoOrden", cdOrden);
                intent.putExtra("numeroViaje", nViaje);
                intent.putExtra("ocupacion", ocupacionP);
                intent.putExtra("vehiculo", tipoVehiculo);
                intent.putExtra("sabado", viajeSabado);
                intent.putExtra("hayMotivoViaje", hayMotivoViaje);
                intent.putExtra("zatActividad", zatActividad);
                startActivity(intent);
                finish();
            }
        }
        else
        {
            ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
            lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
            sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
            laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
            direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
            zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

            if (ocupacion.equals("NINGUNA"))
            {
                lugarEstudio = "";
                sectorTrabajo = "";
                laborDesempeño = "";
                direccionActividadSecundaria = "";
                zatActividadSecundaria = "";
            }


            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
            db.close();

            Intent intent = new Intent(this,InformacionViajesActivity. class);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("zatVivienda", zatVivienda);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("codigoOrden", cdOrden);
            intent.putExtra("numeroViaje", nViaje);
            intent.putExtra("ocupacion", ocupacionP);
            intent.putExtra("vehiculo", tipoVehiculo);
            intent.putExtra("sabado", viajeSabado);
            intent.putExtra("hayMotivoViaje", hayMotivoViaje);
            intent.putExtra("zatActividad", zatActividad);
            startActivity(intent);
            finish();
        }
    }


    public void onClickContinuarAgregarPersona(View view){

        if (txtDireccionActividadSecundaria.isEnabled()) {
            if (txtDireccionActividadSecundaria.getText().toString().equals("")) {
                Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            } else
            {
                ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
                lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
                sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
                laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
                direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
                zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

                if (ocupacion.equals("ESTUDIAR"))
                {
                    sectorTrabajo = "";
                    laborDesempeño = "";
                }
                else if (ocupacion.equals("TRABAJAR"))
                {
                    lugarEstudio = "";

                }

                DBAdapter db = new DBAdapter(this);
                db.open();
                long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
                db.close();

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(true);
                dialog.setMessage("¿Qué desea hacer?")
                        .setTitle("Asegurese de que esta persona no realiza ningún viaje")
                        .setCancelable(true)
                        .setPositiveButton("Agregar viaje", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getBaseContext(), InformacionViajesActivity.class);
                                intent.putExtra("idPersona", idPersona);
                                intent.putExtra("zatVivienda", zatVivienda);
                                intent.putExtra("nroEncuesta", numeroEncuesta);
                                intent.putExtra("idHogar", idHogar);
                                intent.putExtra("codigoOrden", cdOrden);
                                intent.putExtra("numeroViaje", nViaje);
                                intent.putExtra("ocupacion", ocupacionP);
                                intent.putExtra("vehiculo", tipoVehiculo);
                                intent.putExtra("sabado", viajeSabado);
                                intent.putExtra("hayMotivoViaje", hayMotivoViaje);
                                intent.putExtra("zatActividad", zatActividad);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Agregar persona", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getBaseContext(), InformacionPersonaActivity.class);
                                intent.putExtra("zatVivienda", zatVivienda);
                                intent.putExtra("nroEncuesta", numeroEncuesta);
                                intent.putExtra("idHogar", idHogar);
                                intent.putExtra("codigoOrden", cdOrden);
                                intent.putExtra("vehiculo", tipoVehiculo);
                                intent.putExtra("sabado", viajeSabado);
                                //intent.putExtra("numeroViaje", nViaje);
                                startActivity(intent);
                                finish();
                            }
                        });
                dialog.show();


            }
        }
        else
        {
            ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
            lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
            sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
            laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
            direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
            zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

            lugarEstudio = "";
            sectorTrabajo = "";
            laborDesempeño = "";
            direccionActividadSecundaria = "";
            zatActividadSecundaria = "";

            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
            db.close();

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(true);
            dialog.setMessage("¿Qué desea hacer?")
                    .setTitle("Asegurese de que esta persona no realiza ningún viaje")
                    .setCancelable(true)
                    .setPositiveButton("Agregar viaje", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getBaseContext(), InformacionViajesActivity.class);
                            intent.putExtra("idPersona", idPersona);
                            intent.putExtra("zatVivienda", zatVivienda);
                            intent.putExtra("nroEncuesta", numeroEncuesta);
                            intent.putExtra("idHogar", idHogar);
                            intent.putExtra("codigoOrden", cdOrden);
                            intent.putExtra("numeroViaje", nViaje);
                            intent.putExtra("ocupacion", ocupacionP);
                            intent.putExtra("vehiculo", tipoVehiculo);
                            intent.putExtra("sabado", viajeSabado);
                            intent.putExtra("hayMotivoViaje", hayMotivoViaje);
                            intent.putExtra("zatActividad", zatActividad);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Agregar persona", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getBaseContext(), InformacionPersonaActivity.class);
                            intent.putExtra("zatVivienda", zatVivienda);
                            intent.putExtra("nroEncuesta", numeroEncuesta);
                            intent.putExtra("idHogar", idHogar);
                            intent.putExtra("codigoOrden", cdOrden);
                            intent.putExtra("vehiculo", tipoVehiculo);
                            intent.putExtra("sabado", viajeSabado);
                            startActivity(intent);
                            finish();
                        }
                    });
            dialog.show();

        }


    }

    public void onClickFinalizar(View view){
        if (txtDireccionActividadSecundaria.isEnabled()) {
            if (txtDireccionActividadSecundaria.getText().toString().equals("")) {
                Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            } else
            {
                ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
                lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
                sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
                laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
                direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
                zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

                if (ocupacion.equals("ESTUDIAR"))
                {
                    sectorTrabajo = "";
                    laborDesempeño = "";
                }
                else if (ocupacion.equals("TRABAJAR"))
                {
                    lugarEstudio = "";

                }

                DBAdapter db = new DBAdapter(this);
                db.open();
                long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
                db.close();


                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(true);
                dialog.setMessage("Asegurese de que esta persona no realiza ningún viaje no ser asi cierre este dialogo tocando cualquier parte de la " +
                        "pantalla para poder agregar un viaje. ¿Qué desea hacer?")
                        .setTitle("Gracias por hacer uso de la aplicación Encuesta Riosucio.")
                        .setCancelable(true)
                        .setPositiveButton("Comenzar una nueva encuesta", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Finalizar la aplicación", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                InformacionOcupacionSecundariaActivity.this.finish();
                            }
                        });
                dialog.show();


            }
        }
        else {
            ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
            lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
            sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
            laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
            //lugarTrabajo = spinnerLugarTrabajoSecundaria.getSelectedItem().toString();
            direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
            zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

            lugarEstudio = "";
            sectorTrabajo = "";
            laborDesempeño = "";
            direccionActividadSecundaria = "";
            zatActividadSecundaria = "";

            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
            db.close();

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(true);
            dialog.setMessage("¿Qué desea hacer?")
                    .setTitle("Gracias por hacer uso de la aplicación Encuesta Riosucio")
                    .setCancelable(true)
                    .setPositiveButton("Comenzar una nueva encuesta", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Finalizar la aplicación", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            InformacionOcupacionSecundariaActivity.this.finish();
                        }
                    });
            dialog.show();
        }




    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_ocupacion_secundaria, menu);
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
            //return true;
            //return true;
            DBAdapter db = new DBAdapter(getBaseContext());
            db.open();
            Cursor mCursor = db.getAllRestriccion();
            db.getAllRestriccion();
            if(mCursor.moveToFirst())
            {
                do{
                    Toast.makeText(this,
                            "id: " +mCursor.getString(0)+ "\n"+
                                    "Tabla: " +mCursor.getString(1)+ "\n"+
                                    "Descripción: "+mCursor.getString(2)+ "\n"+
                                    "Referencia persona: "+mCursor.getString(3)+"\n"+
                                    "Número encuesta: "+mCursor.getString(4), Toast.LENGTH_LONG).show();
                }while (mCursor.moveToNext());
            }
            db.close();
        }

        return super.onOptionsItemSelected(item);
    }
}
