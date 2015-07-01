package com.example.roxed.encuestariosucio;

/**
 * Created by usuario on 1/07/15.
 */
public class Viaje {
    //--- VARIABLES ---
    String viajeNumero;
    String lugarOrigen;
    String zatOrigen;
    String horaSalida;
    String lugarDestino;
    String zatDestino;
    String horaLlegada;
    String motivoViaje;
    String modoViaje;
    String[] frecuenciaViaje;

    //--- CONSTRUCTOR ---
    public Viaje(String mViajeNumero, String mLugarOrigen, String mZatOrigen, String mHoraSalida, String mLugarDestino, String mZatDestino, String mHoraLlegada, String mMotivoViaje, String mModoViaje, String[] mFrecuenciaViaje)
    {
        viajeNumero = mViajeNumero;
        lugarOrigen = mLugarOrigen;
        zatOrigen = mZatOrigen;
        horaSalida = mHoraSalida;
        lugarDestino = mLugarDestino;
        zatDestino = mZatDestino;
        horaLlegada = mHoraLlegada;
        motivoViaje = mMotivoViaje;
        modoViaje = mModoViaje;
        frecuenciaViaje = mFrecuenciaViaje;
    }

    public String getViajeNumero()
    {
        return viajeNumero;
    }

    public String getLugarOrigen()
    {
        return lugarOrigen;
    }

    public String getZatOrigen()
    {
        return zatOrigen;
    }

    public String getHoraSalida()
    {
        return horaSalida;
    }

    public String getLugarDestino()
    {
        return lugarDestino;
    }

    public String getZatDestino()
    {
        return zatDestino;
    }

    public String getHoraLlegada()
    {
        return horaLlegada;
    }

    public String getMotivoViaje()
    {
        return motivoViaje;
    }

    public String getModoViaje()
    {
        return modoViaje;
    }

    public String[] getFrecuenciaViaje()
    {
        return frecuenciaViaje;
    }
}
