package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Encuesta {

    //--- VARIABLES ---
    String numeroEncuesta;
    String coordinadorEncuesta;
    String encuestador;
    String horaInicio;
    String fechaEncuesta;

    //--- CONSTRUCTOR ---
    public Encuesta(String mNumeroEncuesta, String mCoordinador, String mEncuestador, String mHoraInicio, String mFecha)
    {
        numeroEncuesta = mNumeroEncuesta;
        coordinadorEncuesta = mCoordinador;
        encuestador = mEncuestador;
        horaInicio = mHoraInicio;
        fechaEncuesta = mFecha;
    }

    public String getNumeroEncuesta()
    {
        return numeroEncuesta;
    }

    public String getCoordinadorEncuesta()
    {
        return coordinadorEncuesta;
    }

    public String getEncuestador()
    {
        return encuestador;
    }

    public String getHoraInicio()
    {
        return horaInicio;
    }

    public String getFechaEncuesta()
    {
        return fechaEncuesta;
    }


    //--- CREAR MÉTODO QUE HAGA LA INSERCIÓN EN LA BASE DE DATOS ---




}
