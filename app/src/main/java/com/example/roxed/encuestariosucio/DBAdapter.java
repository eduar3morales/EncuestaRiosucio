package com.example.roxed.encuestariosucio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

                db.execSQL("CREATE TABLE vivienda (_id_vivienda integer primary key autoincrement, barrio text not null, estrato text not null, direccion text not null, "
                        +"zat text not null, telefono text, celular text, tipo_vivienda text not null, cantidad_hogares_vivienda text not null);");

                db.execSQL("CREATE TABLE hogar (_id_hogar integer primary key autoincrement, cantidadPersonasHogar text not null, cantidadPersonasDiaTipico text not null, " +
                        "cantidadPersonasDiaSabado text not null, cantidadPersonasPresentes text not null, tipoPropiedad text not null, ingresosMensuales text," +
                        " numeroEncuestaFk text not null, viviendaFk text not null);");

                db.execSQL("CREATE TABLE medioTransporte (_id_vehiculo integer primary key autoincrement, tipoVehiculo text not null, modeloVehiculo text, kmUltimo text, " +
                        "lugarMatricula text, sitioEstacionamiento text not null, hogarFk text not null);");

                db.execSQL("CREATE TABLE persona (_id_persona integer primary key autoincrement, codigo_orden text not null, nombre text not null, edad text not null, genero text not null, " +
                        "ultimo_nivel_estudio text not null, uso_red_cicloruta text not null, hogar_fk text not null);");

                db.execSQL("CREATE TABLE viaje (_id_viaje integer primary key autoincrement, viaje_numero text not null, lugar_origen text not null, zat_origen text not null, hora_salida text not null, " +
                        "lugar_destino text not null, zat_destino text not null, hora_llegada text not null, motivo_viaje text not null, modo_viaje text not null, persona_fk text not null);");

                db.execSQL("CREATE TABLE modoTransporteDificilAcceso (_id_modo_trasnporte integer primary key autoincrement, modo_transporte text not null, persona_fk text not null);");

                db.execSQL("CREATE TABLE herramientaApoyo (_id_herramienta_apoyo integer primary key autoincrement, herramienta_apoyo text not null, persona_fk text not null);");

                db.execSQL("CREATE TABLE frecuenciaViaje (_id_frecuencia integer primary key autoincrement, nombre_dia text not null, viaje_fk text not null);");

                db.execSQL("CREATE TABLE discapacidad (_id_discapacidad integer primary key autoincrement, tipo_discapacidad text not null, discapacidad_duracion text, persona_fk text not null);");

                db.execSQL("CREATE TABLE ocupacion (_id_ocupacion integer primary key autoincrement, ocupacion text not null, lugar_estudio text, sector_trabajo text, labor_desempeño text, lugar_trabajo text, " +
                        "direccion_Actividad text, zat_actividad text, tipo_ocupacion text not null, persona_fk text not null);");

                // ---- Revisar bien esta tabla como crear los campos ----
                //db.execSQL("CREATE TABLE restriccion");

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
            db.execSQL("DROP TABLE IF EXISTS discapacidad");
            db.execSQL("DROP TABLE IF EXISTS herramientaApoyo");
            db.execSQL("DROP TABLE IF EXISTS modoTransporteDificilAcceso");
            db.execSQL("DROP TABLE IF EXISTS ocupacion");
            onCreate(db);
        }

        //Permitir el uso de Foreign Keys
        /*@Override
        public void onOpen(SQLiteDatabase db)
        {
            super.onOpen(db);
            if(!db.isReadOnly())
            {
                db.execSQL("PRAGMA foreigns_keys =ON;");
            }
        }*/
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

    public long insertHogar(String cantPersHogar, String cantPersViajanTipico, String cantPersViajanSabado, String cantPersPresentesHogar, String tipoPropiedad, String ingresosMensaules, String numeroEncuestaFk, String viviendaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("cantidadPersonasHogar", cantPersHogar);
        initialValues.put("cantidadPersonasDiaTipico", cantPersViajanTipico);
        initialValues.put("cantidadPersonasDiaSabado", cantPersViajanSabado);
        initialValues.put("cantidadPersonasPresentes", cantPersPresentesHogar);
        initialValues.put("tipoPropiedad", tipoPropiedad);
        initialValues.put("ingresosMensuales", ingresosMensaules);
        initialValues.put("numeroEncuestaFk", numeroEncuestaFk);
        initialValues.put("viviendaFk", viviendaFk);
        return db.insert("hogar", null, initialValues);
    }

    public long insertMediosTransporte(String tipoVehiculo, String modeloVehiculo, String kmUltimo, String lugarMatricula, String sitioEstacionamiento, String hogarFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("tipoVehiculo", tipoVehiculo);
        initialValues.put("modeloVehiculo", modeloVehiculo);
        initialValues.put("kmUltimo", kmUltimo);
        initialValues.put("lugarMatricula", lugarMatricula);
        initialValues.put("sitioEstacionamiento", sitioEstacionamiento);
        initialValues.put("hogarFk", hogarFk);
        return db.insert("medioTransporte", null, initialValues);
    }

    public long insertPersona(String codigoOrden, String nombre, String edad, String genero, String ultimoNivelEstudio, String usoRedCicloruta, String hogarFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("codigo_orden", codigoOrden);
        initialValues.put("nombre", nombre);
        initialValues.put("edad", edad);
        initialValues.put("genero", genero);
        initialValues.put("ultimo_nivel_estudio", ultimoNivelEstudio);
        initialValues.put("uso_red_cicloruta", usoRedCicloruta);
        initialValues.put("hogar_fk", hogarFk);
        return db.insert("persona", null, initialValues);
    }

    public long insertViaje(String numeroViaje, String lugarOrigen, String zatOrigen, String horaSalida, String lugarDestino, String zatDestino, String horaLlegada, String motivoViaje, String modoViaje, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("viaje_numero", numeroViaje);
        initialValues.put("lugar_origen", lugarOrigen);
        initialValues.put("zat_origen", zatOrigen);
        initialValues.put("hora_salida", horaSalida);
        initialValues.put("lugar_destino", lugarDestino);
        initialValues.put("zat_destino", zatDestino);
        initialValues.put("hora_llegada", horaLlegada);
        initialValues.put("motivo_viaje", motivoViaje);
        initialValues.put("modo_viaje", modoViaje);
        initialValues.put("persona_fk", personaFk);
        return db.insert("viaje", null, initialValues);

    }

    public long insertModoTransporteDificilAcceso(String modoTransporte, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("modo_transporte", modoTransporte);
        initialValues.put("persona_fk", personaFk);
        return db.insert("modoTransporteDificilAcceso", null, initialValues);
    }

    public long insertHerramientaApoyo(String herramientaApoyo, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("herramienta_apoyo", herramientaApoyo);
        initialValues.put("persona_fk", personaFk);
        return db.insert("herramientaApoyo", null, initialValues);
    }

    public long insertFrecuenciaViaje(String dia, String viajeFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("nombre_dia", dia);
        initialValues.put("viaje_fk", viajeFk);
        return db.insert("frecuenciaViaje", null, initialValues);
    }

    public long insertDiscapacidad(String tipoDiscapacidad, String duracionDiscapacidad, String personaFk)
    {
        ContentValues initialValues= new ContentValues();
        initialValues.put("tipo_discapacidad", tipoDiscapacidad);
        initialValues.put("duracion_discapacidad", duracionDiscapacidad);
        initialValues.put("persona_fk", personaFk);
        return db.insert("discapacidad", null, initialValues);
    }

    public long insertOcupacion(String ocupacion, String lugarEstudio, String sectorTrabajo, String laborDesempeño, String lugarTrabajo, String direccionActividad, String zatActividad, String tipoOcupacion, String persona_fk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("ocupacion", ocupacion);
        initialValues.put("lugar_estudio", lugarEstudio);
        initialValues.put("sector_trabajo", sectorTrabajo);
        initialValues.put("labor_desempeño", laborDesempeño);
        initialValues.put("lugar_trabajo", lugarTrabajo);
        initialValues.put("direccion_actividad", direccionActividad);
        initialValues.put("zat_actividad", zatActividad);
        initialValues.put("tipo_ocupacion", tipoOcupacion);
        initialValues.put("persona_fk", persona_fk);
        return db.insert("ocupacion", null, initialValues);
    }


    // --- Get ---
    public Cursor getAllEncuestas()
    {
        return db.query("encuesta", new String[]{"_id_numero_encuesta", "coordinador", "encuestador", "hora_inicio", "fecha"}, null,
                null, null, null, null);
    }

    public Cursor getAllViviendas()
    {
        return db.query("vivienda", new String[]{"_id_vivienda", "barrio", "estrato", "direccion", "zat", "telefono", "celular",
                "tipo_vivienda", "cantidad_hogares_vivienda"}, null, null, null, null, null);
    }

    public Cursor getAllHogares()
    {
        return db.query("hogar", new String[]{"_id_hogar", "cantidadPersonasHogar", "cantidadPersonasDiaTipico", "cantidadPersonasDiaSabado", "cantidadPersonasPresentes", "tipoPropiedad", "ingresosMensuales", "numeroEncuestaFk", "viviendaFk"}, null, null, null, null, null);
    }

    public Cursor getAllMediosTransporte()
    {
        return db.query("medioTransporte", new String[] {"_id_vehiculo", "tipoVehiculo", "modeloVehiculo", "kmUltimo", "lugarMatricula", "sitioEstacionamiento", "hogarFk"}, null, null, null, null, null);
    }

    public Cursor getAllPersonas()
    {
        return db.query("persona", new String[]{"_id_persona", "codigo_orden", "nombre", "edad", "genero", "ultimo_nivel_estudio", "uso_red_cicloruta", "hogar_fk"}, null, null, null, null, null);
    }

    public Cursor getAllViajes()
    {
        return db.query("viaje", new String[] {"_id_viaje", "viaje_numero", "lugar_origen", "zat_origen", "hora_salida", "lugar_destino", "zat_destino", "hora_llegada",
                "motivo_viaje", "modo_viaje", "persona_fk"}, null, null, null, null, null);
    }

    public Cursor getAllModoTransporteDificilAcceso()
    {
        return db.query("modoTransporteDificilAcceso", new String[] {"_id_modo_transporte", "modo_transporte", "persona_fk"}, null, null, null,null, null);
    }

    public Cursor getAllHerramientaApoyo()
    {
        return db.query("herramientaApoyo", new String[] {"_id_herramienta_apoyo", "herramienta_apoyo", "persona_fk"}, null, null, null, null, null);
    }

    public Cursor getAllFrecuenciaViaje()
    {
        return db.query("frecuenciaViaje", new String[] {"_id_frecuencia", "nombre_dia", "viaje_fk"}, null, null, null, null, null);
    }

    public Cursor getAllDiscapacidad()
    {
        return db.query("discapacidad", new String[] {"_id_discapacidad", "tipo_discapacidad", "discapacidad_duracion", "persona_fk"}, null, null, null, null, null);
    }

    public Cursor getAllOcupacion()
    {
        return db.query("ocupacion", new String[]{"_id_ocupacion", "ocupacion", "lugar_estudio", "sector_trabajo", "labor_desempeño", "lugar_trabajo", "direccion_actividad",
                "zat_actividad", "tipo_ocupacion", "persona_fk"}, null, null, null, null, null);
    }

}
