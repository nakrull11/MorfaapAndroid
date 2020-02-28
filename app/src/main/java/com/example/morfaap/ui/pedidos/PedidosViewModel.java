package com.example.morfaap.ui.pedidos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.morfaap.Models.DetalleModel;
import com.example.morfaap.Models.PedidoModel;
import com.example.morfaap.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidosViewModel extends AndroidViewModel {
    private MutableLiveData<String> token;
    private MutableLiveData<Integer> idPedido;
    private Context context;
    private int resd;
    private int id;
    private SharedPreferences sp;
    private MutableLiveData<PedidoModel> pedido;
    private MutableLiveData<DetalleModel> detalle;


    public PedidosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = context.getSharedPreferences("token",0);
    }

    public LiveData<Integer> getIdPedido(){
        if(idPedido==null){
            idPedido= new MutableLiveData<>();
        }
        return idPedido;
    }

    public MutableLiveData<String> getToken() {
        if(token == null){
            token = new MutableLiveData<>();
        }
        return token;
    }

    public MutableLiveData<PedidoModel> getPedido() {
        if(pedido == null){
            pedido = new MutableLiveData<>();
        }
        return pedido;
    }

    public MutableLiveData<DetalleModel> getDetalle() {
        if(detalle == null){
            detalle = new MutableLiveData<>();
        }
        return detalle;
    }

    public int agregarPedido(PedidoModel pedidoModel){
        Call<PedidoModel> dato = ApiClient.getMyApiClient().registrarPedido(sp.getString("token",""),pedidoModel);
        id = 0;
        dato.enqueue(new Callback<PedidoModel>() {
            @Override
            public void onResponse(Call<PedidoModel> call, Response<PedidoModel> response) {
                    id = response.body().getIdPedido();
                    Log.d("el id del pedido",String.valueOf(id));
            }
            @Override
            public void onFailure(Call<PedidoModel> call, Throwable t) {
                Log.d("fallo pedido",t.getMessage());
            }
        });
        return id;
    }

    public void getLastPedido(){
        Call<PedidoModel> dato = ApiClient.getMyApiClient().obtenerUltimoPedido(sp.getString("token",""));
        dato.enqueue(new Callback<PedidoModel>() {
            @Override
            public void onResponse(Call<PedidoModel> call, Response<PedidoModel> response) {
                pedido.postValue(response.body());
                Log.d("trajo el ultimo ped",response.message());
                Log.d("el ultimo pedid",response.body().toString());
            }

            @Override
            public void onFailure(Call<PedidoModel> call, Throwable t) {

            }
        });
    }

    public int agregarDetalle(DetalleModel detalleModel){
        Call<DetalleModel> dato = ApiClient.getMyApiClient().registrarDetalle(sp.getString("token",""),detalleModel);
        dato.enqueue(new Callback<DetalleModel>() {
            @Override
            public void onResponse(Call<DetalleModel> call, Response<DetalleModel> response) {
                try {
                    Log.d("detalle creado",response.message());
                    Log.d("detalles",response.toString());
                    Log.d("mas detalles",response.errorBody().string());
                    resd =1;
                }catch (Exception ex){
                    Log.d("detalle error err",ex.getMessage());
                    resd =2;
                }
            }
            @Override
            public void onFailure(Call<DetalleModel> call, Throwable t) {
                Log.d("fallo pedido",t.getMessage());
                resd=3;
            }
        });
        return resd;
    }


}