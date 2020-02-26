package com.example.morfaap.Models;

public class LocalModel {

    private int idLocal;
    private String nombre;
    private double lat;
    private double lon;
    private String direccion;
    private String numCelular;
    private int idPropietario;
    private UsuarioModel propietario;

    public LocalModel(int idLocal, String nombre, double lat, double lon, String direccion, String numCelular, int idPropietario, UsuarioModel propietario) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.direccion = direccion;
        this.numCelular = numCelular;
        this.idPropietario = idPropietario;
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "LocalModel{" +
                "IdLocal=" + idLocal +
                ", nombre='" + nombre + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", direccion='" + direccion + '\'' +
                ", numCelular='" + numCelular + '\'' +
                ", IdPropietario=" + idPropietario +
                ", propietario=" + propietario +
                '}';
    }

    public LocalModel() {
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public UsuarioModel getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioModel propietario) {
        this.propietario = propietario;
    }
}
