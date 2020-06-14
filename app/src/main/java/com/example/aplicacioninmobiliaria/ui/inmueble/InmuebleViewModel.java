package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.model.Inmueble;
import com.example.aplicacioninmobiliaria.request.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> listInmueblesMutableLiveData;
    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();

    }
    public LiveData<List<Inmueble>> getListInmueblesMutableLiveData() {
        if (listInmueblesMutableLiveData == null) {
            listInmueblesMutableLiveData = new MutableLiveData<>();
        }
        return listInmueblesMutableLiveData;
    }

    public void cargarListViewInmuebles(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<List<Inmueble>> consulta = ApiClient.getMyApiClient().listaInmuebles(accessToken);
        consulta.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    List<Inmueble> inmuebles = response.body();
                    listInmueblesMutableLiveData.setValue(inmuebles);
                }
            }
            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {

            }
        });
    }
}