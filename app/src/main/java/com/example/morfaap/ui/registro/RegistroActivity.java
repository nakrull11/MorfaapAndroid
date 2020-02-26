package com.example.morfaap.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.morfaap.R;

public class RegistroActivity extends AppCompatActivity {
    EditText email,fecNac,etLan,etLon,direccion,numCelular,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    private void inicializar(){
        email = findViewById(R.id.etEmailRegistro);
    }

}

