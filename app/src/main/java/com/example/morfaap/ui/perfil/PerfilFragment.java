package com.example.morfaap.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.R;

public class PerfilFragment extends Fragment {
    private View root;
    private EditText email,fecNac,direccion,numCelular,password;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_perfil,container,false);
        inicializar();
        return root;
    }

    private void inicializar(){
        email = root.findViewById(R.id.etEmailPerfil);
        fecNac = root.findViewById(R.id.etFecNacPerfil);
        direccion = root.findViewById(R.id.etDireccionPerfil);
        numCelular = root.findViewById(R.id.etNumCelularPerfil);
        password = root.findViewById(R.id.etPasswordPerfil);
        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        perfilViewModel.setUsuario();

        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<UsuarioModel>() {
            @Override
            public void onChanged(UsuarioModel usuarioModel) {
                if(usuarioModel !=null) {
                    email.setText(usuarioModel.getEmail());
                    fecNac.setText(usuarioModel.getFecNac());
                    direccion.setText(usuarioModel.getDireccion());
                    numCelular.setText(usuarioModel.getNumCelular());
                }else Toast.makeText(getContext(),"Hay un problema con el objeto",Toast.LENGTH_LONG).show();

            }
        });



    }
}