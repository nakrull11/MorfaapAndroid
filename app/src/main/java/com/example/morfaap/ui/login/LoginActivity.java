package com.example.morfaap.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.morfaap.ui.nav.MainActivity;
import com.example.morfaap.Models.LoginView;
import com.example.morfaap.R;
import com.example.morfaap.ui.registro.RegistroActivity;

import java.math.BigDecimal;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private TextView error;
    private String token;
    private LoginViewModel loginViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);
        error = findViewById(R.id.tvError);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getError().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                error.setVisibility(integer);
                if(integer == 1){
                    error.setText("Usuario y/o password incorrecto");
                }
                else{
                    error.setText("Ha ocurrido un error interno");
                }
            }
        });
        loginViewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("onchanged del token",s);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void Registro(android.view.View view){
        Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
        //intent.putExtra("Latitud",Lat);
        //intent.putExtra("Longitud",Lon);
        startActivity(intent);
    }

    public void Login(android.view.View view){
        LoginView loginView = new LoginView();
        loginView.setEmail(email.getText().toString());
        loginView.setPassword(password.getText().toString());
        Log.d("entro al login","bien");
        loginViewModel.Login(loginView);
    }



}
