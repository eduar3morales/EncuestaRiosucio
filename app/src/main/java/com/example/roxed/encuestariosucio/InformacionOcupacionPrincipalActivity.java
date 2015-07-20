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

    String ocupacion;
    String lugarEstudio;
    String sectorTrabajo;
    String laborDesempeño;

    String direccionActividadPrincipal;
    String zatActividadPrincipal;

    String idPersona; //Valor proveniente del Intent
    String edad; //Valor proveniente del Intent
    String zatVivienda;
    String numeroEncuesta;
    String idHogar;
    String cdOrden;
    static final String TIPO_OCUPACION = "Principal";

    private List<String> listaOcupacionPrincipal= new ArrayList<String>();
    private List<String> listaLugarEstudioPrincipal= new ArrayList<String>();
    private List<String> listaSectorTrabajoPrincipal= new ArrayList<String>();
    private List<String> listaLaborDesempeñaPrincipal= new ArrayList<String>();
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


        idPersona = getIntent().getStringExtra("idPersona");
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
        Toast.makeText(this, "Id Persona: "+idPersona, Toast.LENGTH_SHORT).show();

        edad = getIntent().getStringExtra("edad");
        Toast.makeText(this, "Edad: "+edad, Toast.LENGTH_SHORT).show();

        zatVivienda = getIntent().getStringExtra("zatVivienda");

        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");

        idHogar = getIntent().getStringExtra("idHogar");

        cdOrden = getIntent().getStringExtra("codigoOrden");
        cdOrden = ""+(Integer.parseInt(cdOrden) + 1);

        Toast.makeText(this, "Codigo orden: "+cdOrden, Toast.LENGTH_SHORT).show();





    }

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
                    sectorTrabajo = "NA";
                    laborDesempeño = "NA";
                }
                else if (ocupacion.equals("TRABAJAR"))
                {
                    lugarEstudio = "NA";
                }
                else
                {
                    lugarEstudio = "NA";
                    sectorTrabajo = "NA";
                    laborDesempeño = "NA";
                    direccionActividadPrincipal = "NA";
                    zatActividadPrincipal = "NA";
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

            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, direccionActividadPrincipal, zatActividadPrincipal, TIPO_OCUPACION, idPersona);
            db.close();

            Intent intent = new Intent(this,InformacionDiscapacidadActivity. class);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("codigoOrden", cdOrden);
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
