package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Vivienda {
    //--- VARIABLES ---
    String barrio;
    String estrato;
    String direccion;
    String zat;
    String telefono;
    String celular;
    String tipoVivienda;
    String cantidadHogaresVivienda;

    //--- CONSTRUCTOR ---
    public Vivienda(String mBarrio, String mEstrato, String mDireccion, String mZat, String mTelefono, String mCelular, String mTipoVivienda, String mCantidadHogares)
    {
        barrio = mBarrio;
        estrato = mEstrato;
        direccion = mDireccion;
        zat = mZat;
        telefono = mTelefono;
        celular = mCelular;
        tipoVivienda = mTipoVivienda;
        cantidadHogaresVivienda = mCantidadHogares;
    }

    public String getBarrio()
    {
        return barrio;
    }

    public String getEstrato()
    {
        return  estrato;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getZat()
    {
        return zat;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public String getCelular()
    {
        return celular;
    }

    public String getTipoVivienda()
    {
        return tipoVivienda;
    }

    public String getCantidadHogaresVivienda()
    {
        return cantidadHogaresVivienda;
    }


    //HACER MÉTODO QUE INSERTE LOS VALORES EN LA BASE DE DATOS
}
