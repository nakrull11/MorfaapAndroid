package com.example.morfaap.ui.pedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.morfaap.R;
import com.example.morfaap.ui.adapters.ListaPlatosAdapter;

public class PedidosFragment extends Fragment {

    private PedidosViewModel pedidosViewModel;
    private ListaPlatosAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel = ViewModelProviders.of(this).get(PedidosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pedidos, container, false);

        TextView textView = root.findViewById(R.id.text_slideshow);
        textView.setText(ListaPlatosAdapter.getPlatosPedidos().toString());
        return root;
    }
}