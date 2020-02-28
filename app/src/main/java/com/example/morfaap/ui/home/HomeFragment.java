package com.example.morfaap.ui.home;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.R;
import com.example.morfaap.ui.adapters.ListaPlatosAdapter;
import com.example.morfaap.ui.perfil.PerfilViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<PlatoModel> lista = new ArrayList<>();
    private View root;
    private PerfilViewModel perfilViewModel;
    private Spinner spinner;
    private static String[] categorias ={"Todas","Sanwiches","Pizza","Tartas","Carne","Pescado","Pasta","Agregado"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        perfilViewModel =ViewModelProviders.of(this).get(PerfilViewModel.class);
        mostrar();
        incializar();
        Log.d("platos lista",lista.toString());
        return root;
    }

    public void mostrar(){
        homeViewModel.setPlatos();
        perfilViewModel.setUsuario();
    }


    public void incializar(){

        homeViewModel.getPlatos().observe(getViewLifecycleOwner(), new Observer<List<PlatoModel>>() {
            @Override
            public void onChanged(List<PlatoModel> platoModels) {
                lista = platoModels;
                ArrayAdapter<PlatoModel> adapter = new ListaPlatosAdapter(getActivity(),R.layout.elemento_lista,lista,getLayoutInflater());
                ListView lv = root.findViewById(R.id.lvLista);
                lv.setAdapter(adapter);
            }
        });

        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<UsuarioModel>() {
            @Override
            public void onChanged(UsuarioModel usuarioModel) {
                try {
                    Log.d("usuario home",usuarioModel.toString());
                    SharedPreferences sp = getContext().getSharedPreferences("idusuario",0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("idusuario",usuarioModel.getIdUsuario());
                    editor.commit();
                    Log.d("pref id",String.valueOf(sp.getInt("idusuario",0)));
                }catch (Exception ex){
                    Log.d("err",ex.getMessage());
                }
            }
        });
        spinner = root.findViewById(R.id.spinner);


    }


}