package com.example.morfaap.Models;

import java.util.Date;

public class PedidoModel {

    private int idPedido;
    private String fecha;
    private String estado;
    private int idUsuario;
    private UsuarioModel usuario;

    public PedidoModel(int idPedido, String fecha, String estado, int idUsuario, UsuarioModel usuario) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public PedidoModel() {
    }

    @Override
    public String toString() {
        return "PedidoModel{" +
                "idPedido=" + idPedido +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", idUsuario=" + idUsuario +
                ", usuario=" + usuario +
                '}';
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
