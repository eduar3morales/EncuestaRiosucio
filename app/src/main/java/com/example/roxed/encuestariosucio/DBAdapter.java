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
                        "horaInicio text not null, fecha text not null);");

                db.execSQL("CREATE TABLE vivienda (_id_vivienda integer primary key autoincrement, barrio text not null, estrato text not null, direccion text not null, "
                        +"zat text not null, telefono text, celular text, tipoVivienda text not null, cantidadHogaresVivienda text not null);");

                db.execSQL("CREATE TABLE hogar (_id_hogar integer primary key autoincrement, cantidadPersonasHogar text not null, cantidadPersonasDiaTipico text not null, " +
                        "cantidadPersonasDiaSabado text not null, cantidadPersonasPresentes text not null, tipoPropiedad text not null, ingresosMensuales text," +
                        " numeroEncuestaFk text not null, viviendaFk text not null);");

                db.execSQL("CREATE TABLE medioTransporte (_id_vehiculo integer primary key autoincrement, tipoVehiculo text not null, modeloVehiculo text, kmUltimo text, " +
                        "lugarMatricula text, sitioEstacionamiento text not null, hogarFk text not null);");

                db.execSQL("CREATE TABLE persona (_id_persona integer primary key autoincrement, codigoOrden text not null, nombre text not null, edad text not null, genero text not null, " +
                        "ultimoNivelEstudio text not null, usoRedCicloruta text not null, hogarFk text not null);");

                db.execSQL("CREATE TABLE viaje (_id_viaje integer primary key autoincrement, viajeNumero text not null, lugarOrigen text not null, zatOrigen text not null, horaSalida text not null, " +
                        "lugarDestino text not null, zatDestino text not null, horaLlegada text not null, motivoViaje text not null, modoViaje text not null, personaFk text not null);");

                db.execSQL("CREATE TABLE modoTransporteDificilAcceso (_id_modo_transporte integer primary key autoincrement, modoTransporte text not null, personaFk text not null);");

                db.execSQL("CREATE TABLE herramientaApoyo (_id_herramienta_apoyo integer primary key autoincrement, herramientaApoyo text not null, personaFk text not null);");

                db.execSQL("CREATE TABLE frecuenciaViaje (_id_frecuencia integer primary key autoincrement, nombreDia text not null, viajeFk text not null);");

                db.execSQL("CREATE TABLE discapacidad (_id_discapacidad integer primary key autoincrement, tipoDiscapacidad text not null, discapacidadDuracion text, personaFk text not null);");

                db.execSQL("CREATE TABLE ocupacion (_id_ocupacion integer primary key autoincrement, ocupacion text not null, lugarEstudio text, sectorTrabajo text, laborDesempeño text, " +
                        "direccionActividad text, zatActividad text, tipoOcupacion text not null, personaFk text not null);");

                db.execSQL("CREATE TABLE restriccion (_id_restriccion integer primary key autoincrement, nombreTabla text not null, descripcion text not null, referencia text not null, " +
                        "encuestaFk)");

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
            db.execSQL("DROP TABLE IF EXISTS restriccion");
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
        initialValues.put("horaInicio", horaInicio);
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
        initialValues.put("tipoVivienda", tipoVivienda);
        initialValues.put("cantidadHogaresVivienda", cantidadHogares);
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
        initialValues.put("codigoOrden", codigoOrden);
        initialValues.put("nombre", nombre);
        initialValues.put("edad", edad);
        initialValues.put("genero", genero);
        initialValues.put("ultimoNivelEstudio", ultimoNivelEstudio);
        initialValues.put("usoRedCicloruta", usoRedCicloruta);
        initialValues.put("hogarFk", hogarFk);
        return db.insert("persona", null, initialValues);
    }

    public long insertViaje(String numeroViaje, String lugarOrigen, String zatOrigen, String horaSalida, String lugarDestino, String zatDestino, String horaLlegada, String motivoViaje, String modoViaje, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("viajeNumero", numeroViaje);
        initialValues.put("lugarOrigen", lugarOrigen);
        initialValues.put("zatOrigen", zatOrigen);
        initialValues.put("horaSalida", horaSalida);
        initialValues.put("lugarDestino", lugarDestino);
        initialValues.put("zatDestino", zatDestino);
        initialValues.put("horaLlegada", horaLlegada);
        initialValues.put("motivoViaje", motivoViaje);
        initialValues.put("modoViaje", modoViaje);
        initialValues.put("personaFk", personaFk);
        return db.insert("viaje", null, initialValues);

    }

    public long insertModoTransporteDificilAcceso(String modoTransporte, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("modoTransporte", modoTransporte);
        initialValues.put("personaFk", personaFk);
        return db.insert("modoTransporteDificilAcceso", null, initialValues);
    }

    public long insertHerramientaApoyo(String herramientaApoyo, String personaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("herramientaApoyo", herramientaApoyo);
        initialValues.put("personaFk", personaFk);
        return db.insert("herramientaApoyo", null, initialValues);
    }

    public long insertFrecuenciaViaje(String dia, String viajeFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("nombreDia", dia);
        initialValues.put("viajeFk", viajeFk);
        return db.insert("frecuenciaViaje", null, initialValues);
    }

    public long insertDiscapacidad(String tipoDiscapacidad, String duracionDiscapacidad, String personaFk)
    {
        ContentValues initialValues= new ContentValues();
        initialValues.put("tipoDiscapacidad", tipoDiscapacidad);
        initialValues.put("discapacidadDuracion", duracionDiscapacidad);
        initialValues.put("personaFk", personaFk);
        return db.insert("discapacidad", null, initialValues);
    }

    public long insertOcupacion(String ocupacion, String lugarEstudio, String sectorTrabajo, String laborDesempeño, String direccionActividad, String zatActividad, String tipoOcupacion, String persona_fk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("ocupacion", ocupacion);
        initialValues.put("lugarEstudio", lugarEstudio);
        initialValues.put("sectorTrabajo", sectorTrabajo);
        initialValues.put("laborDesempeño", laborDesempeño);
        initialValues.put("direccionActividad", direccionActividad);
        initialValues.put("zatActividad", zatActividad);
        initialValues.put("tipoOcupacion", tipoOcupacion);
        initialValues.put("personaFk", persona_fk);
        return db.insert("ocupacion", null, initialValues);
    }

    public long insertRestriccion(String nombreTabla, String descripcion, String referencia, String encuestaFk)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("nombreTabla", nombreTabla);
        initialValues.put("descripcion", descripcion);
        initialValues.put("referencia", referencia);
        initialValues.put("encuestaFk", encuestaFk);
        return db.insert("restriccion", null, initialValues);
    }


    // --- Get ---
    public Cursor getAllEncuestas()
    {
        return db.query("encuesta", new String[]{"_id_numero_encuesta", "coordinador", "encuestador", "horaInicio", "fecha"}, null,
                null, null, null, null);
    }

    public Cursor getAllViviendas()
    {
        return db.query("vivienda", new String[]{"_id_vivienda", "barrio", "estrato", "direccion", "zat", "telefono", "celular",
                "tipoVivienda", "cantidadHogaresVivienda"}, null, null, null, null, null);
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
        return db.query("persona", new String[]{"_id_persona", "codigoOrden", "nombre", "edad", "genero", "ultimoNivelEstudio", "usoRedCicloruta", "hogarFk"}, null, null, null, null, null);
    }

    public Cursor getAllViajes()
    {
        return db.query("viaje", new String[] {"_id_viaje", "viajeNumero", "lugarOrigen", "zatOrigen", "horaSalida", "lugarDestino", "zatDestino", "horaLlegada",
                "motivoViaje", "modoViaje", "personaFk"}, null, null, null, null, null);
    }

    public Cursor getAllModoTransporteDificilAcceso()
    {
        return db.query("modoTransporteDificilAcceso", new String[] {"_id_modo_transporte", "modoTransporte", "personaFk"}, null, null, null,null, null);
    }

    public Cursor getAllHerramientaApoyo()
    {
        return db.query("herramientaApoyo", new String[] {"_id_herramienta_apoyo", "herramientaApoyo", "personaFk"}, null, null, null, null, null);
    }

    public Cursor getAllFrecuenciaViaje()
    {
        return db.query("frecuenciaViaje", new String[] {"_id_frecuencia", "nombreDia", "viajeFk"}, null, null, null, null, null);
    }

    public Cursor getAllDiscapacidad()
    {
        return db.query("discapacidad", new String[] {"_id_discapacidad", "tipoDiscapacidad", "discapacidadDuracion", "personaFk"}, null, null, null, null, null);
    }

    public Cursor getAllOcupacion()
    {
        return db.query("ocupacion", new String[]{"_id_ocupacion", "ocupacion", "lugarEstudio", "sectorTrabajo", "laborDesempeño", "direccionActividad",
                "zatActividad", "tipoOcupacion", "personaFk"}, null, null, null, null, null);
    }

    public  Cursor getAllRestriccion()
    {
        return db.query("restriccion", new String[] {"_id_restriccion", "nombreTabla", "descripcion", "referencia", "encuestaFk"}, null, null, null, null, null);
    }


    public Cursor getViaje(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, "viaje", new String[] {"_id_viaje", "viajeNumero", "lugarOrigen", "zatOrigen", "horaSalida", "lugarDestino", "zatDestino", "horaLlegada",
                "motivoViaje", "modoViaje", "personaFk"}, "_id_viaje" + "=" + rowId, null, null, null, null, null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return  mCursor;
    }

}
