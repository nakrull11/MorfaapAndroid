package com.example.morfaap.Models;

public class MenuModel {

    private int idMenu;
    private int idLocal;
    private LocalModel local;

    public MenuModel(int idMenu, int idLocal, LocalModel local) {
        this.idMenu = idMenu;
        this.idLocal = idLocal;
        this.local = local;
    }

    public MenuModel() {
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "IdMenu=" + idMenu +
                ", IdLocal=" + idLocal +
                ", local=" + local +
                '}';
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public LocalModel getLocal() {
        return local;
    }

    public void setLocal(LocalModel local) {
        this.local = local;
    }
}
