package com.example.morfaap.Models;

public class PlatoModel {
    private int idPlato;
    private String nombre;
    private String categoria;
    private String precio;
    private String estado;
    private int idMenu;
    private MenuModel menu;

    public PlatoModel(int idPlato, String nombre, String categoria, String precio, String estado, int idMenu, MenuModel menu) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.idMenu = idMenu;
        this.menu = menu;
    }

    public PlatoModel() {
    }

    @Override
    public String toString() {
        return "PlatoModel{" +
                "IdPlato=" + idPlato +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                ", IdMenu=" + idMenu +
                ", menu=" + menu +
                '}';
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }
}
