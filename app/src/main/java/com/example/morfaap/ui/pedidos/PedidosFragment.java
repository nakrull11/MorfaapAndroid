package com.example.morfaap.ui.pedidos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.morfaap.Models.DetalleModel;
import com.example.morfaap.Models.PedidoModel;
import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.R;
import com.example.morfaap.ui.adapters.ListaPlatosAdapter;
import com.example.morfaap.ui.perfil.PerfilViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PedidosFragment extends Fragment {

    private PedidosViewModel pedidosViewModel;
    private UsuarioModel usuario;
    private int idPedido;
    private PedidoModel pedidoControl = new PedidoModel();
    private PerfilViewModel perfilViewModel;
    private ArrayAdapter<PlatoModel> adapter;
    private PedidoModel pedidoModel;
    private DetalleModel detalleModel;
    private SharedPreferences sp;
    private TextView total;
    private Button btnPedido;
    private List<PlatoModel> listaPlatosPedidos = new ArrayList<>();
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);
        pedidosViewModel.getPedido().observe(getViewLifecycleOwner(), new Observer<PedidoModel>() {
            @Override
            public void onChanged(PedidoModel pedidoModel) {
                pedidoControl = pedidoModel;
            }
        });
        pedidosViewModel.getLastPedido();
        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        idPedido = 0;
        root = inflater.inflate(R.layout.fragment_pedidos, container, false);
        total= root.findViewById(R.id.tvTotal);
        btnPedido = root.findViewById(R.id.btnAgregarYPedir);
        iniciar();
        return root;
    }

    private void iniciar(){
        //Se carga la lista con los platos pedidos en el home
        listaPlatosPedidos = ListaPlatosAdapter.getPlatosPedidos();
        adapter = new ListaPedidoAdapter(getActivity(),R.layout.elemento_lista_pedido,listaPlatosPedidos,getLayoutInflater());
        ListView lv = root.findViewById(R.id.listaPedidos);
        lv.setAdapter(adapter);
        //el total del monto del pedido
        double cont=0;
        if(!listaPlatosPedidos.isEmpty()) {
            for (PlatoModel plato : listaPlatosPedidos) {
                cont += plato.getPrecio();
            }
        }
        total.setText("$"+cont);
        perfilViewModel.setUsuario();
        //obtener los datos del usuario
        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<UsuarioModel>() {
            @Override
            public void onChanged(UsuarioModel usuarioModel) {
                try {
                    usuario = usuarioModel;
                }catch (Exception ex){
                    Log.d("err",ex.getMessage());
                }
            }
        });


        //se arma el pedido en este boton
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listaPlatosPedidos.isEmpty()) {
                    pedidoModel = new PedidoModel();
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    pedidoModel.setFecha(formatter.format(date));
                    pedidoModel.setEstado("Pendiente");
                    pedidoModel.setIdUsuario(usuario.getIdUsuario());
                    pedidoModel.setUsuario(null);
                    pedidosViewModel.agregarPedido(pedidoModel);
                    try {
                        Log.d("El pedido control", pedidoControl.toString());
                        Log.d("El control",String.valueOf(idPedido));
                    } catch (Exception ex) {
                        Log.d("error pedido", ex.getMessage());
                    }
                    //Detalle
                    detalleModel = new DetalleModel();
                    for (PlatoModel plato : listaPlatosPedidos) {
                        detalleModel.setIdPedido(pedidoControl.getIdPedido()+1);
                        detalleModel.setPedido(null);
                        detalleModel.setIdPlato(plato.getIdPlato());
                        detalleModel.setPlato(null);
                        pedidosViewModel.agregarDetalle(detalleModel);
                    }
                    listaPlatosPedidos.clear();
                    adapter.notifyDataSetChanged();
                    actualizarTextPrecio(total,listaPlatosPedidos);
                    alertaPedido();
                    try {
                        Log.d("El detalle", String.valueOf(detalleModel));
                    } catch (Exception ex) {
                        Log.d("error detalle", ex.getMessage());
                    }

                }else{
                    Toast.makeText(getContext(),"No tienes nada para pedir!:(",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void alertaPedido(){
        new AlertDialog.Builder(getContext()).setTitle("Pedido Realizado!!").setMessage("Muy bien! tu pedido fue realizado, espera a que el delivery llegue a tu ubicacion. Posiblemente se pongan en contacto con vos!").setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }



    public void actualizarTextPrecio(TextView textView, List<PlatoModel> lista){
        double precioNuevo = 0;
        if(!listaPlatosPedidos.isEmpty()){
            for (PlatoModel plato: lista) {
                precioNuevo+=plato.getPrecio();
            }
        }
        textView.setText("$"+precioNuevo);
    }












    public class ListaPedidoAdapter extends ArrayAdapter<PlatoModel> {

        private Context context;
        private List<PlatoModel> listaPlatoPedidos;
        private LayoutInflater li;

        public ListaPedidoAdapter(@NonNull Context context, int resource, @NonNull List<PlatoModel> objects , LayoutInflater li) {
            super(context, resource, objects);
            this.context = context;
            this.listaPlatoPedidos = objects;
            this.li = li;
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null){
                itemView = li.inflate(R.layout.elemento_lista_pedido,parent,false);
            }


            PlatoModel plato = listaPlatoPedidos.get(position);

            TextView nombrePlato = itemView.findViewById(R.id.tvNombrePlatoPedido);
            nombrePlato.setText(plato.getNombre());
            TextView precioPlato = itemView.findViewById(R.id.tvPrecioPlatoPedido);
            precioPlato.setText("$"+plato.getPrecio());
            Button btnSacar = itemView.findViewById(R.id.btnSacar);

            btnSacar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listaPlatoPedidos.remove(position);
                    adapter.notifyDataSetChanged();
                    actualizarTextPrecio(total,listaPlatosPedidos);
                    Toast.makeText(getContext(),"Has sacado "+nombrePlato.getText()+" de tu pedido",Toast.LENGTH_SHORT).show();

                }
            });
            Log.d("El adapter","bien");
            //Aqui poner metodos OnClick

            return itemView;
        }


    }


}