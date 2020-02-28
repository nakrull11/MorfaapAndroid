package com.example.morfaap.Models;

import androidx.annotation.NonNull;

public class DetalleModel {

    private int idDetalle;
    private int idPedido;
    private PedidoModel pedido;
    private int idPlato;
    private PlatoModel plato;

    public DetalleModel(int idDetalle, int idPedido, PedidoModel pedido, int idPlato, PlatoModel plato) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.pedido = pedido;
        this.idPlato = idPlato;
        this.plato = plato;
    }

    public DetalleModel() {
    }

    @Override
    public String toString() {
        return "DetalleModel{" +
                "idDetalle=" + idDetalle +
                ", idPedido=" + idPedido +
                ", pedido=" + pedido +
                ", idPlato=" + idPlato +
                ", plato=" + plato +
                '}';
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public PlatoModel getPlato() {
        return this.plato;
    }

    public void setPlato(PlatoModel plato) {
        this.plato = plato;
    }
}
