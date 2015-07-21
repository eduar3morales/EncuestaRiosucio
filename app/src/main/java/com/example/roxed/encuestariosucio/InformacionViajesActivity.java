package com.example.roxed.encuestariosucio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformacionViajesActivity extends ActionBarActivity {

    int horaS, minutoS;
    int horaL, minutoL;
    static final int TIME_DIALOG_ID_SALIDA = 0;
    static final int TIME_DIALOG_ID_LLEGADA = 1;

    private CheckBox checkBoxLunesAViernes;
    private CheckBox checkBoxLunesASabado;
    private CheckBox checkBoxLunesADomingo;
    private CheckBox checkBoxLunes;
    private CheckBox checkBoxMartes;
    private CheckBox checkBoxMiercoles;
    private CheckBox checkBoxJueves;
    private CheckBox checkBoxViernes;
    private CheckBox checkBoxSabado;
    private CheckBox checkBoxDomingo;
    private EditText txtLugarOrigen;
    private EditText txtLugarDestino;
    TextView txtHoraSalida;
    TextView txtHoraLlegada;


    Spinner spinnerZatOrigen;
    Spinner spinnerZatDestino;
    Spinner spinnerModoDeViaje;
    Spinner spinnerMotivoDeViaje;
    private List<String> listaZatDestino = new ArrayList<String>();
    private List<String> listaZatOrigen = new ArrayList<String>();
    private List<String> listaModoDeViaje = new ArrayList<String>();
    private List<String> listaMotivoDeViaje = new ArrayList<String>();

    String viajeNumero;
    String lugarOrigen;
    String zatOrigen;
    String horaSalida;
    String lugarDestino;
    String zatDestino;
    String horaLlegada;
    String motivoViaje;
    String modoViaje;
    //String[] frecuenciaViaje;
    boolean somethingChecked = false;
    List<String> frecuenciaViaje = new ArrayList<String>();

    String idPersona; //Valor proveniente del Intent
    String idViaje;
    String nViaje;
    String zatVivienda;
    String zatViviendaBd;
    String numeroEncuesta;
    String idHogar;
    String cdOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_viajes);

        txtLugarOrigen = (EditText) findViewById(R.id.txtLugarDeOrigen);
        txtLugarDestino = (EditText) findViewById(R.id.txtLugarDeDestino);

        spinnerZatDestino = (Spinner) findViewById(R.id.spinnerZATDestino);
        spinnerZatOrigen = (Spinner) findViewById(R.id.spinnerZATOrigen);
        spinnerModoDeViaje = (Spinner) findViewById(R.id.spinnerModoDeViaje);
        spinnerMotivoDeViaje = (Spinner) findViewById(R.id.spinnerMotivoDeViaje);

        checkBoxLunesAViernes = (CheckBox) findViewById(R.id.checkBoxLunesAViernes);
        checkBoxLunesASabado = (CheckBox) findViewById(R.id.checkBoxLunesASabado);
        checkBoxLunesADomingo = (CheckBox) findViewById(R.id.checkBoxLunesADomingo);
        checkBoxLunes = (CheckBox) findViewById(R.id.checkBoxLunes);
        checkBoxMartes = (CheckBox) findViewById(R.id.checkBoxMartes);
        checkBoxMiercoles = (CheckBox) findViewById(R.id.checkBoxMiercoles);
        checkBoxJueves = (CheckBox) findViewById(R.id.checkBoxJueves);
        checkBoxViernes = (CheckBox) findViewById(R.id.checkBoxViernes);
        checkBoxSabado = (CheckBox) findViewById(R.id.checkBoxSabado);
        checkBoxDomingo = (CheckBox) findViewById(R.id.checkBoxDomingo);
        txtHoraSalida =(TextView) findViewById(R.id.txtHoraSalida);
        txtHoraLlegada =(TextView) findViewById(R.id.txtHoraLlegada);

        checkBoxLunesASabado.setChecked(false);
        checkBoxJueves.setChecked(false);

        for (int i = -1; i< 17; i++)
            listaZatOrigen.add(String.valueOf((i+1)));

        ArrayAdapter<String> adaptadorZatOrigen = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatOrigen);
        adaptadorZatOrigen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZatOrigen.setAdapter(adaptadorZatOrigen);


        for (int i = -1; i< 17; i++)
            listaZatDestino.add(String.valueOf((i+1)));

        ArrayAdapter<String> adaptadorZatDestino = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaZatDestino);
        adaptadorZatDestino.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZatDestino.setAdapter(adaptadorZatDestino);

        listaMotivoDeViaje.add("REGRESO A RESIDENCIA");
        listaMotivoDeViaje.add("TRABAJO");
        listaMotivoDeViaje.add("ESTUDIO");
        listaMotivoDeViaje.add("NEGOCIOS");
        listaMotivoDeViaje.add("COMPRAS");
        listaMotivoDeViaje.add("ASUNTOS PERSONALES");
        listaMotivoDeViaje.add("OTRO");

        ArrayAdapter<String> adaptadorMotivoViaje = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaMotivoDeViaje);
        adaptadorMotivoViaje.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMotivoDeViaje.setAdapter(adaptadorMotivoViaje);

        listaModoDeViaje.add("A PIE");
        listaModoDeViaje.add("BICICLETA");
        listaModoDeViaje.add("MOTOTAXI");
        listaModoDeViaje.add("MOTO");
        listaModoDeViaje.add("AUTOMOVIL PRIVADO");
        listaModoDeViaje.add("TAXI");
        listaModoDeViaje.add("BUS/BUSETA/MICROBUS");
        listaModoDeViaje.add("BUS INTERMUNICIPAL");
        listaModoDeViaje.add("VEHICULO MIXTO (JEEP)");
        listaModoDeViaje.add("CAMION");
        listaModoDeViaje.add("OTRO");

        ArrayAdapter<String> adaptadorModoViaje = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaModoDeViaje);
        adaptadorModoViaje.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModoDeViaje.setAdapter(adaptadorModoViaje);



        checkBoxLunesAViernes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
                if (isChecked)
                {
                    checkBoxLunesASabado.setEnabled(false);
                    checkBoxLunesADomingo.setEnabled(false);
                }
                else{
                    checkBoxLunesASabado.setEnabled(true);
                    checkBoxLunesADomingo.setEnabled(true);
                }
            }
        });

        checkBoxLunesASabado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChekced) {
                if (isChekced)
                {
                    checkBoxLunesAViernes.setEnabled(false);
                    checkBoxLunesADomingo.setEnabled(false);
                }
                else{
                    checkBoxLunesAViernes.setEnabled(true);
                    checkBoxLunesADomingo.setEnabled(true);
                }
            }
        });

        checkBoxLunesADomingo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    checkBoxLunesAViernes.setEnabled(false);
                    checkBoxLunesASabado.setEnabled(false);
                }
                else{
                    checkBoxLunesAViernes.setEnabled(true);
                    checkBoxLunesASabado.setEnabled(true);
                }
            }
        });




        idPersona = getIntent().getStringExtra("idPersona");
        Toast.makeText(this, "Id persona: "+ idPersona, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Id Persona: "+ idPersona+ " Numero Viaje: " +nroViaje, Toast.LENGTH_SHORT).show();

        zatVivienda = getIntent().getStringExtra("zatVivienda");

        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");

        idHogar = getIntent().getStringExtra("idHogar");

        cdOrden = getIntent().getStringExtra("codigoOrden");
        Toast.makeText(this, "Codigo orden: "+cdOrden, Toast.LENGTH_SHORT).show();

        nViaje = getIntent().getStringExtra("numeroViaje");
        Toast.makeText(this, "Numero viaje: "+nViaje, Toast.LENGTH_SHORT).show();

        int numeroViaje = (Integer.parseInt(nViaje));
        numeroViaje ++;

        nViaje = ""+numeroViaje;

    }

    public void onClickHoraLlegada(View view)
    {
        showDialog(TIME_DIALOG_ID_LLEGADA);
    }
    public void onClickHoraSalida(View view)
    {
        showDialog(TIME_DIALOG_ID_SALIDA);
    }



    public void onClickContinuarInformacionViajes(View view) {
        if (checkBoxLunesAViernes.isChecked())
        {
            frecuenciaViaje.add("LUNES A VIERNES");
            somethingChecked = true;
        }

        if (checkBoxLunesASabado.isChecked())
        {
            frecuenciaViaje.add("LUNES A SABADO");
            somethingChecked = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
        }

        if (checkBoxLunes.isChecked())
        {
            frecuenciaViaje.add("LUNES");
            somethingChecked = true;
        }

        if (checkBoxMartes.isChecked())
        {
            frecuenciaViaje.add("MARTES");
            somethingChecked = true;
        }

        if (checkBoxMiercoles.isChecked())
        {
            frecuenciaViaje.add("MIERCOLES");
            somethingChecked = true;
        }

        if (checkBoxJueves.isChecked())
        {
            frecuenciaViaje.add("JUEVES");
            somethingChecked = true;
        }

        if (checkBoxViernes.isChecked())
        {
            frecuenciaViaje.add("VIERNES");
            somethingChecked = true;
        }

        if (checkBoxSabado.isChecked())
        {
            frecuenciaViaje.add("SABADO");
            somethingChecked = true;
        }

        if (checkBoxDomingo.isChecked())
        {
            frecuenciaViaje.add("DOMINGO");
            somethingChecked = true;
        }

        if (txtLugarOrigen.getText().toString().equals("") || txtLugarDestino.getText().toString().equals("") || txtHoraSalida.getText().toString().equals("")
                || txtHoraLlegada.getText().toString().equals("") || (somethingChecked == false))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            viajeNumero = nViaje;
            lugarOrigen = txtLugarOrigen.getText().toString();
            lugarDestino = txtLugarDestino.getText().toString();
            zatOrigen = spinnerZatOrigen.getSelectedItem().toString();
            horaSalida = horaS+":"+minutoS;
            horaLlegada = horaL+":"+minutoL;
            motivoViaje = spinnerMotivoDeViaje.getSelectedItem().toString();
            modoViaje = spinnerModoDeViaje.getSelectedItem().toString();
            zatDestino = spinnerZatDestino.getSelectedItem().toString();

            boolean is = false;
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor cur  = db.getViaje(1); //Acá se debería de pasar si el númerod e viaje es 1
            if (cur.moveToFirst())
            {
                is = true;
            }
            db.close();
            /*if (is == true)
            {
                if (!zatVivienda.equals(zatOrigen))
                {
                    db.open();
                    long id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", cur.getString(0), numeroEncuesta );
                    db.close();
                }
            }*/

            db.open();
            long id = db.insertViaje(viajeNumero, lugarOrigen, zatOrigen, horaSalida, lugarDestino, zatDestino, horaLlegada, motivoViaje, modoViaje, idPersona);
            db.close();

            db.open();
            Cursor c = db.getAllViajes();
            if(c.moveToFirst())
            {
                do{
                    idViaje = c.getString(0);
                }while (c.moveToNext());
            }
            db.close();

            db.open();
            for (int i=0; i< frecuenciaViaje.size(); i++)
                id = db.insertFrecuenciaViaje(frecuenciaViaje.get(i), idViaje);

            db.close();

            Intent intent = new Intent(this,InformacionViajesActivity. class);
            intent.putExtra("numeroViaje", nViaje);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("codigoOrden", cdOrden);
            startActivity(intent);
            finish();
        }


    }

    public void onClickContinuarAgregarPersona(View view){
        if (checkBoxLunesAViernes.isChecked())
        {
            frecuenciaViaje.add("LUNES A VIERNES");
            somethingChecked = true;
        }

        if (checkBoxLunesASabado.isChecked())
        {
            frecuenciaViaje.add("LUNES A SABADO");
            somethingChecked = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
        }

        if (checkBoxLunes.isChecked())
        {
            frecuenciaViaje.add("LUNES");
            somethingChecked = true;
        }

        if (checkBoxMartes.isChecked())
        {
            frecuenciaViaje.add("MARTES");
            somethingChecked = true;
        }

        if (checkBoxMiercoles.isChecked())
        {
            frecuenciaViaje.add("MIERCOLES");
            somethingChecked = true;
        }

        if (checkBoxJueves.isChecked())
        {
            frecuenciaViaje.add("JUEVES");
            somethingChecked = true;
        }

        if (checkBoxViernes.isChecked())
        {
            frecuenciaViaje.add("VIERNES");
            somethingChecked = true;
        }

        if (checkBoxSabado.isChecked())
        {
            frecuenciaViaje.add("SABADO");
            somethingChecked = true;
        }

        if (checkBoxDomingo.isChecked())
        {
            frecuenciaViaje.add("DOMINGO");
            somethingChecked = true;
        }

        if (txtLugarOrigen.getText().toString().equals("") || txtLugarDestino.getText().toString().equals("") || txtHoraSalida.getText().toString().equals("")
                || txtHoraLlegada.getText().toString().equals("") || (somethingChecked == false))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            viajeNumero = nViaje;
            lugarOrigen = txtLugarOrigen.getText().toString();
            lugarDestino = txtLugarDestino.getText().toString();
            zatOrigen = spinnerZatOrigen.getSelectedItem().toString();
            horaSalida = txtHoraSalida.getText().toString();//horaS+":"+minutoS;
            horaLlegada = txtHoraLlegada.getText().toString();//horaL+":"+minutoL;
            motivoViaje = spinnerMotivoDeViaje.getSelectedItem().toString();
            modoViaje = spinnerModoDeViaje.getSelectedItem().toString();
            zatDestino = spinnerZatDestino.getSelectedItem().toString();

            boolean is = false;
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor cur  = db.getViaje(1); //Acá se debería de pasar si el númerod e viaje es 1
            if (cur.moveToFirst())
            {
                is = true;
            }
            db.close();
            /*if (is == true)
            {
                if (!zatVivienda.equals(zatOrigen))
                {
                    db.open();
                    long id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", cur.getString(0), numeroEncuesta );
                    db.close();
                }
            }*/



            db.open();
            long id = db.insertViaje(viajeNumero, lugarOrigen, zatOrigen, horaSalida, lugarDestino, zatDestino, horaLlegada, motivoViaje, modoViaje, idPersona);
            db.close();

            db.open();
            Cursor c = db.getAllViajes();
            if(c.moveToFirst())
            {
                do{
                    idViaje = c.getString(0);
                    zatViviendaBd = c.getString(6);

                }while (c.moveToNext());
            }
            db.close();

            /*if (! (zatVivienda.equals(zatDestino))) {
                db.open();
                    id = db.insertRestriccion("VIAJES", "LA ZAT del Viaje final no corresponde a la ZAT de residencia", idViaje, numeroEncuesta);
                db.close();
            }*/

            db.open();
            for (int i=0; i< frecuenciaViaje.size(); i++)
                id = db.insertFrecuenciaViaje(frecuenciaViaje.get(i), idViaje);
            db.close();

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(true);
            dialog.setMessage("¿Qué desea hacer?")
                    .setTitle("¿Esta seguro de que esta persona no realiza mas viajes?")
                    .setCancelable(true)
                    .setPositiveButton("Agregar viaje", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getBaseContext(), InformacionViajesActivity.class);
                            intent.putExtra("idHogar", idHogar);
                            intent.putExtra("nroEncuesta", numeroEncuesta);
                            intent.putExtra("codigoOrden", cdOrden);
                            intent.putExtra("numeroViaje", nViaje);
                            intent.putExtra("idPersona", idPersona);


                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Agregar persona", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getBaseContext(), InformacionPersonaActivity.class);
                            intent.putExtra("idHogar", idHogar);
                            intent.putExtra("nroEncuesta", numeroEncuesta);
                            intent.putExtra("codigoOrden", cdOrden);
                            startActivity(intent);
                            finish();
                        }
                    });
            dialog.show();

        }


    }

    public void onClickFinalizar(View view){
        if (checkBoxLunesAViernes.isChecked())
        {
            frecuenciaViaje.add("LUNES A VIERNES");
            somethingChecked = true;
        }

        if (checkBoxLunesASabado.isChecked())
        {
            frecuenciaViaje.add("LUNES A SABADO");
            somethingChecked = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
        }

        if (checkBoxLunes.isChecked())
        {
            frecuenciaViaje.add("LUNES");
            somethingChecked = true;
        }

        if (checkBoxMartes.isChecked())
        {
            frecuenciaViaje.add("MARTES");
            somethingChecked = true;
        }

        if (checkBoxMiercoles.isChecked())
        {
            frecuenciaViaje.add("MIERCOLES");
            somethingChecked = true;
        }

        if (checkBoxJueves.isChecked())
        {
            frecuenciaViaje.add("JUEVES");
            somethingChecked = true;
        }

        if (checkBoxViernes.isChecked())
        {
            frecuenciaViaje.add("VIERNES");
            somethingChecked = true;
        }

        if (checkBoxSabado.isChecked())
        {
            frecuenciaViaje.add("SABADO");
            somethingChecked = true;
        }

        if (checkBoxDomingo.isChecked())
        {
            frecuenciaViaje.add("DOMINGO");
            somethingChecked = true;
        }


        if (txtLugarOrigen.getText().toString().equals("") || txtLugarDestino.getText().toString().equals("") || txtHoraSalida.getText().toString().equals("")
                || txtHoraLlegada.getText().toString().equals("") || (somethingChecked == false))
        {
            Toast.makeText(getBaseContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            viajeNumero = nViaje;
            lugarOrigen = txtLugarOrigen.getText().toString();
            lugarDestino = txtLugarDestino.getText().toString();
            zatOrigen = spinnerZatOrigen.getSelectedItem().toString();
            horaSalida = txtHoraSalida.getText().toString(); //horaS+":"+minutoS;
            horaLlegada = txtHoraLlegada.getText().toString();//horaL+":"+minutoL;
            motivoViaje = spinnerMotivoDeViaje.getSelectedItem().toString();
            modoViaje = spinnerModoDeViaje.getSelectedItem().toString();
            zatDestino = spinnerZatDestino.getSelectedItem().toString();

            boolean is = false;
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor cur  = db.getViaje(1); //Acá se debería de pasar si el númerod e viaje es 1
            if (cur.moveToFirst())
            {
                is = true;
            }
            db.close();
            /*if (is == true)
            {
                if (!zatVivienda.equals(zatOrigen))
                {
                    db.open();
                    long id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", cur.getString(0), numeroEncuesta );
                    db.close();
                }
            }*/


            db.open();
            long id = db.insertViaje(viajeNumero, lugarOrigen, zatOrigen, horaSalida, lugarDestino, zatDestino, horaLlegada, motivoViaje, modoViaje, idPersona);
            db.close();

            db.open();
            Cursor c = db.getAllViajes();
            if(c.moveToFirst())
            {
                do{
                    idViaje = c.getString(0);
                    zatViviendaBd = c.getString(6);

                }while (c.moveToNext());
            }
            db.close();

            /*
            if (! (zatVivienda.equals(zatDestino))) {
                db.open();
                id = db.insertRestriccion("VIAJES", "LA ZAT del Viaje final no corresponde a la ZAT de residencia", idViaje, numeroEncuesta);
                db.close();
            }*/


            db.open();
            for (int i=0; i< frecuenciaViaje.size(); i++)
                id = db.insertFrecuenciaViaje(frecuenciaViaje.get(i), idViaje);
            db.close();

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("¿Qué desea hacer?")
                    .setCancelable(true)
                    .setTitle("Gracias por hacer uso de la aplicación Encuesta Riosucio")
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
                            InformacionViajesActivity.this.finish();
                        }
                    });
            dialog.show();
        }




    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case TIME_DIALOG_ID_SALIDA:
                return new TimePickerDialog(this, mTimeSetListenerSalida, horaS, minutoS, false);
            case TIME_DIALOG_ID_LLEGADA:
                return new TimePickerDialog(this, mTimeSetListenerLlegada, horaL, minutoL, false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListenerSalida = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int horaDia, int minutoHora)
        {
            horaS = horaDia;
            minutoS = minutoHora;
            txtHoraSalida.setText(Horas(horaS,minutoS));


        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListenerLlegada = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int horaDia, int minutoHora)
        {
            horaL = horaDia;
            minutoL = minutoHora;
            //HORA DE LLEGADA MOSTRADA EN PANTALLA
            txtHoraLlegada.setText(Horas(horaL,minutoL));

        }
    };

    private String Horas(int hora, int minuto){

        if(minuto<10){
            return "HORA:  "+hora+" : 0"+minuto+" ";
        }

        return "HORA  "+hora+" : "+minuto+" ";

    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacion_viajes, menu);
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

        if (id  == R.id.actin_exit)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Asegurese de haber completado todos los datos. ¿Qué desea hacer?")
                    .setCancelable(true)
                    .setTitle("Gracias por hacer uso de la aplicación Encuesta Riosucio")
                    .setPositiveButton("Seguir en la encuesta", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Finalizar la aplicación", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            InformacionViajesActivity.this.finish();
                        }
                    });
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
