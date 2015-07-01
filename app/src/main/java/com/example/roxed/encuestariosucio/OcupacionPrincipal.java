package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class OcupacionPrincipal {
    //--- VARIABLES ---     La variables tipo de ocupación se utilizara para decir si es ocupación principal o secundaria y llenar la tabla OCUPACION_PRINCIPAL_SECUNDARIA
    String ocupacion;
    String lugarEstudio;
    String sectorTrabajo;
    String laborDesempeño;
    String lugarTrabajo;
    String direccionActividadPrincipal;
    String zatActividadPrincipal;
    String tipoOcupacion;

    //--- CONSTRUCTOR ---
    public OcupacionPrincipal(String mOcupacion, String mLugarEstudio, String mSectorTrabajo, String mLaborDesempeño, String mLugarTrabajo, String mDireccionActvidadPrincipal, String mZatActividadPrincpal)
    {
        ocupacion = mOcupacion;
        lugarEstudio = mLugarEstudio;
        sectorTrabajo = mSectorTrabajo;
        laborDesempeño = mLaborDesempeño;
        lugarTrabajo = mLugarTrabajo;
        direccionActividadPrincipal = mDireccionActvidadPrincipal;
        zatActividadPrincipal = mZatActividadPrincpal;
        tipoOcupacion = "OCUPACIÓN PRINCIPAL";
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

    public String getDireccionActividadPrincipal()
    {
        return direccionActividadPrincipal;
    }

    public String getZatActividadPrincipal()
    {
        return zatActividadPrincipal;
    }
}
