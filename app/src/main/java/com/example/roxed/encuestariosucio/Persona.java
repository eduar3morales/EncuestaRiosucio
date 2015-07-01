package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Persona {
    //--- VARIABLES ---
    String codigoOrden;
    String nombre;
    int edad;
    String genero;
    String ultimoNivelEstudio;
    String usoRedCicloRuta;

    //--- CONSTRUCTOR ---
    public Persona(String mCodigoOrden, String mNombre, int mEdad, String mGenero, String mUltimoNivelEstudio, String mUsoCicloRuta)
    {
        codigoOrden = mCodigoOrden;
        nombre = mNombre;
        edad = mEdad;
        genero = mGenero;
        ultimoNivelEstudio = mUltimoNivelEstudio;
        usoRedCicloRuta = mUsoCicloRuta;
    }

    public String getCodigoOrden()
    {
        return codigoOrden;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getEdad()
    {
        return edad;
    }

    public String getGenero()
    {
        return genero;
    }

    public String getUltimoNivelEstudio()
    {
        return ultimoNivelEstudio;
    }

    public String getUsoRedCicloRuta()
    {
        return usoRedCicloRuta;
    }
}
