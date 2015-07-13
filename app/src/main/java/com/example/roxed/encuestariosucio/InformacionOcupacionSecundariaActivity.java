package com.example.roxed.encuestariosucio;

import android.content.Intent;
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
    Spinner spinnerLugarTrabajoSecundaria;
    Spinner spinnerZatActividadSecundaria;
    EditText txtDireccionActividadSecundaria;

    String ocupacion;
    String lugarEstudio;
    String sectorTrabajo;
    String laborDesempeño;
    String lugarTrabajo;
    String direccionActividadSecundaria;
    String zatActividadSecundaria;

    String idPersona;
    //String numeroViaje = "0";
    int numeroViaje = 0;
    static final String TIPO_OCUPACION = "OCUPACIÓN SECUNARIA";


    private List<String> listaOcupacionSecundaria= new ArrayList<String>();
    private List<String> listaLugarEstudioSecundaria= new ArrayList<String>();
    private List<String> listaSectorTrabajoSecundaria= new ArrayList<String>();
    private List<String> listaLaborDesempeñaSecundaria= new ArrayList<String>();
    private List<String> listaLugarTrabajoSecundaria = new ArrayList<String>();
    private List<String> listaZatActividadSecundaria = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_ocupacion_secundaria);

        spinnerOcupacionSecundaria= (Spinner) findViewById(R.id.spinnerOcupacionSecundaria);
        spinnerLugarEstudioSecundaria= (Spinner) findViewById(R.id.spinnerLugarEstudioSecundaria);
        spinnerSectorTrabajoSecundaria= (Spinner) findViewById(R.id.spinnerSectorTrabajoSecundaria);
        spinnerLaborDesempeñaSecundaria= (Spinner) findViewById(R.id.spinnerLaborDesempeñaSecundaria);
        spinnerLugarTrabajoSecundaria=(Spinner) findViewById(R.id.spinnerLugarTrabajoSecundaria);
        spinnerZatActividadSecundaria=(Spinner) findViewById(R.id.spinnerZATActividadSecundaria);
        txtDireccionActividadSecundaria = (EditText) findViewById (R.id.txtDireccionActividadSecundaria);

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

        listaLugarTrabajoSecundaria.add("CASA");
        listaLugarTrabajoSecundaria.add("OFICINA O ESTACIONAMIENTO");
        listaLugarTrabajoSecundaria.add("CASA Y OFICINA");
        listaLugarTrabajoSecundaria.add("EN LA CALLE");
        listaLugarTrabajoSecundaria.add("AGENTE VIAJERO");
        listaLugarTrabajoSecundaria.add("OTRO");
        ArrayAdapter<String> adaptadorLugarTrabajoSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaLugarTrabajoSecundaria);
        adaptadorLugarTrabajoSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLugarTrabajoSecundaria.setAdapter(adaptadorLugarTrabajoSecundaria);
        for (int i = -1; i< 17; i++)
            listaZatActividadSecundaria.add(""+(i+1));

        ArrayAdapter<String> adaptadorZatActividadSecundaria = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatActividadSecundaria);
        adaptadorZatActividadSecundaria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZatActividadSecundaria.setAdapter(adaptadorZatActividadSecundaria);

        idPersona = getIntent().getStringExtra("idPersona");
        //Toast.makeText(this, idPersona, Toast.LENGTH_SHORT).show();


        spinnerOcupacionSecundaria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerOcupacionSecundaria.getSelectedItem().toString().equals("ESTUDIAR"))
                {
                    spinnerLugarEstudioSecundaria.setEnabled(true);
                    spinnerSectorTrabajoSecundaria.setEnabled(false);
                    spinnerLaborDesempeñaSecundaria.setEnabled(false);
                    spinnerLugarTrabajoSecundaria.setEnabled(false);
                    txtDireccionActividadSecundaria.setEnabled(true);
                    spinnerZatActividadSecundaria.setEnabled(true);


                }
                else if (spinnerOcupacionSecundaria.getSelectedItem().toString().equals("TRABAJAR"))
                {
                    spinnerLugarEstudioSecundaria.setEnabled(false);
                    spinnerSectorTrabajoSecundaria.setEnabled(true);
                    spinnerLaborDesempeñaSecundaria.setEnabled(true);
                    spinnerLugarTrabajoSecundaria.setEnabled(true);
                    txtDireccionActividadSecundaria.setEnabled(true);
                    spinnerZatActividadSecundaria.setEnabled(true);
                }
                else
                {
                    spinnerLugarEstudioSecundaria.setEnabled(false);
                    spinnerSectorTrabajoSecundaria.setEnabled(false);
                    spinnerLaborDesempeñaSecundaria.setEnabled(false);
                    spinnerLugarTrabajoSecundaria.setEnabled(false);
                    txtDireccionActividadSecundaria.setEnabled(false);
                    spinnerZatActividadSecundaria.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onClickContinuarInformacionViajes(View view) {
        ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
        lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
        sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
        laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
        lugarTrabajo = spinnerLugarTrabajoSecundaria.getSelectedItem().toString();
        direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
        zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, lugarTrabajo,direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
        db.close();

        Intent intent = new Intent(this,InformacionViajesActivity. class);
        intent.putExtra("idPersona", idPersona);
        intent.putExtra("numeroViaje", numeroViaje);
        startActivity(intent);
    }

    public void onClickContinuarAgregarPersona(View view){
        ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
        lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
        sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
        laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
        lugarTrabajo = spinnerLugarTrabajoSecundaria.getSelectedItem().toString();
        direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
        zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, lugarTrabajo,direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
        db.close();

        Intent intent = new Intent(this,InformacionPersonaActivity. class);
        startActivity(intent);

    }

    public void onClickFinalizar(View view){
        ocupacion = spinnerOcupacionSecundaria.getSelectedItem().toString();
        lugarEstudio = spinnerLugarEstudioSecundaria.getSelectedItem().toString();
        sectorTrabajo = spinnerSectorTrabajoSecundaria.getSelectedItem().toString();
        laborDesempeño = spinnerLaborDesempeñaSecundaria.getSelectedItem().toString();
        lugarTrabajo = spinnerLugarTrabajoSecundaria.getSelectedItem().toString();
        direccionActividadSecundaria = txtDireccionActividadSecundaria.getText().toString();
        zatActividadSecundaria = spinnerZatActividadSecundaria.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertOcupacion(ocupacion, lugarEstudio, sectorTrabajo, laborDesempeño, lugarTrabajo,direccionActividadSecundaria, zatActividadSecundaria, TIPO_OCUPACION, idPersona);
        db.close();

        Intent intent = new Intent(this,MainActivity. class);
        startActivity(intent);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
