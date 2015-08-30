package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.Toast;

//--- Actividad Inicial de la encuesta donde se debe ingresar la información relacionada con la encuesta ---

public class MainActivity extends ActionBarActivity {


    int hora, minuto;
    int año, mes, dia;
    static final int TIME_DIALOG_ID = 0; //ID que identifica la fuente del dialogo para el TimePicker
    static final int DATE_DIALOG_ID = 1;
    EditText numeroEncuestaTxt;
    EditText encuestadorTxt;
    Spinner coordinadorSpinner;

    /**
     * Lista donde se almacenan las Opciones de Coordinador que se muestran en el Spinner
     */
    List<String> listaCoordinador = new ArrayList<String>();

    /**
     * Variable donde se almacena el número de encuesta
     */
    String numeroEncuesta;

    /**
     * Variable donde se almacena el coordinador de la encuesta
     */
    String coordinadorEncuesta;
    String encuestador;

    /**
     * Variable donde se almacena la hora de inicio de la encuesta
     */
    String horaInicio;

    /**
     * Variable donde se almacena la fecha en que se realiza la enceusta
     */
    String fechaEncuesta;

    /**
     * Text View que muestra la hora seleccionada
     */
    TextView txtHoraEncuesta;

    /**
     * Text View que muestra la fecha seleccionada
     */
    TextView txtHFechaEncuesta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //timePickerHoraInicio = (TimePicker) findViewById(R.id.timePickerHoraInicio);
        //timePickerHoraInicio.setIs24HourView(true);
        numeroEncuestaTxt = (EditText) findViewById(R.id.txtNumeroEncuesta);
        encuestadorTxt = (EditText) findViewById(R.id.txtEncuestador);
        coordinadorSpinner = (Spinner) findViewById(R.id.spinnerCoordinador);
        txtHoraEncuesta = (TextView) findViewById(R.id.txtHoraEncuesta);
        txtHFechaEncuesta = (TextView) findViewById(R.id.txtFechaEncuesta);

        // --- GENERAR AUTOMATICAMENTE EL NÚMERO DE LA SIGUIENTE ENCUESTA ---
        DBAdapter  db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllEncuestas();
        if(c.moveToFirst())
        {
            do{
                int numero = Integer.parseInt(c.getString(0));
                numero ++;
                numeroEncuestaTxt.setText(""+numero);
            }while (c.moveToNext());
        }
        db.close();




        listaCoordinador.add("JORGE ALBERTO MONTOYA");
        listaCoordinador.add("LUIS GABRIEL QUINA");
        listaCoordinador.add("GUSTAVO CANO");
        ArrayAdapter<String> adaptadorCoordinador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCoordinador);
        adaptadorCoordinador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coordinadorSpinner.setAdapter(adaptadorCoordinador);


        //Obtener fecha actual
        Calendar fechaActual = Calendar.getInstance();
        año = fechaActual.get(Calendar.YEAR);
        mes = fechaActual.get(Calendar.MONTH);
        dia = fechaActual.get(Calendar.DAY_OF_MONTH);

        DBAdapter dataB = new DBAdapter(this);
        dataB.open();
        Cursor cur = dataB.getAllEncuestas();
        if (cur.moveToFirst())
        {
            do{
                encuestadorTxt.setText(cur.getString(2));
            }while (cur.moveToNext());
        }
        db.close();



    }


    /**
     * Función que muestra el cuadro de dialogo que permite seleccionar la hora
     */
    public void onClickHora(View view)
    {
        showDialog(TIME_DIALOG_ID);
    }

    /**
     * Función que muestra el cuadro de dialogo que permite seleccionar la fecha
     */
    public void onClickFecha(View view)
    {
        showDialog(DATE_DIALOG_ID);
    }

    /**
     * Función que permite
     */
    public void onClickContinuarEncuesta(View view)
    {
        if (numeroEncuestaTxt.getText().toString().equals("") || encuestadorTxt.getText().toString().equals("") || txtHoraEncuesta.getText().toString().equals("")
                || txtHFechaEncuesta.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            numeroEncuesta = numeroEncuestaTxt.getText().toString();
            encuestador = encuestadorTxt.getText().toString();
            coordinadorEncuesta = coordinadorSpinner.getSelectedItem().toString();
            fechaEncuesta = dia+"/"+(mes+1)+"/"+año;
            horaInicio = txtHoraEncuesta.getText().toString();

            DBAdapter  db = new DBAdapter(this);
            db.open();
            long id = db.insertEncuesta(numeroEncuesta, coordinadorEncuesta, encuestador, horaInicio, fechaEncuesta);
            db.close();

            Intent intent = new Intent(this, InformacionViviendaActivity.class);
            intent.putExtra("numeroEncuesta", numeroEncuesta);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, hora, minuto, false);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, año, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            año = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            //Toast.makeText(getBaseContext(), "Fecha seleccionada: " + dia + "/"+ (mes + 1) + "/" + año, Toast.LENGTH_SHORT ).show();
            txtHFechaEncuesta.setText("FECHA: " + año + " / " + (mes + 1) + " / " + dia);
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int horaDia, int minutoHora)
        {
            hora = horaDia;
            minuto = minutoHora;

            SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss"); //Como poner este formato de 24 horas?????
            Date date = new Date();
            String strDate = timeFormat.format(date);
            //Toast.makeText(getBaseContext(), "Hora seleccionada "+ strDate, Toast.LENGTH_SHORT).show();

            //HORA MOSTRATA EN PANTALLA
            txtHoraEncuesta.setText(Horas(hora,minuto));

        }
    };

    /**
     * Función que da formato a la hora seleccionada
     * @param hora - Hora seleccionada del TimePicker
     * @param minuto - Minuto seleccionado del TimePicker
     * @return - Devuelve el formato de la hora xx:xx ó x:xx
     */
    private String Horas(int hora, int minuto){

        if(minuto<10){
            return "HORA: "+hora+":0"+minuto;
        }

        return "HORA  "+hora+":"+minuto;

    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.action_encuesta)
        {
            Intent intent = new Intent(this, ListActivityEncuesta.class);
            startActivity(intent);
        }

        if (id == R.id.action_vivienda)
        {
            Intent intent = new Intent(this, ListActivityVivienda.class);
            startActivity(intent);
        }

        if (id == R.id.action_hogar)
        {
            Intent intent = new Intent (this, ListActivityHogar.class);
            startActivity(intent);
        }

        if (id == R.id.action_medio_transporte)
        {
            Intent intent = new Intent(this, ListActivityMedioTransporte.class);
            startActivity(intent);
        }

        if (id == R.id.action_persona)
        {
            Intent intent = new Intent(this, ListActivityPersona.class);
            startActivity(intent);
        }

        if (id == R.id.action_discapacidad)
        {
            Intent intent = new Intent(this, ListActivityDiscapacidad.class);
            startActivity(intent);
        }

        if (id == R.id.action_ocupacion)
        {
            Intent intent = new Intent(this, ListActivityOcupacion.class);
            startActivity(intent);
        }

        if (id == R.id.action_viaje)
        {
            Intent intent = new Intent(this, ListActivityViaje.class);
            startActivity(intent);
        }

        if (id == R.id.action_herramienta_apoyo)
        {
            Intent intent = new Intent(this, ListActivityHerramientaApoyo.class);
            startActivity(intent);
        }

        if (id == R.id.action_transporte_dificil_acceso)
        {
            Intent intent = new Intent(this, ListActivityMediosTransporteDificilAcceso.class);
            startActivity(intent);
        }

        if (id == R.id.action_frecuencia_viaje)
        {
            Intent intent = new Intent(this, ListActivityFrecuenciaViaje.class);
            startActivity(intent);
        }

        if (id == R.id.action_restriccion)
        {
            Intent intent = new Intent(this, ListActivityRestricciones.class);
            startActivity(intent);
        }

        if (id == R.id.actin_exit)
        {
            MainActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
