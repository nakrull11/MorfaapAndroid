package com.example.morfaap.Models;

import java.time.LocalDate;
import java.util.Date;

public class UsuarioModel {

    private int idUsuario;
    private String email;
    private String fecNac;
    private String lat;
    private String lon;
    private String direccion;
    private String numCelular;
    private String password;

    public UsuarioModel(int idUsuario, String email, String fecNac, String lat, String lon, String direccion, String numCelular, String password) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.lat = lat;
        this.lon = lon;
        this.direccion = direccion;
        this.numCelular = numCelular;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "IdUsuario=" + idUsuario +
                ", email='" + email + '\'' +
                ", fecNac='" + fecNac + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", direccion='" + direccion + '\'' +
                ", numCelular='" + numCelular + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UsuarioModel() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
