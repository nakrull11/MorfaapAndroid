package com.example.morfaap.Models;

public class ComentarioModel {

    private int IdUsuario;
    private int IdLocal;
    private String Comentario;
    private int Puntuacion;

    public ComentarioModel(int idUsuario, int idLocal, String comentario, int puntuacion) {
        IdUsuario = idUsuario;
        IdLocal = idLocal;
        Comentario = comentario;
        Puntuacion = puntuacion;
    }

    public ComentarioModel() {
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public int getIdLocal() {
        return IdLocal;
    }

    public void setIdLocal(int idLocal) {
        IdLocal = idLocal;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        Puntuacion = puntuacion;
    }
}
