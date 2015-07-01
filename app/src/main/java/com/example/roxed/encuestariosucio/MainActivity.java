package com.example.roxed.encuestariosucio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //timePickerHoraInicio = (TimePicker) findViewById(R.id.timePickerHoraInicio);
        //timePickerHoraInicio.setIs24HourView(true);

        //Obtener fecha actual
        Calendar fechaActual = Calendar.getInstance();
        año = fechaActual.get(Calendar.YEAR);
        mes = fechaActual.get(Calendar.MONTH);
        dia = fechaActual.get(Calendar.DAY_OF_MONTH);
    }

    public void onClickHora(View view)
    {
        showDialog(TIME_DIALOG_ID);
    }

    public void onClickFecha(View view)
    {
        showDialog(DATE_DIALOG_ID);
    }

    public void onClickContinuarEncuesta(View view)
    {
        Intent intent = new Intent(this, InformacionViviendaActivity.class);
        startActivity(intent);
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
            Toast.makeText(getBaseContext(), "Fecha seleccionada: " + dia + "/"+ (mes + 1) + "/" + año, Toast.LENGTH_SHORT ).show();
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


            Toast.makeText(getBaseContext(), "Hora seleccionada "+ strDate, Toast.LENGTH_SHORT).show(); //El TOAST esta mostrando la Hora del Sistema, Corregir
        }
    };

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

        return super.onOptionsItemSelected(item);
    }
}
