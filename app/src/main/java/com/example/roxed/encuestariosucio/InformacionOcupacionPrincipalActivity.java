package com.example.roxed.encuestariosucio;

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


public class InformacionOcupacionPrincipalActivity extends ActionBarActivity {


    Spinner spinnerOcupacionPrincipal;
    Spinner spinnerLugarEstudioPrincipal;
    Spinner spinnerSectorTrabajoPrincipal;
    Spinner spinnerLaborDesempeñaPrincipal;
    Spinner spinnerZatActividadPrincipal;
    EditText txtDireccionActividadPrincipal;

    /**
     * Variable en la que se guarda la Ocupación principal
     */
    String ocupacion;

    /**
     * Variable donde se guarda el lugar de estudio
     */
    String lugarEstudio;

    /**
     * Variable donde se guarda el sector de trabajo
     */
    String sectorTrabajo;

    /**
     * Variable donde se guarda la labor de desempeño
     */
    String laborDesempeño;

    /**
     * Variable donde se guarda la Dirección de la actividad principal
     */
    String direccionActividadPrincipal;

    /**
     * Variable donde se guarda la ZAT de la actividad principal
     */
    String zatActividadPrincipal;

    /**
     * Variable que contiene el id de persona proveniente de la actividad persona
     */
    String idPersona; //Valor proveniente del Intent

    /**
     * Variable que contiene la edad proveniente de la actividad de persona
     */
    String edad; //Valor proveniente del Intent

    /**
     * Variable que contiene la Zat de la vivienda proveniente de la actividad vivienda
     */
    String zatVivienda;

    /**
     * Variable que contiene el número de encuesta proveniente de la actividad principal
     */
    String numeroEncuesta;

    /**
     * Variable que contiene el id del hogar proveniente de la actividad hogar
     */
    String idHogar;

    /**
     * Variable que contiene el código de orden de una persona proveniente de la actividad persona
     */
    String cdOrden;

    /**
     * Variable que contiene el tipo de vehículo proveniente de la actividad medios de transporte
     */
    String tipoVehiculo;


    String viajeSabado;
    static final String TIPO_OCUPACION = "Principal";

    /**
     * Lista donde se almacenan las opciones de Ocupación principal que se muestran en el Spinner ocupaciín
     */
    private List<String> listaOcupacionPrincipal= new ArrayList<String>();

    /**
     * Lista donde se almacenan las opciones de Lugar de Estudio que se muestran en el Spinner lugar de estudio
     */
    private List<String> listaLugarEstudioPrincipal= new ArrayList<String>();

    /**
     * Lista donde se almacenan las opciones de Sector de trabajo que se muestran en el Spinner sector de trabajo
     */
    private List<String> listaSectorTrabajoPrincipal= new ArrayList<String>();

    /**
     * Lista donde se almacenan las opciones de labor de desempeño que se muestran en el Spinner labor que se desempeña
     */
    private List<String> listaLaborDesempeñaPrincipal= new ArrayList<String>();

    /**
     * Lista donde se almacenan opciones de ZAT disponibles que se muestran en el Spinner Zat de la actividad secundaria
     */
    private List<String> listaZatActividadPrincipal = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_ocupacion_principal);

        spinnerOcupacionPrincipal= (Spinner) findViewById(R.id.spinnerOcupacionPrincipal);
        spinnerLugarEstudioPrincipal= (Spinner) findViewById(R.id.spinnerLugarEstudioPrincipal);
        spinnerSectorTrabajoPrincipal= (Spinner) findViewById(R.id.spinnerSectorTrabajoPrincipal);
        spinnerLaborDesempeñaPrincipal= (Spinner) findViewById(R.id.spinnerLaborDesempeñaPrincipal);
        //spinnerLugarTrabajoPrincipal=(Spinner) findViewById(R.id.spinnerLugarTrabajoPrincipal);
        spinnerZatActividadPrincipal = (Spinner) findViewById(R.id.spinnerZATActividadPrincipal);
        txtDireccionActividadPrincipal = (EditText) findViewById(R.id.txtDireccionActividadPrincipal);


        listaOcupacionPrincipal.add("ESTUDIAR");
        listaOcupacionPrincipal.add("TRABAJAR");
        listaOcupacionPrincipal.add("OFICIOS DEL HOGAR");
        listaOcupacionPrincipal.add("JUBILADO");
        listaOcupacionPrincipal.add("RENTISTA");
        listaOcupacionPrincipal.add("BUSCAR TRABAJO");
        listaOcupacionPrincipal.add("INCAPACIDAD PERMANENTE PARA TRABAJAR");
        listaOcupacionPrincipal.add("OTRA ACTIVIDAD");


        ArrayAdapter<String> adaptadorOcupacionPrincipal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaOcupacionPrincipal);
        adaptadorOcupacionPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOcupacionPrincipal.setAdapter(adaptadorOcupacionPrincipal);

        listaLugarEstudioPrincipal.add("COLEGIO - ESCUELA");
        listaLugarEstudioPrincipal.add("UNIVERSIDAD");
        listaLugarEstudioPrincipal.add("INSTITUTO TECNICO O TECNOLOGICO");
        listaLugarEstudioPrincipal.add("INSTITUTO DE EDUCACION NO FOMRAL");
        listaLugarEstudioPrincipal.add("OTRO");

        ArrayAdapter<String> adaptadorEstudioPrincipal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLugarEstudioPrincipal);
        adaptadorEstudioPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLugarEstudioPrincipal.setAdapter(adaptadorEstudioPrincipal);

        listaSectorTrabajoPrincipal.add("AGRICULTURA");
        listaSectorTrabajoPrincipal.add("MANTENIMIENTO Y REPARACION");
        listaSectorTrabajoPrincipal.add("MINERIA");
        listaSectorTrabajoPrincipal.add("MANUFACTURA");
        listaSectorTrabajoPrincipal.add("CONSTRUCCION");
        listaSectorTrabajoPrincipal.add("EDUCACION");
        listaSectorTrabajoPrincipal.add("FINANCIERO");
        listaSectorTrabajoPrincipal.add("SALUD");
        listaSectorTrabajoPrincipal.add("TRANSPORTE");
        listaSectorTrabajoPrincipal.add("HOTELES Y RESTAURANTES");
        listaSectorTrabajoPrincipal.add("COMERCIO");
        listaSectorTrabajoPrincipal.add("GOBIERNO");
        listaSectorTrabajoPrincipal.add("OTROS SERVICIOS");
        ArrayAdapter<String> adaptadorSectorTrabajoPrincipal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSectorTrabajoPrincipal);
        adaptadorSectorTrabajoPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSectorTrabajoPrincipal.setAdapter(adaptadorSectorTrabajoPrincipal);

        listaLaborDesempeñaPrincipal.add("OBRERO O EMPLEADO");
        listaLaborDesempeñaPrincipal.add("EMPLEADO DOMESTICO");
        listaLaborDesempeñaPrincipal.add("TRABAJADOR INDEPENDIENTE O CUENTA PROPIA");
        listaLaborDesempeñaPrincipal.add("PATRON O EMPLEADOR");
        listaLaborDesempeñaPrincipal.add("TRABAJADOR FAMILAIR SIN REMUNERACION");
        listaLaborDesempeñaPrincipal.add("OTRO");
        ArrayAdapter<String> adaptadorLaborDesempeñaPrincipal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLaborDesempeñaPrincipal);
        adaptadorLaborDesempeñaPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLaborDesempeñaPrincipal.setAdapter(adaptadorLaborDesempeñaPrincipal);


        for (int i = -1; i< 17; i++)
            listaZatActividadPrincipal.add(String.valueOf((i+1)));

        ArrayAdapter<String> adaptadorZatActividadPrincipal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatActividadPrincipal);
        adaptadorZatActividadPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZatActividadPrincipal.setAdapter(adaptadorZatActividadPrincipal);


        //idPersona = getIntent().getStringExtra("idPersona");
        //Toast.makeText(this, idPersona, Toast.LENGTH_SHORT).show();


        spinnerOcupacionPrincipal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //adapterView.getItemAtPosition(i).toString();
                if (spinnerOcupacionPrincipal.getSelectedItem().toString().equals("ESTUDIAR"))
                {
                    spinnerLugarEstudioPrincipal.setEnabled(true);
                    spinnerSectorTrabajoPrincipal.setEnabled(false);
                    spinnerLaborDesempeñaPrincipal.setEnabled(false);
                    txtDireccionActividadPrincipal.setEnabled(true);
                    spinnerZatActividadPrincipal.setEnabled(true);


                }
                else if (spinnerOcupacionPrincipal.getSelectedItem().toString().equals("TRABAJAR"))
                {
                    spinnerLugarEstudioPrincipal.setEnabled(false);
                    spinnerSectorTrabajoPrincipal.setEnabled(true);
                    spinnerLaborDesempeñaPrincipal.setEnabled(true);
                    txtDireccionActividadPrincipal.setEnabled(true);
                    spinnerZatActividadPrincipal.setEnabled(true);
                }
                else
                {
                    spinnerLugarEstudioPrincipal.setEnabled(false);
                    spinnerSectorTrabajoPrincipal.setEnabled(false);
                    spinnerLaborDesempeñaPrincipal.setEnabled(false);
                    txtDireccionActividadPrincipal.setEnabled(false);
                    spinnerZatActividadPrincipal.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        idPersona = getIntent().getStringExtra("idPersona");

        edad = getIntent().getStringExtra("edad");

        zatVivienda = getIntent().getStringExtra("zatVivienda");

        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");

        idHogar = getIntent().getStringExtra("idHogar");

        cdOrden = getIntent().getStringExtra("codigoOrden");
        cdOrden = ""+(Integer.parseInt(cdOrden) + 1);

        tipoVehiculo = getIntent().getStringExtra("vehiculo");

        viajeSabado = getIntent().getStringExtra("sabado");
        //Toast.makeText(this, "Id Persona: "+idPersona, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Edad: "+edad, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Codigo orden: "+cdOrden, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Viaje sabado: "+viajeSabado, Toast.LENGTH_SHORT).show();

    }


    /**
     * Función que permite hacer el registro de los datos relacionados a la Ocupación Principal y lleva a
     * la siguiente actividad para hacer el registro de los datos de discapacidad
     */
    public void onClickContinuarInformacionDiscapacidad(View view) {
        if (txtDireccionActividadPrincipal.isEnabled())
        {
            if (txtDireccionActividadPrincipal.getText().toString().equals(""))
            {
                Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ocupacion = spinnerOcupacionPrincipal.getSelectedItem().toString();
                lugarEstudio = spinnerLugarEstudioPrincipal.getSelectedItem().toString();
                sectorTrabajo = spinnerSectorTrabajoPrincipal.getSelectedItem().toString();
                laborDesempeño = spinnerLaborDesempeñaPrincipal.getSelectedItem().toString();

                direccionActividadPrincipal = txtDireccionActividadPrincipal.getText().toString();
                zatActividadPrincipal = spinnerZatActividadPrincipal.getSelectedItem().toString();

                if (ocupacion.equals("ESTUDIAR"))
                {
                    sectorTrabajo = "";
                    laborDesempeño = "";
                }
                else if (ocupacion.equals("TRABAJAR"))
                {
                    lugarEstudio = "";
                }
                else
                {
                    lugarEstudio = "";
                    sectorTrabajo = "";
                    laborDesempeño = "";
                    direccionActividadPrincipal = "";
                    zatActividadPrincipal = "";
                }

                DBAdapter db = new DBAdapter(this);
                db.open();
                long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadPrincipal, zatActividadPrincipal, TIPO_OCUPACION, idPersona);
                db.close();

                //--- VALIDACIÓN DATOS SOPECHOSOS ---
                if (ocupacion.equals("TRABAJAR") && (Integer.parseInt(edad) <= 16))
                {
                    //Registro dato sospechoso
                    db.open();
                    id = db.insertRestriccion("OCUPACIÓN PRINCIPAL", "Menor de 16 años Trabajando", idPersona, numeroEncuesta);
                    db.close();
                }
                else if (ocupacion.equals("ESTUDIAR") && (Integer.parseInt(edad) >= 30))
                {
                    //Registro dato sospechoso
                    db.open();
                    id = db.insertRestriccion("OCUPACION PRINCIPAL", "Persona mayor de 30 años Estudiando", idPersona, numeroEncuesta);
                    db.close();
                }

                Intent intent = new Intent(this,InformacionDiscapacidadActivity. class);
                intent.putExtra("idPersona", idPersona);
                intent.putExtra("zatVivienda", zatVivienda);
                intent.putExtra("nroEncuesta", numeroEncuesta);
                intent.putExtra("idHogar", idHogar);
                intent.putExtra("codigoOrden", cdOrden);
                intent.putExtra("ocupacion", ocupacion);
                intent.putExtra("vehiculo", tipoVehiculo);
                intent.putExtra("sabado", viajeSabado);
                intent.putExtra("zatActividad", zatActividadPrincipal);

                startActivity(intent);

                finish();
            }
        }
        else
        {
            ocupacion = spinnerOcupacionPrincipal.getSelectedItem().toString(); // --- VERIFICAR QUE LAS VISTAS DESACTIVADAS NO GENEREN UN ERROR CON VALORES NULOS ---
            lugarEstudio = spinnerLugarEstudioPrincipal.getSelectedItem().toString();
            sectorTrabajo = spinnerSectorTrabajoPrincipal.getSelectedItem().toString();
            laborDesempeño = spinnerLaborDesempeñaPrincipal.getSelectedItem().toString();
            //lugarTrabajo = spinnerLugarTrabajoPrincipal.getSelectedItem().toString();
            direccionActividadPrincipal = txtDireccionActividadPrincipal.getText().toString();
            zatActividadPrincipal = spinnerZatActividadPrincipal.getSelectedItem().toString();

            lugarEstudio = "";
            sectorTrabajo = "";
            laborDesempeño = "";
            direccionActividadPrincipal = "";
            zatActividadPrincipal = "";

            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadPrincipal, zatActividadPrincipal, TIPO_OCUPACION, idPersona);
            db.close();

            Intent intent = new Intent(this,InformacionDiscapacidadActivity. class);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("zatVivienda", zatVivienda);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("codigoOrden", cdOrden);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("ocupacion", ocupacion);
            intent.putExtra("vehiculo", tipoVehiculo);
            intent.putExtra("sabado", viajeSabado);
            intent.putExtra("zatActividad", zatActividadPrincipal);
            startActivity(intent);

            finish();
        }

    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_ocupacion_principal, menu);
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
