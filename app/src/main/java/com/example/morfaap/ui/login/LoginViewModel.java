package com.example.morfaap.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.morfaap.Models.LoginView;
import com.example.morfaap.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> error;
    private MutableLiveData<String> token;
    private Context context;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Integer> getError() {
        if (error == null){
         error= new MutableLiveData<>();
        }
        return  error;
    }

    public MutableLiveData<String> getToken() {
        if (token == null){
            token= new MutableLiveData<>();
        }
        return  token;
    }

    public void Login(LoginView loginView){
        Call<String> dato = ApiClient.getMyApiClient().login(loginView);
        Log.d("llamada al token:",loginView.toString());
        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("entro el token:","respondio");
                    token.postValue(response.body());
                    SharedPreferences sp = context.getSharedPreferences("token",0);
                    SharedPreferences.Editor editor = sp.edit();
                    String t = "Bearer "+response.body();
                    editor.putString("token",t);
                    editor.putString("email",loginView.getEmail());
                    editor.commit();


                }else
                    Log.d("no respondio bien",response.message());
                    //Visible Visibility
                    error.postValue(1);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                error.postValue(0);
                Log.d("fallo",t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
