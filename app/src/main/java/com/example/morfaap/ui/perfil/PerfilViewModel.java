package com.example.morfaap.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.morfaap.Models.UsuarioModel;
import com.example.morfaap.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<String> token;
    private MutableLiveData<UsuarioModel> usuario;
    private SharedPreferences sp;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        usuario = new MutableLiveData<>();
        sp = context.getSharedPreferences("token",0);
    }



    public MutableLiveData<String> getToken() {
        return token;
    }

    public LiveData<UsuarioModel> getUsuario() {
        return usuario;
    }

    public void setUsuario(){
        Call<UsuarioModel> dato = ApiClient.getMyApiClient().getUsuario(sp.getString("token",""),sp.getString("email",""));
        Log.d("Llamo al metodo call","no fallo");
        dato.enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                if (response.isSuccessful()){
                    usuario.postValue(response.body());
                }else{
                    Toast.makeText(context,"Error al mostrar los datos",Toast.LENGTH_LONG).show();
                    Log.d("Respondio Mal",response.message());
                }
            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                Toast.makeText(context,"Las credenciales no son correctas",Toast.LENGTH_LONG).show();
                Log.d("Fallo",t.getMessage());
            }
        });
    }
}