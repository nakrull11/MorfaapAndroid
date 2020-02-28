package com.example.morfaap.ui.registro;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.request.ApiClient;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    private int res;


    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }



    public int Registro(UsuarioModel usuarioModel){
        Call<UsuarioModel> dato = ApiClient.getMyApiClient().registro(usuarioModel);
        Log.d("call registro",usuarioModel.toString());
        dato.enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                Log.d("respondio registro",call.toString());
                try {
                    Log.d("respuesta registro",response.message());
                }catch (Exception ex){
                    Log.d("excepcion",ex.getMessage());
                }
                if(response.isSuccessful()){
                    res=1;
                }else {
                    res=2;
                }
            }
            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                Log.d("fallo registro",t.getMessage());
                res=3;
                Toast.makeText(context,"Fallo el registro"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return res;
    }
}
