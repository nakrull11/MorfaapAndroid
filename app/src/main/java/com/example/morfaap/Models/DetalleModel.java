package com.example.morfaap.Models;

public class DetalleModel {

    private int IdDetalle;
    private int IdPedido;
    private PedidoModel Pedido;
    private int IdPlato;
    private PlatoModel Plato;

    public DetalleModel(int idDetalle, int idPedido, PedidoModel pedido, int idPlato, PlatoModel plato) {
        IdDetalle = idDetalle;
        IdPedido = idPedido;
        Pedido = pedido;
        IdPlato = idPlato;
        Plato = plato;
    }

    public DetalleModel() {
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        IdDetalle = idDetalle;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public PedidoModel getPedido() {
        return Pedido;
    }

    public void setPedido(PedidoModel pedido) {
        Pedido = pedido;
    }

    public int getIdPlato() {
        return IdPlato;
    }

    public void setIdPlato(int idPlato) {
        IdPlato = idPlato;
    }

    public PlatoModel getPlato() {
        return Plato;
    }

    public void setPlato(PlatoModel plato) {
        Plato = plato;
    }
}
