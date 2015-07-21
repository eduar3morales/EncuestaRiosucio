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

    String idHogar; //Valor proveniente del Intent
    String idPersona;
    String zatVivienda;
    String numeroEncuesta;
    String cdOrden;
    String tipoVehiculo;
    String viajeSabado;


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

        for (int i = 0; i < 120; i++)
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

        idHogar = getIntent().getStringExtra("idHogar");
        Toast.makeText(this, "Id hogar: "+idHogar, Toast.LENGTH_SHORT).show();
        zatVivienda = getIntent().getStringExtra("zatVivienda");
        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");
        cdOrden = getIntent().getStringExtra("codigoOrden");

        Toast.makeText(this, "Codigo Orden: "+cdOrden, Toast.LENGTH_SHORT).show();
        spinnerCodigoDeOrden.setSelection(Integer.parseInt(cdOrden));

        tipoVehiculo = getIntent().getStringExtra("vehiculo");

        viajeSabado = getIntent().getStringExtra("sabado");
        Toast.makeText(this, "Viaje sabado: "+viajeSabado, Toast.LENGTH_SHORT).show();



    }

    public void onClickContinuarOcupacionPrincipal(View view) {

        if (txtNombre.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            codigoOrden = spinnerCodigoDeOrden.getSelectedItem().toString();
            nombre = txtNombre.getText().toString();
            genero = spinnerGenero.getSelectedItem().toString();
            edad = spinnerEdad.getSelectedItem().toString();
            usoRedCicloRuta = spinnerUsoCicloruta.getSelectedItem().toString();
            ultimoNivelEstudio = spinnerNivelDeEstudios.getSelectedItem().toString();

            DBAdapter db = new DBAdapter(this);
            db.open();
            long id = db.insertPersona(codigoOrden, nombre, edad, genero, ultimoNivelEstudio, usoRedCicloRuta, idHogar);
            db.close();

            db.open();
            Cursor c = db.getAllPersonas();
            if(c.moveToFirst())
            {
                do{
                    idPersona = c.getString(0);
                }while (c.moveToNext());
            }
            db.close();

            Intent intent = new Intent(this,InformacionOcupacionPrincipalActivity. class);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("edad", edad);
            intent.putExtra("zatVivienda", zatVivienda);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("codigoOrden", cdOrden);
            intent.putExtra("vehiculo", tipoVehiculo);
            intent.putExtra("sabado", viajeSabado);
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


    ///Cuando se vuelve a esta vista, aparece un null
}
