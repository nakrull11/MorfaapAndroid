package com.example.morfaap.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.R;
import com.example.morfaap.ui.adapters.ListaPlatosAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private List<PlatoModel> lista = new ArrayList<>();
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mostrar();
        incializar();
        Log.d("platos lista",lista.toString());
        return root;
    }

    public void mostrar(){
        homeViewModel.setPlatos();
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
    }

}