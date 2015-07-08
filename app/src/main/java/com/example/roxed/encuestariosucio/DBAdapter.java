package com.example.roxed.encuestariosucio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by usuario on 6/07/15.
 */
public class DBAdapter {
    static final String DATABASE_NAME = "encuestaRiosucio";
    static final int DATABASE_VERSION = 1;
    static final String TAG = "DBAdapter";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    // ------------------ INICIO DE LA CLASE DatabaseHelper SQLiteOpneHelper ----------------------
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
                //Todas las sentencias para crear las tablas
                db.execSQL("CREATE TABLE encuesta (_id_numero_encuesta primary key, coordinador text not null, encuestador text not null," +
                        "hora_inicio text not null, fecha text not null);");

                db.execSQL("CREATE TABLE vivienda (_id_vivienda primary key autoincrement, barrio text not null, estrato text not null, direccion text not null," +
                        " zat text not null, telefono text, celular text not null, tipo_vivienda text not null, cantidad_hogares_vivienda text not null);");

                db.execSQL("CREATE TABLE hogar (id_hogar primary key autoincrement, cantidad_personas_hogar text not null, cantidad_personas_dia_tipico text not null," +
                        " cantidad_personas_dia_sabado text not null, cantidad_personas_presentes text not null, tipo_propiedad text not null, ingresos_mensuales text);");

                db.execSQL("CREATE TABLE medios_transporte (_id_vehiculo primary key autoincrement, tipo_vehiculo text not null, modelo_vehiculo text, km_ultimo text," +
                        " lugar_matricula text not null, sitio_estacionamiento text not null);");

                db.execSQL("CREATE TABLE persona (_id_persona primary key autoincrement, codigo_orden text not null, nombre text not null, edad text not null, genero text not null," +
                        " ultimo_nivel_estudio text not null, uso_red_ciclovia text not null);");

                db.execSQL("CREATE TABLE viaje (_id_viaje primary key autoincrement, viaje_numero text not null, lugar_origen text not null, zat_origen text not null, hora_salida text not null," +
                        " lugar_destino text not null, zat_destino text not null, hora_llegada text not null, motivo_viaje text not null, modo_viaje text not null);");

                db.execSQL("CREATE TABLE modoTransporteDificilAcceso (_id_modo_trasnporte primary key autoincrement, modo_transporte text not null);");

                db.execSQL("CREATE TABLE herramientaApoyo (_id_herramienta_apoyo primary key autoincrement, herramienta_apoyo text not null);");

                db.execSQL("CREATE TABLE frecuenciaViaje (_id_frecuencia primary key autoincrement, nombre_dia text not null);");

                db.execSQL("CREATE TABLE discapacidad (_id_discapacidad primary key autoincrement, tipo_discapacidad text not null, discapacidad_duracion text not null);");

                db.execSQL("CREATE TABLE ocupacion (_id_ocupacion primary key autoincrement, ocupacion text not null, lugar_estudio text, sector_trabajo text, labor_desempeño text, lugar_trabajo text, " +
                        "direccion_Actividad text not null, zat_actividad text not null);");

                db.execSQL("CREATE TABLE discapacidad_persona"); //Definir Foreigns Keys

                db.execSQL("CREATE TABLE ocupacion_prin_sec"); //Definir Foreigns Keys

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Actualizando base de datos de la verisón " + oldVersion + " a " + newVersion + ", ¡Se borrará los datos anteriores!");
            db.execSQL("DROP TABLE IF EXISTS encuesta");
            db.execSQL("DROP TABLE IF EXISTS hogar");
            db.execSQL("DROP TABLE IF EXISTS vivienda");
            db.execSQL("DROP TABLE IF EXISTS medioTransporte");
            db.execSQL("DROP TABLE IF EXISTS persona");
            db.execSQL("DROP TABLE IF EXISTS viaje");
            db.execSQL("DROP TABLE IF EXISTS frecuenciaViaje");
            db.execSQL("DROP TABLE IF EXISTS dia");
            db.execSQL("DROP TABLE IF EXISTS discapacidadPersona");
            db.execSQL("DROP TABLE IF EXISTS discapacidad");
            db.execSQL("DROP TABLE IF EXISTS ocupacionPrincipalSecundaria");
            db.execSQL("DROP TABLE IF EXISTS herramientaApoyo");
            db.execSQL("DROP TABLE IF EXISTS modoTransporteDificilAcceso");
            db.execSQL("DROP TABLE IF EXISTS ocupacion");
            onCreate(db);
        }

        //Permitir el uso de Foreign Keys
        @Override
        public void onOpen(SQLiteDatabase db)
        {
            super.onOpen(db);
            if(!db.isReadOnly())
            {
                db.execSQL("PRAGMA foreigns_keys =ON;");
            }
        }
    }//--------------------- FIN DE LA CLASE DatabaseHelper SQLiteOpenHelper


    // --- Abre la base de datos ---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // --- Cierra la base de datos ---
    public void close()
    {
        DBHelper.close();
    }

    // --- Insertar valores en la base de datos ---
    public long insertEncuesta(String id, String coordinador, String encuestador, String horaInicio, String fecha)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("_id_numero_encuesta", id);
        initialValues.put("coordinador", coordinador);
        initialValues.put("encuestador", encuestador);
        initialValues.put("hora_inicio", horaInicio);
        initialValues.put("fecha", fecha);
        return db.insert("encuesta", null, initialValues);
    }


    public long insertVivienda(String barrio, String estrato, String direccion, String zat, String telefono, String celular, String tipoVivienda, String cantidadHogares)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("barrio", barrio);
        initialValues.put("estrato", estrato);
        initialValues.put("direccion", direccion);
        initialValues.put("zat", zat);
        initialValues.put("telefono", telefono);
        initialValues.put("celular", celular);
        initialValues.put("tipo_vivienda", tipoVivienda);
        initialValues.put("cantidad_hogares_vivienda", cantidadHogares);
        return db.insert("vivienda", null, initialValues);
    }

    public long insertHogar(String cantPersHogar, String cantPersViajanTipico, String cantPersViajanSabado, String cantPersPresentesHogar, String tipoPropiedad, String ingresosMensaules)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("cantidad_personas_hogar", cantPersHogar);
        initialValues.put("cantidad_personas_dia_tipico", cantPersViajanTipico);
        initialValues.put("cantidad_personas_dia_sabado", cantPersViajanSabado);
        initialValues.put("cantidad_personas_presentes", cantPersPresentesHogar);
        initialValues.put("tipo_propiedad", tipoPropiedad);
        initialValues.put("ingresos_mensuales", ingresosMensaules);
        return db.insert("hogar", null, initialValues);
    }

    public long insertMediosTransporte(String tipoVehiculo, String modeloVehiculo, String kmUltimo, String lugarMatricula, String sitioEstacionamiento)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("tipo_vehiculo", tipoVehiculo);
        initialValues.put("modelo_vehiculo", modeloVehiculo);
        initialValues.put("km_ultimo", kmUltimo);
        initialValues.put("lugar_matricula", lugarMatricula);
        initialValues.put("sitio_estacionamiento", sitioEstacionamiento);
        return db.insert("medios_transporte", null, initialValues);
    }

   
    public Cursor getAllContacts()
    {
        return db.query("encuesta", new String[] {"_id_numero_encuesta", "coordinador", "encuestador", "hora_inicio", "fecha"}, null,
                 null, null, null, null);
    }

}
