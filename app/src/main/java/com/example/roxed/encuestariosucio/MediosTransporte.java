package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class MediosTransporte {
    //--- VARIABLES ---
    String tipoVehiculo;
    String modeloVehiculo; //Ponerlo como NULL o como ""
    String kmUltimoA; //Ponerlo como NULL o como ""
    String lugarMatricula;
    String sitioEstacionamiento;

    //--- CONSTRUCTOR ---
    public MediosTransporte(String mTipoVehiculo, String mModeloVehiculo, String mKmUltimoA, String mLugarMatricula, String mSitioEstacionamiento)
    {
        tipoVehiculo = mTipoVehiculo;
        modeloVehiculo = mModeloVehiculo;
        kmUltimoA = mKmUltimoA;
        lugarMatricula = mLugarMatricula;
        sitioEstacionamiento = mSitioEstacionamiento;
    }

    public String getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    public String getModeloVehiculo()
    {
        return modeloVehiculo;
    }

    public String getKmUltimoA()
    {
        return kmUltimoA;
    }

    public String getLugarMatricula()
    {
        return lugarMatricula;
    }

    public String getSitioEstacionamiento()
    {
        return sitioEstacionamiento;
    }
}
