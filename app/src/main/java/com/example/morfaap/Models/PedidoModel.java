package com.example.morfaap.Models;

import java.util.Date;

public class PedidoModel {

    private int IdPedido;
    private Date Fecha;
    private String Estado;
    private int IdUsuario;
    private UsuarioModel Usuario;

    public PedidoModel(int idPedido, Date fecha, String estado, int idUsuario, UsuarioModel usuario) {
        IdPedido = idPedido;
        Fecha = fecha;
        Estado = estado;
        IdUsuario = idUsuario;
        Usuario = usuario;
    }

    public PedidoModel() {
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public UsuarioModel getUsuario() {
        return Usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        Usuario = usuario;
    }
}
