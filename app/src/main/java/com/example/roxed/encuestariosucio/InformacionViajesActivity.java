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
import android.widget.AdapterView;
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
    /**
     * Lista que almacena los valores de ZAT disponibles que se muestran en el Spinner Zat destino
     */
    private List<String> listaZatDestino = new ArrayList<String>();

    /**
     * Lista que almacena los valores de ZAT disponibles que se muestran en el Spinner Zat origen
     */
    private List<String> listaZatOrigen = new ArrayList<String>();

    /**
     * Lista que almacena los valores de Modo de viaje que se muestran en el Spinner Modo de viaje
     */
    private List<String> listaModoDeViaje = new ArrayList<String>();

    /**
     * Lista que almacena los valores de Motivo de viaje que se muestran en el Spinner Motivo Viaje
     */
    private List<String> listaMotivoDeViaje = new ArrayList<String>();

    /**
     * Variable que almacena el número de viaje de una persona
     */
    String viajeNumero;

    /**
     * Variable que almacena el lugar de origen de un viaje
     */
    String lugarOrigen;

    /**
     * Variable que almacena la Zat de origen del viaje
     */
    String zatOrigen;

    /**
     * Variable que almacena la hora de salida del viaje
     */
    String horaSalida;

    /**
     * Variable que almacena el lugar de destino del viaje
     */
    String lugarDestino;

    /**
     * Variable que almacena la Zat de destino del viaje
     */
    String zatDestino;

    /**
     * Variable que almacena la hora de llegada del viaje
     */
    String horaLlegada;

    /**
     * Variable que almacena el motivo del viaje
     */
    String motivoViaje;

    /**
     * Variable que almacena el modo del viaje
     */
    String modoViaje;
    //String[] frecuenciaViaje;
    boolean somethingChecked = false;

    /**
     * Lista que almacena los días en los que se realiza el viaje
     */
    List<String> frecuenciaViaje = new ArrayList<String>();
    boolean thereMotivo = false;
    boolean saturday = false;
    boolean motivoEstudio = false;
    boolean motivoTrabajo = false;

    /**
     * Variable que contiene el id de una persona proveniente de la actividad persona
     */
    String idPersona; //Valor proveniente del Intent

    String idViaje;
    String nViaje;

    /**
     * Variable que contiene la Zat de la vivienda proveniente de la actividad vivienda
     */
    String zatVivienda;
    String zatViviendaBd;

    /**
     * Variable que contiene el número de encuesta proveniente de la actividad principal
     */
    String numeroEncuesta;

    /**
     * Variable que contiene el id de hogar proveniente de la actividad hogar
     */
    String idHogar;

    /**
     * Variable que contiene el código de orden proveniente de la actividad persona
     */
    String cdOrden;

    /**
     * Variable que contiene la ocupación principal de una persona proveniente de la actividad ocupación principal
     */
    String ocupacion;

    /**
     * Variable que contiene el tipo de vehículo proveniente de la actividad medios de transporte
     */
    String tipoVehiculo;
    String viajeSabado;
    String hayMotivoViaje;

    /**
     * Variable que contiene la zat de la actividad princopal proveniente de la actividad ocupación principal
     */
    String zatActividad;

    String zatViajeAnterior;


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



        checkBoxLunesAViernes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkBoxLunesASabado.setEnabled(false);
                    checkBoxLunesADomingo.setEnabled(false);
                } else {
                    checkBoxLunesASabado.setEnabled(true);
                    checkBoxLunesADomingo.setEnabled(true);
                }
            }
        });

        checkBoxLunesASabado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChekced) {
                if (isChekced) {
                    checkBoxLunesAViernes.setEnabled(false);
                    checkBoxLunesADomingo.setEnabled(false);
                } else {
                    checkBoxLunesAViernes.setEnabled(true);
                    checkBoxLunesADomingo.setEnabled(true);
                }
            }
        });

        checkBoxLunesADomingo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkBoxLunesAViernes.setEnabled(false);
                    checkBoxLunesASabado.setEnabled(false);
                } else {
                    checkBoxLunesAViernes.setEnabled(true);
                    checkBoxLunesASabado.setEnabled(true);
                }
            }
        });

        spinnerMotivoDeViaje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerMotivoDeViaje.getSelectedItem().toString().equals("ESTUDIO"))
                {
                    motivoEstudio = true;
                    motivoTrabajo = false;
                }
                else if (spinnerMotivoDeViaje.getSelectedItem().toString().equals("TRABAJO"))
                {
                    motivoTrabajo = true;
                    motivoEstudio = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        idPersona = getIntent().getStringExtra("idPersona");
        //Toast.makeText(this, "Id persona: "+ idPersona, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Id Persona: "+ idPersona+ " Numero Viaje: " +nroViaje, Toast.LENGTH_SHORT).show();

        zatVivienda = getIntent().getStringExtra("zatVivienda");
        //Toast.makeText(this, "Zat vivienda:"+zatVivienda, Toast.LENGTH_SHORT).show();

        numeroEncuesta = getIntent().getStringExtra("nroEncuesta");

        idHogar = getIntent().getStringExtra("idHogar");

        cdOrden = getIntent().getStringExtra("codigoOrden");
        //Toast.makeText(this, "Codigo orden: "+cdOrden, Toast.LENGTH_SHORT).show();

        nViaje = getIntent().getStringExtra("numeroViaje");

        int numeroViaje = (Integer.parseInt(nViaje));
        numeroViaje ++;

        nViaje = ""+numeroViaje;
        //Toast.makeText(this, "Numero viaje: "+nViaje, Toast.LENGTH_SHORT).show();

        ocupacion = getIntent().getStringExtra("ocupacion");
        //Toast.makeText(this, "Ocupacion: "+ocupacion, Toast.LENGTH_SHORT).show();

        tipoVehiculo = getIntent().getStringExtra("vehiculo");
        //Toast.makeText(this, "Tipo vehiculo: "+tipoVehiculo, Toast.LENGTH_SHORT).show();

        viajeSabado = getIntent().getStringExtra("sabado");
        //Toast.makeText(this, "Viaje sabado: "+viajeSabado, Toast.LENGTH_SHORT).show();

        zatViajeAnterior = getIntent().getStringExtra("zatAnterior");
        //Toast.makeText(this, "Zat viaje anterior: "+zatViajeAnterior, Toast.LENGTH_SHORT).show();

        hayMotivoViaje = getIntent().getStringExtra("hayMotivoViaje");

        zatActividad = getIntent().getStringExtra("zatActividad");
        //Toast.makeText(this, "ZAT actividad: "+zatActividad, Toast.LENGTH_SHORT).show();

    }

    /**
     * Función que permite seleccionar la hora de llegada de viaje a través de un cuadro de dialogo
     */
    public void onClickHoraLlegada(View view)
    {
        showDialog(TIME_DIALOG_ID_LLEGADA);
    }

    /**
     * Función que permite seleccionar la hora de salida de viaje a travñes de un cuadro de dialogo
     */
    public void onClickHoraSalida(View view)
    {
        showDialog(TIME_DIALOG_ID_SALIDA);
    }


    /**
     *Función que permite hacer el registro de los datos de un viaje y permite registrar un nuevo viaje
     */
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
            saturday = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
            saturday = true;
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
            saturday = true;
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



            DBAdapter db = new DBAdapter(this);

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



            // --- Restricción Checked ---      //Mirar con la ZAT
            if (ocupacion.equals("ESTUDIAR") && motivoEstudio)
            {
                thereMotivo = true;
                hayMotivoViaje = "Si";
                if (!zatActividad.equals(zatDestino))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                    db.close();
                }

            }
            else if (ocupacion.equals("TRABAJAR") && motivoTrabajo)
            {
                thereMotivo = true;
                hayMotivoViaje = "Si";
                if (!zatActividad.equals(zatDestino))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                    db.close();
                }
            }


            // RESTRICCIÓN CHECKED
            if (viajeNumero.equals("1"))
            {
                if (!(zatOrigen.equals(zatVivienda)))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", idViaje, numeroEncuesta );
                    db.close();
                }
            }
            else if (!zatViajeAnterior.equals(zatOrigen))
            {
                db.open();
                id = db.insertRestriccion("VIAJES", "ZAT de destino no corresponde a la nueva ZAT de origen", idViaje, numeroEncuesta);
                db.close();
            }
            zatViajeAnterior = zatDestino;


            if (tipoVehiculo.equals("NO TIENE VEHÍCULO"))
            {
                if (modoViaje.equals("BICICLETA") || modoViaje.equals("MOTO") || modoViaje.equals("AUTOMOVIL PRIVADO") || modoViaje.equals("CAMION"))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "El viaje al parecer se realiza en un vehículo propio, pero el hogar no cuenta con uno", idViaje, numeroEncuesta);
                    db.close();
                }
            }

            if (viajeSabado.equals("0") && saturday== true)
            {
                db.open();
                id = db.insertRestriccion("VIAJES", "Viajes en el día sabado pero no hay personas que viaje el día sabado", idViaje, numeroEncuesta);
                db.close();
            }

            if (horaS == horaL)
            {
                if (minutoS > minutoL)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                    db.close();
                }
            }
            else if (horaS > horaL)
            {
                db.open();
                id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                db.close();
            }

            if (horaL-horaS > 1)
            {
                db.open();
                id = db.insertRestriccion("VIAJE", "El viaje tarda mas d euna hora", idViaje, numeroEncuesta);
                db.close();
            }


            Intent intent = new Intent(this,InformacionViajesActivity. class);
            intent.putExtra("numeroViaje", nViaje);
            intent.putExtra("idPersona", idPersona);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("codigoOrden", cdOrden);
            intent.putExtra("zatVivienda", zatVivienda);
            intent.putExtra("nroEncuesta", numeroEncuesta);
            intent.putExtra("ocupacion", ocupacion);
            intent.putExtra("vehiculo", tipoVehiculo);
            intent.putExtra("sabado", viajeSabado);
            intent.putExtra("zatAnterior", zatViajeAnterior);
            intent.putExtra("hayMotivoViaje", hayMotivoViaje);
            intent.putExtra("zatActividad", zatActividad);
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
            saturday = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
            saturday = true;
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
            saturday = true;
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


            final DBAdapter db = new DBAdapter(this);


            if (viajeNumero.equals("1"))
            {
                Toast.makeText(getBaseContext(), "Para finalizar los viajes, la persona debe tener por lo menos dos viajes registrados", Toast.LENGTH_SHORT).show();
            }
            else
            {
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


                db.open();
                for (int i=0; i< frecuenciaViaje.size(); i++)
                    id = db.insertFrecuenciaViaje(frecuenciaViaje.get(i), idViaje);
                db.close();


                // --- Restricción Checked ---      //Mirar con la ZAT
                if (ocupacion.equals("ESTUDIAR") && motivoEstudio)
                {
                    thereMotivo = true;
                    hayMotivoViaje = "Si";
                    if (!zatActividad.equals(zatDestino))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                        db.close();
                    }

                }
                else if (ocupacion.equals("TRABAJAR") && motivoTrabajo)
                {
                    thereMotivo = true;
                    hayMotivoViaje = "Si";
                    if (!zatActividad.equals(zatDestino))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                        db.close();
                    }
                }



                // RESTRICCIÓN CHECKED
                if (viajeNumero.equals("1"))
                {
                    if (!(zatOrigen.equals(zatVivienda)))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", idViaje, numeroEncuesta );
                        db.close();
                    }
                }
                else if (!zatViajeAnterior.equals(zatOrigen))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "ZAT de destino no corresponde a la nueva ZAT de origen", idViaje, numeroEncuesta);
                    db.close();
                }
                zatViajeAnterior = zatDestino;


                if (tipoVehiculo.equals("NO TIENE VEHÍCULO"))
                {
                    if (modoViaje.equals("BICICLETA") || modoViaje.equals("MOTO") || modoViaje.equals("AUTOMOVIL PRIVADO") ||
                            modoViaje.equals("CAMION"))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJES", "El viaje al parecer se realiza en un vehículo propio, pero el hogar no cuenta con uno", idViaje, numeroEncuesta);
                        db.close();
                    }
                }

                if (viajeSabado.equals("0") && saturday== true)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "Viajes en el día sabado pero no hay personas que viaje el día sabado", idViaje, numeroEncuesta);
                    db.close();
                }

                if (horaS == horaL)
                {
                    if (minutoS > minutoL)
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                        db.close();
                    }
                }
                else if (horaS > horaL)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                    db.close();
                }

                if (horaL-horaS > 1)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "El viaje tarda mas d euna hora", idViaje, numeroEncuesta);
                    db.close();
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(true);
                dialog.setMessage("¿Qué desea hacer?")
                        .setTitle("¿Esta seguro de que esta persona no realiza mas viajes?")
                        .setCancelable(false)
                        .setPositiveButton("Agregar viaje", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(getBaseContext(), InformacionViajesActivity.class);
                                intent.putExtra("idHogar", idHogar);
                                intent.putExtra("nroEncuesta", numeroEncuesta);
                                intent.putExtra("codigoOrden", cdOrden);
                                intent.putExtra("numeroViaje", nViaje);
                                intent.putExtra("idPersona", idPersona);
                                intent.putExtra("zatVivienda", zatVivienda);
                                intent.putExtra("ocupacion", ocupacion);
                                intent.putExtra("vehiculo", tipoVehiculo);
                                intent.putExtra("sabado", viajeSabado);
                                intent.putExtra("zatAnterior", zatViajeAnterior);
                                intent.putExtra("hayMotivoViaje", hayMotivoViaje);
                                intent.putExtra("zatActividad", zatActividad);

                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Agregar persona", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (hayMotivoViaje.equals("No")) {
                                    db.open();
                                    long idq = db.insertRestriccion("VIAJES", "No hay ningún motivo de viaje relacionado con la ocupación principal", idViaje, numeroEncuesta);
                                    db.close();
                                }


                                Intent intent = new Intent(getBaseContext(), InformacionPersonaActivity.class);
                                intent.putExtra("idHogar", idHogar);
                                intent.putExtra("nroEncuesta", numeroEncuesta);
                                intent.putExtra("codigoOrden", cdOrden);
                                intent.putExtra("zatVivienda", zatVivienda);
                                intent.putExtra("vehiculo", tipoVehiculo);
                                intent.putExtra("sabado", viajeSabado);
                                startActivity(intent);
                                finish();

                            }
                        });
                dialog.show();
            }

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
            saturday = true;
        }

        if (checkBoxLunesADomingo.isChecked())
        {
            frecuenciaViaje.add("LUNES A DOMINGO");
            somethingChecked = true;
            saturday = true;
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
            saturday = true;
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


            final DBAdapter db = new DBAdapter(this);


            if (viajeNumero.equals("1"))
            {
                Toast.makeText(this, "Para finalizar los viajes, la persona debe tener por lo menos dos viajes registrados", Toast.LENGTH_SHORT).show();
            }
            else
            {
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


                db.open();
                for (int i=0; i< frecuenciaViaje.size(); i++)
                    id = db.insertFrecuenciaViaje(frecuenciaViaje.get(i), idViaje);
                db.close();


                // --- Restricción Checked ---      //Mirar con la ZAT
                if (ocupacion.equals("ESTUDIAR") && motivoEstudio)
                {
                    thereMotivo = true;
                    hayMotivoViaje = "Si";
                    if (!zatActividad.equals(zatDestino))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                        db.close();
                    }

                }
                else if (ocupacion.equals("TRABAJAR") && motivoTrabajo)
                {
                    thereMotivo = true;
                    hayMotivoViaje = "Si";
                    if (!zatActividad.equals(zatDestino))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La ZAT de destino no corresponde con la ZAT de la actividad principal", idViaje, numeroEncuesta);
                        db.close();
                    }
                }


                // RESTRICCIÓN CHECKED
                if (viajeNumero.equals("1"))
                {
                    if (!(zatOrigen.equals(zatVivienda)))
                    {
                        if (!(zatOrigen.equals(zatVivienda)))
                        {
                            db.open();
                            id = db.insertRestriccion("VIAJES", "ZAT de origen del primer viaje no es Zat de residencia", idViaje, numeroEncuesta );
                            db.close();
                        }
                    }
                }
                else if (!zatViajeAnterior.equals(zatOrigen))
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "ZAT de destino no corresponde a la nueva ZAT de origen", idViaje, numeroEncuesta);
                    db.close();
                }

                zatViajeAnterior = zatDestino;

                if (tipoVehiculo.equals("NO TIENE VEHÍCULO"))
                {
                    if (modoViaje.equals("BICICLETA") || modoViaje.equals("MOTO") || modoViaje.equals("AUTOMOVIL PRIVADO") ||
                            modoViaje.equals("CAMION"))
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJES", "El viaje al parecer se realiza en un vehículo propio, pero el hogar no cuenta con uno", idViaje, numeroEncuesta);
                        db.close();
                    }
                }

                if (viajeSabado.equals("0") && saturday== true)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJES", "Viajes en el día sabado pero no hay personas que viaje el día sabado", idViaje, numeroEncuesta);
                    db.close();
                }

                if (horaS == horaL)
                {
                    if (minutoS > minutoL)
                    {
                        db.open();
                        id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                        db.close();
                    }
                }
                else if (horaS > horaL)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "La hora de salida es mayor a la hora de llegada", idViaje, numeroEncuesta);
                    db.close();
                }

                if (horaL-horaS > 1)
                {
                    db.open();
                    id = db.insertRestriccion("VIAJE", "El viaje tarda mas de una hora", idViaje, numeroEncuesta);
                    db.close();
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("¿Qué desea hacer?")
                        .setCancelable(false)
                        .setTitle("Gracias por hacer uso de la aplicación Encuesta Riosucio")
                        .setPositiveButton("Comenzar una nueva encuesta", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (hayMotivoViaje.equals("No")) {
                                    db.open();
                                    long idq = db.insertRestriccion("VIAJES", "No hay ningún motivo de viaje relacionado con la ocupación principal", idViaje, numeroEncuesta);
                                    db.close();
                                }

                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Finalizar la aplicación", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (hayMotivoViaje.equals("No")) {
                                    db.open();
                                    long idq = db.insertRestriccion("VIAJES", "No hay ningún motivo de viaje relacionado con la ocupación principal", idViaje, numeroEncuesta);
                                    db.close();
                                }
                                InformacionViajesActivity.this.finish();
                            }
                        });
                dialog.show();
            }




            //--- Restricción ---





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
            Toast.makeText(getBaseContext(), "Hora seleccionada: "+txtHoraSalida.getText().toString(), Toast.LENGTH_SHORT).show();


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
            Toast.makeText(getBaseContext(), "Hora seleccionada: "+txtHoraLlegada.getText().toString(), Toast.LENGTH_SHORT).show();

        }
    };

    /**
     *
     * @param hora
     * @param minuto
     * @return
     */
    private String Horas(int hora, int minuto){

        if(minuto<10){
            return "HORA:"+hora+":0"+minuto+" ";
        }

        return "HORA:"+hora+":"+minuto+" ";

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
