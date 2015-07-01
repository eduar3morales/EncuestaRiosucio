package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class OcupacionSecundaria {
    //--- VARIABLES ---
    String ocupacion;
    String lugarEstudio;
    String sectorTrabajo;
    String laborDesempeño;
    String lugarTrabajo;
    String direccionActividadSecundaria;
    String zatActividadSecundaria;
    String tipoOcupacion;

    //--- CONSTRUCTOR ---
    public OcupacionSecundaria(String mOcupacion, String mLugarEstudio, String mSectorTrabajo, String mLaborDesempeño, String mLugarTrabajo, String mDireccion, String mZat)
    {
        ocupacion = mOcupacion;
        lugarEstudio = mLugarEstudio;
        sectorTrabajo = mSectorTrabajo;
        laborDesempeño = mLaborDesempeño;
        lugarTrabajo = mLugarTrabajo;
        direccionActividadSecundaria = mDireccion;
        zatActividadSecundaria = mZat;
        tipoOcupacion = "OCUPACIÓN SECUNDARIA";
    }

    public String getOcupacion()
    {
        return ocupacion;
    }

    public String getLugarEstudio()
    {
        return lugarEstudio;
    }

    public String getSectorTrabajo()
    {
        return sectorTrabajo;
    }

    public String getLaborDesempeño()
    {
        return laborDesempeño;
    }

    public String getLugarTrabajo()
    {
        return lugarTrabajo;
    }

    public String getDireccionActividadSecundaria()
    {
        return direccionActividadSecundaria;
    }

    public String getZatActividadSecundaria()
    {
        return zatActividadSecundaria;
    }
}
