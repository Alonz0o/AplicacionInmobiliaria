package com.example.aplicacioninmobiliaria.model;

import java.io.Serializable;

@SuppressWarnings("Propietario")
public class Propietario implements Serializable {
    private int propietarioId;
    private int dni;
    private String apellido;
    private String nombre;
    private int telefono;
    private String email;
    private String clave;

    public Propietario() {
    }

    public Propietario(int propietarioId, int dni, String apellido, String nombre, int telefono, String email, String clave) {
        this.propietarioId = propietarioId;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }

    public int getPropietarioId() {
        return propietarioId;
    }
    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "propietarioId=" + propietarioId +
                ", dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", contrasena='" + clave + '\'' +
                '}';
    }
}
