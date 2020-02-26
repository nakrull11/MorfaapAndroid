package com.example.morfaap.request;

import android.util.Log;

import com.example.morfaap.Models.LoginView;
import com.example.morfaap.Models.PlatoModel;
import com.example.morfaap.Models.UsuarioModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class ApiClient {
    private static final String PATH="http://192.168.0.11:45455/api/";
    private static  MyApiInterface myApiInteface;
    private static String accessToken=null;


    public static MyApiInterface getMyApiClient(){


        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }
    public interface MyApiInterface {

        //Usuarios
        @POST("usuariomodels/login")
        Call<String> login(@Body LoginView loginView);

        @GET("usuariomodels/{email}")
        Call<UsuarioModel> getUsuario(@Header("Authorization") String token, @Path("email") String email);

        //Platos
        @GET("platomodels")
        Call<List<PlatoModel>> getPlatos(@Header("Authorization")String token);

    }
}
