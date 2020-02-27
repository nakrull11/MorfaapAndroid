package com.example.morfaap.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.morfaap.Models.PedidoModel;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidoAdapter extends ArrayAdapter<PedidoModel> {

    private Context context;
    private List<PedidoModel> lista;
    private LayoutInflater li;

    public ListaPedidoAdapter(@NonNull Context context, int resource, @NonNull List<PedidoModel> objects , LayoutInflater li) {
        super(context, resource, objects);
        this.context = context;
        this.lista = objects;
        this.li = li;
    }


}
