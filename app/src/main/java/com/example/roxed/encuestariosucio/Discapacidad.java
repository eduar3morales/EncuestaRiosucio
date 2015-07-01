package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Discapacidad {
    //--- VARIABLES ---
    String tipoDiscapacidad;
    String duracionDiscapacidad;
    String[] herramientaApoyo;     //Esta debe ser un Array o una Lista
    String[] mediosTransporteDificilAcceso;

    //--- CONSTRUCTOR ---
    public Discapacidad(String mTipoDiscapacidad, String mDuracion, String[] arrayHerramienta, String[] arrayMediosTransporte)
    {
        tipoDiscapacidad = mTipoDiscapacidad;
        duracionDiscapacidad = mDuracion;
        herramientaApoyo = arrayHerramienta;
        mediosTransporteDificilAcceso = arrayMediosTransporte;
    }

    public String getTipoDiscapacidad()
    {
        return tipoDiscapacidad;
    }

    public String getDuracionDiscapacidad()
    {
        return duracionDiscapacidad;
    }

    public String[] getHerramientaApoyo()
    {
        return herramientaApoyo;
    }

    public String[] getMediosTransporteDificilAcceso()
    {
        return mediosTransporteDificilAcceso;
    }
}
