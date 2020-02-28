package com.example.morfaap.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListaPlatosAdapter extends ArrayAdapter<PlatoModel> {

    private Context context;
    private List<PlatoModel> lista;
    private static List<PlatoModel> platosPedidos = new ArrayList<>();
    private LayoutInflater li;

    public ListaPlatosAdapter(@NonNull Context context, int resource, @NonNull List<PlatoModel> objects , LayoutInflater li) {
        super(context, resource, objects);
        this.context = context;
        this.lista = objects;
        this.li = li;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null){
            itemView = li.inflate(R.layout.elemento_lista,parent,false);
        }

        PlatoModel plato = lista.get(position);

        TextView nombrePlato = itemView.findViewById(R.id.tvNombrePlato);
        nombrePlato.setText(plato.getNombre());
        TextView precioPlato = itemView.findViewById(R.id.tvPrecioPlato);
        precioPlato.setText("$"+plato.getPrecio());
        TextView nombreLocal = itemView.findViewById(R.id.tvNombreLocal);
        nombreLocal.setText(plato.getMenu().getLocal().getNombre());
        Button btnPedir = itemView.findViewById(R.id.btnPedir);
        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Has cargado "+nombrePlato.getText()+" a tu pedido",Toast.LENGTH_SHORT).show();
                platosPedidos.add(plato);
            }
        });
        Log.d("El adapter","bien");
        //Aqui poner metodos OnClick

        return itemView;
    }

    public static List<PlatoModel> getPlatosPedidos(){

        return platosPedidos;
    }
}
