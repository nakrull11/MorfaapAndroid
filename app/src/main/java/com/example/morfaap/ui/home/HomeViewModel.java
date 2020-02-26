package com.example.morfaap.ui.home;

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

import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    private Context context;
    private SharedPreferences sp;
    private MutableLiveData<List<PlatoModel>> platos;
    private MutableLiveData<PlatoModel> plato;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token",0);
    }

    public LiveData<List<PlatoModel>> getPlatos() {
        if(platos== null){
            platos=new MutableLiveData<>();
        }
        return platos;
    }

    public LiveData<PlatoModel> getPlato() {
        if (plato == null){
            plato = new MutableLiveData<>();
        }
        return plato;
    }

    public void setPlatos(){
        Call<List<PlatoModel>> dato = ApiClient.getMyApiClient().getPlatos(sp.getString("token",""));
        Log.d("dato",dato.toString());
        dato.enqueue(new Callback<List<PlatoModel>>() {
            @Override
            public void onResponse(Call<List<PlatoModel>> call, Response<List<PlatoModel>> response) {
                if (response.isSuccessful()){
                    platos.postValue(response.body());
                    Log.d("Respuesta plato","trajo un plato");
                    Log.d("los platos",response.body().toString());
                }
                else{
                    Toast.makeText(context,"Error"+response.message(),Toast.LENGTH_LONG).show();
                    Log.d("Respuesta plato","respuesta mala");
                }
            }

            @Override
            public void onFailure(Call<List<PlatoModel>> call, Throwable t) {
                Log.d("Fallo Platos",t.getMessage());
            }
        });
    }
}