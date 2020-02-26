package com.example.morfaap.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morfaap.MainActivity;
import com.example.morfaap.Models.LoginView;
import com.example.morfaap.R;

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

    public void Login(android.view.View view){
        LoginView loginView = new LoginView();
        loginView.setEmail(email.getText().toString());
        loginView.setPassword(password.getText().toString());
        Log.d("entro al login","bien");
        loginViewModel.Login(loginView);
    }

}
