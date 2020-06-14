package com.example.aplicacioninmobiliaria.model;

import java.io.Serializable;

@SuppressWarnings("Inmueble")
public class Inmueble implements Serializable {
    private int inmuebleId;
    private String direccion;
    private int ambientes;
    private int superficie;
    private String latitud;
    private String longitud;
    private int propietarioId;
    private Propietario duenio;
    private boolean estaPublicado;
    private boolean estaHabilitado;

    public Inmueble() {
    }

    public Inmueble(int inmuebleId, String direccion, int ambientes, int superficie, String latitud, String longitud, int propietarioId, boolean estaPublicado, boolean estaHabilitado) {
        this.inmuebleId = inmuebleId;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
        this.propietarioId = propietarioId;
        this.estaPublicado = estaPublicado;
        this.estaHabilitado = estaHabilitado;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }
    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }
    
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }
    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getSuperficie() {
        return superficie;
    }
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public boolean getEstaPublicado() {
        return estaPublicado;
    }
    public void setEstaPublicado(boolean estaPublicado) {
        this.estaPublicado = estaPublicado;
    }

    public boolean getEstaHabilitado() {
        return estaHabilitado;
    }
    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

}
