package com.example.morfaap.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.R;
import com.example.morfaap.ui.login.LoginActivity;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RegistroActivity extends AppCompatActivity implements LocationListener {
    private EditText email,etLan,etLon,direccion,numCelular,password;
    private EditText fecNacAño, fecNacMes, fecNacDia;
    private String fecNac;
    private double lat, lon;
    private RegistroViewModel registroViewModel;
    private CheckBox cbUbicacion;
    private UsuarioModel usuarioModel;
    private LocationManager ubicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuarioModel = new UsuarioModel();
        inicializar();
        localizacion();
        registroViewModel = ViewModelProviders.of(this).get(RegistroViewModel.class);


    }

    public void registrarse(View view){

        if(email.getText().toString() !="" && (fecNacAño.getText().toString() != ""
        && fecNacMes.getText().toString() !=""
        && fecNacDia.getText().toString() !="")
        && direccion.getText().toString() != ""
        && numCelular.getText().toString() !=""
        && password.getText().toString() !="") {
            usuarioModel.setEmail(email.getText().toString());
            fecNac = fecNacAño.getText().toString() + "-" + fecNacMes.getText().toString() + "-" + fecNacDia.getText().toString();
            usuarioModel.setFecNac(fecNac);
            usuarioModel.setLat(etLan.getText().toString());
            usuarioModel.setLon(etLon.getText().toString());
            usuarioModel.setDireccion(direccion.getText().toString());
            usuarioModel.setNumCelular(numCelular.getText().toString());
            usuarioModel.setPassword(password.getText().toString());


            if (registroViewModel.Registro(usuarioModel) == 1) {
                Toast.makeText(getApplicationContext(), "Registro exitoso!!!!", Toast.LENGTH_LONG).show();
            }
            alertaRegistroExito();
        }else{
            alertaRegistroError();
        }

    }

    private  void alertaRegistroError(){
        new AlertDialog.Builder(this).setTitle("Error al registrarse!!").setMessage("Fijate que todos los campos esten completos. Son todos obligatorios").setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    private  void alertaRegistroExito(){
        new AlertDialog.Builder(this).setTitle("Exito al registrarse!!").setMessage("Ahora podes inciar sesion y pedir comida!!").setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishActivity(0);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }

    private void inicializar(){

        email = findViewById(R.id.etEmailRegistro);
        fecNacAño = findViewById(R.id.etFecNacRegistroAño);
        fecNacMes = findViewById(R.id.etFecNacRegistroMes);
        fecNacDia = findViewById(R.id.etFecNacRegistroDia);
        cbUbicacion = findViewById(R.id.cbUbicacion);
        etLan = findViewById(R.id.etLatRegistro);
        etLon = findViewById(R.id.etLonRegistro);
        direccion = findViewById(R.id.etDireccionRegistro);
        numCelular = findViewById(R.id.etNumCelularRegistro);
        password = findViewById(R.id.etPasswordRegistro);


        Log.d("Latitud en registro",String.valueOf(etLan.getText()));


    }

    public void comprobar(android.view.View view){
        etLan.setEnabled(!cbUbicacion.isChecked());
        etLon.setEnabled(!cbUbicacion.isChecked());
    }


    private void localizacion() {

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions(this,new String[]{
                    //Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1000);
            return;
        }

        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);

        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        onLocationChanged(loc);



        if(ubicacion!=null){
            Log.d("Latitud",String.valueOf(loc.getLatitude()));
            Log.d("Longitud",String.valueOf(loc.getLongitude()));
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        double Latitud = location.getLatitude();
        double Longitud = location.getLongitude();
        etLan.setText(String.valueOf(Latitud));
        etLon.setText(String.valueOf(Longitud));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

