package com.example.roxed.encuestariosucio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
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


public class InformacionHogarActivity extends ActionBarActivity {

    Spinner spinnerTipoPropiedad;
    Spinner spinnerRangoIngresosMensuales;
    Spinner spinnerCantidadPersonasHogar;
    Spinner spinnerCantidadPersonasViajanDiaTipico;
    Spinner spinnerCantidadPersonasViajanSabado;
    Spinner spinnerCantidadPersonasPresentes;
    Spinner spinnerTieneVehiculo;
    Spinner spinnerCantidadVehiculos;

    private List<String> listaTipoPropiedad = new ArrayList<String>();
    private List<String> listaRangoIngresosMensuales = new ArrayList<String>();
    private List<String> listaCantidadPersonasHogar = new ArrayList<String>();
    private List<String> listaCantidadPersonasViajanDiaTipico = new ArrayList<String>();
    private List<String> listaCantidadPersonasPresentes = new ArrayList<String>();
    private List<String> listaCantidadPersonasViajanSabado = new ArrayList<String>();
    private List<String> listaTieneVehiculo = new ArrayList<String>();
    private List<String> listaCantidadVehiculos = new ArrayList<String>();

    String cantidadPersonasConformanHogar;
    String cantidadPersonasViajanDiaTipico;
    String cantidadPersonasViajanDiaSabado;
    String cantidadPersonasPresentes;
    String tipoPropiedad;
    String rangoIngresos;

    String numeroEncuesta;
    String idVivienda;
    String idHogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_hogar);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("De antemano muchas gracias por indicarnos de los siguientes rangos, en cual de éstos se podría ubicar los ingresos " +
                "mensuales de este hogar (Tener en cuenta arrendamientos, pensiones, salarios, y otro tipo de ingresos que se generen normalmente. " +
                "Toda esta información se usará para el estudio y es condidencial)")
                .setTitle("Por favor leer este mensaje a las personas encuestadas")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
        dialog.show();


        spinnerTipoPropiedad = (Spinner) findViewById(R.id.spinnerTipoPropiedad);
        spinnerRangoIngresosMensuales = (Spinner) findViewById(R.id.spinnerRangoIngresosMensuales);
        spinnerCantidadPersonasHogar = (Spinner) findViewById(R.id.spinnerCantidadPersonasConformanHogar);
        spinnerCantidadPersonasViajanDiaTipico = (Spinner) findViewById(R.id.spinnerCantidadPersonasViajanDiaTipico);
        spinnerCantidadPersonasViajanSabado = (Spinner) findViewById(R.id.spinnerCantidadPersonasViajanSabado);
        spinnerCantidadPersonasPresentes = (Spinner) findViewById(R.id.spinnerCantidadPersonasPresentes);

        listaTipoPropiedad.add("PROPIA");
        listaTipoPropiedad.add("ALQUILADA POR USTED");
        listaTipoPropiedad.add("OTRO");
        ArrayAdapter<String> adaptadorTipoPropiedad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTipoPropiedad);
        adaptadorTipoPropiedad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPropiedad.setAdapter(adaptadorTipoPropiedad);

        listaRangoIngresosMensuales.add("$0 - $644.350");
        listaRangoIngresosMensuales.add("$644.351 - $1.300.000");
        listaRangoIngresosMensuales.add("$1.300.001 - $2.300.000");
        listaRangoIngresosMensuales.add("$2.300.001 - $3.200.000");
        listaRangoIngresosMensuales.add("$3.200.001 - $4.500.000");
        listaRangoIngresosMensuales.add("$4.500.001 - $6.300.000");
        listaRangoIngresosMensuales.add("$6.300.001 - $9.000.000");
        listaRangoIngresosMensuales.add("MAYOR A - $9.000.000");
        ArrayAdapter<String> adaptadorRangoIngresosMensuales = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaRangoIngresosMensuales);
        adaptadorRangoIngresosMensuales.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRangoIngresosMensuales.setAdapter(adaptadorRangoIngresosMensuales);

        for(int i=0;i<=40;i++)
            listaCantidadPersonasViajanDiaTipico.add(""+(i));

        ArrayAdapter<String> adaptadorCantidadPersonasViajanDiaTipico = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCantidadPersonasViajanDiaTipico);
        adaptadorCantidadPersonasViajanDiaTipico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadPersonasViajanDiaTipico.setAdapter(adaptadorCantidadPersonasViajanDiaTipico);

        for(int i=0;i<=40;i++)
            listaCantidadPersonasHogar.add(""+(i));

        ArrayAdapter<String> adaptadorCantidadPersonasHogar= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCantidadPersonasHogar);
        adaptadorCantidadPersonasHogar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadPersonasHogar.setAdapter(adaptadorCantidadPersonasHogar);

        for(int i=0;i<=40;i++)
            listaCantidadPersonasViajanSabado.add(""+(i));

        ArrayAdapter<String> adaptadorCantidadPersonasViajanSabado= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCantidadPersonasViajanSabado);
        adaptadorCantidadPersonasViajanSabado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadPersonasViajanSabado.setAdapter(adaptadorCantidadPersonasViajanSabado);

        for(int i=0;i<=40;i++)
            listaCantidadPersonasPresentes.add(""+(i));

        ArrayAdapter<String> adaptadorCantidadPersonasPresentes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCantidadPersonasPresentes);
        adaptadorCantidadPersonasPresentes .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadPersonasPresentes.setAdapter(adaptadorCantidadPersonasPresentes);


        numeroEncuesta = getIntent().getStringExtra("numeroEncuesta");
        idVivienda = getIntent().getStringExtra("idVivienda");
        Toast.makeText(this, "Nro Encuesta: "+numeroEncuesta+"/"+"Id Vivienda: "+idVivienda, Toast.LENGTH_SHORT).show();

    }

    public void onClickContinuarHogar(View view)
    {
        cantidadPersonasConformanHogar = spinnerCantidadPersonasHogar.getSelectedItem().toString();
        cantidadPersonasViajanDiaTipico = spinnerCantidadPersonasViajanDiaTipico.getSelectedItem().toString();
        cantidadPersonasViajanDiaSabado = spinnerCantidadPersonasViajanSabado.getSelectedItem().toString();
        cantidadPersonasPresentes = spinnerCantidadPersonasPresentes.getSelectedItem().toString();
        tipoPropiedad = spinnerTipoPropiedad.getSelectedItem().toString();
        rangoIngresos = spinnerRangoIngresosMensuales.getSelectedItem().toString();

        DBAdapter db = new DBAdapter(this);
        db.open();
        long id = db.insertHogar(cantidadPersonasConformanHogar, cantidadPersonasViajanDiaTipico, cantidadPersonasViajanDiaSabado, cantidadPersonasPresentes,
                tipoPropiedad, rangoIngresos, numeroEncuesta, idVivienda);

        Cursor c = db.getAllHogares();
        if (c.moveToFirst())
        {
            do{
                idHogar = c.getString(0);
                /*Toast.makeText(this,
                        "Id: " +c.getString(0)+ "\n"+
                                "Personas: " +c.getString(1)+ "\n"+
                                "Tipico: "+c.getString(2)+ "\n"+
                                "Sabado: "+c.getString(3)+"\n"+
                                "Presentes: "+c.getString(4)+"\n"+
                                "Tipo: "+c.getString(5)+"\n"+
                                "Ingresos: "+c.getString(6)+"\n"+
                                "Encuesta: "+c.getString(7)+"\n"+
                                "Vivienda: "+c.getString(8), Toast.LENGTH_LONG).show();*/
            }while (c.moveToNext());
        }
        db.close();

        Intent intent = new Intent(this, InformacionMediosTransporteActivity.class);
        intent.putExtra("idHogar", idHogar);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_hogar, menu);
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
