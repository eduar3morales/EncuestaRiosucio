package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Hogar {
    //--- VARIABLES ---
    String cantidadPersonasConformanHogar;
    String cantidadPersonasViajanDiaTipico;
    String cantidadPersonasViajanDiaSabado;
    String cantidadPersonasPresentes;
    String tipoPropiedad;
    String rangoIngresos;

    //--- CONSTRUCTOR ---
    public Hogar(String mCantidadPersonasConformanHogar, String mCantidadPersonasViajanDiaTipico, String mCantidadPersonasViajanDiaSabado, String mCantidadPersonasPresentes, String mTipoPropiedad, String mRangoIngresos)
    {
        cantidadPersonasConformanHogar = mCantidadPersonasConformanHogar;
        cantidadPersonasViajanDiaTipico = mCantidadPersonasViajanDiaTipico;
        cantidadPersonasViajanDiaSabado = mCantidadPersonasViajanDiaSabado;
        cantidadPersonasPresentes = mCantidadPersonasPresentes;
        tipoPropiedad = mTipoPropiedad;
        rangoIngresos = mRangoIngresos;

    }

    public String getCantidadPersonasConformanHogar()
    {
        return cantidadPersonasConformanHogar;
    }

    public String getCantidadPersonasViajanDiaTipico()
    {
        return cantidadPersonasViajanDiaTipico;
    }

    public String getCantidadPersonasViajanDiaSabado()
    {
        return cantidadPersonasViajanDiaSabado;
    }

    public String getCantidadPersonasPresentes()
    {
        return cantidadPersonasPresentes;
    }

    public String getTipoPropiedad()
    {
        return tipoPropiedad;
    }

    public String getRangoIngresos()
    {
        return rangoIngresos;
    }



}
