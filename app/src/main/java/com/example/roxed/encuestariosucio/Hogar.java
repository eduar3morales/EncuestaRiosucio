package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Hogar {
    //--- VARIABLES ---
    int cantidadPersonasConformanHogar;
    int cantidadPersonasViajanDiaTipico;
    int cantidadPersonasViajanDiaSabado;
    int cantidadPersonasPresentes;
    String tipoPropiedad;
    String rangoIngresos;

    //--- CONSTRUCTOR ---
    public Hogar(int mCantidadPersonasConformanHogar, int mCantidadPersonasViajanDiaTipico, int mCantidadPersonasViajanDiaSabado, int mCantidadPersonasPresentes, String mTipoPropiedad, String mRangoIngresos)
    {
        cantidadPersonasConformanHogar = mCantidadPersonasConformanHogar;
        cantidadPersonasViajanDiaTipico = mCantidadPersonasViajanDiaTipico;
        cantidadPersonasViajanDiaSabado = mCantidadPersonasViajanDiaSabado;
        cantidadPersonasPresentes = mCantidadPersonasPresentes;
        tipoPropiedad = mTipoPropiedad;
        rangoIngresos = mRangoIngresos;

    }

    public int getCantidadPersonasConformanHogar()
    {
        return cantidadPersonasConformanHogar;
    }

    public int getCantidadPersonasViajanDiaTipico()
    {
        return cantidadPersonasViajanDiaTipico;
    }

    public int getCantidadPersonasViajanDiaSabado()
    {
        return cantidadPersonasViajanDiaSabado;
    }

    public int getCantidadPersonasPresentes()
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
