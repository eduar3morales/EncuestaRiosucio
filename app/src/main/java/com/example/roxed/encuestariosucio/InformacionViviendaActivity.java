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


public class InformacionViviendaActivity extends ActionBarActivity {

    Spinner spinnerZat;
    Spinner spinnerTipoVivienda;
    Spinner spinnerEstrato;
    EditText txtBarrio;
    EditText txtDireccion;
    EditText txtTelefono;
    EditText txtCelular;
    Spinner spinnerCantidadHogaresVivienda;
    private List<String> listaZatVivienda = new ArrayList<String>();
    private List<String> listaTipoVivienda = new ArrayList<String>();
    private List<String> listaEstrato = new ArrayList<String>();
    private List<String> listaCantidadHogaresVivienda = new ArrayList<String>();

    String barrio;
    String estrato;
    String direccion;
    String zat;
    String telefono;
    String celular;
    String tipoVivienda;
    String cantidadHogaresVivienda;

    String numeroEncuesta; //Valor pasado a través del Intent
    String idVivienda;

    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_vivienda);


        spinnerZat = (Spinner) findViewById(R.id.spinnerZATvivienda);
        spinnerTipoVivienda = (Spinner) findViewById(R.id.spinnerTipoVivienda);
        spinnerEstrato = (Spinner) findViewById(R.id.spinnerEstrato);
        txtBarrio = (EditText) findViewById(R.id.txtBarrio);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        spinnerCantidadHogaresVivienda = (Spinner) findViewById(R.id.spinnerCantidadHogaresVivienda);


        for (int i = -1; i< 17; i++)
            listaZatVivienda.add(String.valueOf((i+1)));

        ArrayAdapter<String> adaptadorZat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatVivienda);
        adaptadorZat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZat.setAdapter(adaptadorZat);

        listaTipoVivienda.add("CASA");
        listaTipoVivienda.add("APARTAMENTO");
        listaTipoVivienda.add("OTRO");
        ArrayAdapter<String> adaptadorTipoVivienda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoVivienda);
        adaptadorTipoVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoVivienda.setAdapter(adaptadorTipoVivienda);


        listaEstrato.add("1");
        listaEstrato.add("2");
        listaEstrato.add("3");
        ArrayAdapter<String> adaptadorEstrato = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaEstrato);
        adaptadorEstrato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstrato.setAdapter(adaptadorEstrato);

        for(int i=1;i<=40;i++)
            listaCantidadHogaresVivienda.add(""+(i));

        ArrayAdapter<String> adaptadorCantidadHogaresVivienda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCantidadHogaresVivienda);
        adaptadorCantidadHogaresVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadHogaresVivienda.setAdapter(adaptadorCantidadHogaresVivienda);


        numeroEncuesta = getIntent().getStringExtra("numeroEncuesta");
        Toast.makeText(this, "Numero encuesta: "+numeroEncuesta, Toast.LENGTH_SHORT).show();

        /*DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllEncuestas();
        if (c.moveToFirst())
        {
            do{
                Toast.makeText(this,
                        "id: " +c.getString(0)+ "\n"+
                "Coordinador: " +c.getString(1)+ "\n"+
                "Encuestador: "+c.getString(2)+ "\n"+
                "Hora inicio: "+c.getString(3)+"\n"+
                "Fecha: "+c.getString(4), Toast.LENGTH_LONG).show();
            }while (c.moveToNext());
        }
        db.close();*/


    }

    public void onClickContinuarVivienda(View view)
    {
        if ((txtCelular.getText().toString().equals("") && txtTelefono.getText().toString().equals("")) || txtBarrio.getText().toString().equals("")
                || txtDireccion.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            barrio = txtBarrio.getText().toString();
            estrato = spinnerEstrato.getSelectedItem().toString();
            direccion = txtDireccion.getText().toString();
            zat = spinnerZat.getSelectedItem().toString();
            telefono = txtTelefono.getText().toString();
            celular = txtCelular.getText().toString();
            tipoVivienda = spinnerTipoVivienda.getSelectedItem().toString();
            cantidadHogaresVivienda = spinnerCantidadHogaresVivienda.getSelectedItem().toString();

            DBAdapter db = new DBAdapter(this);
            db.open();
            id = db.insertVivienda(barrio, estrato, direccion, zat, telefono, celular,tipoVivienda, cantidadHogaresVivienda);
            db.close();

            db.open();
            Cursor c = db.getAllViviendas();
            if (c.moveToFirst())
            {
                do{
                    idVivienda = c.getString(0);
                /*Toast.makeText(this,
                        "id: " +c.getString(0)+ "\n"+
                                "Barrio: " +c.getString(1)+ "\n"+
                                "Estrato: "+c.getString(2)+ "\n"+
                                "Dirección: "+c.getString(3)+"\n"+
                                "Zat: "+c.getString(4)+"\n"+
                                "Telefono: "+c.getString(5)+"\n"+
                                "Celular: "+c.getString(6)+"\n"+
                                "Tipo Vivienda: "+"\n"+
                                "Cantidad Hogares: ", Toast.LENGTH_LONG).show();*/
                }while (c.moveToNext());
            }
            db.close();


            Intent intent = new Intent(this, InformacionHogarActivity.class);
            intent.putExtra("numeroEncuesta", numeroEncuesta);
            intent.putExtra("idVivienda", idVivienda);
            intent.putExtra("zatVivienda", zat);
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
        getMenuInflater().inflate(R.menu.menu_informacion_vivienda, menu);
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
