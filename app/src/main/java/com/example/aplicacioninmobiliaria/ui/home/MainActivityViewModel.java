package com.example.aplicacioninmobiliaria.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Propietario;
import com.example.aplicacioninmobiliaria.request.ApiClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> correoMutableLiveData;
    private MutableLiveData<String> nomApeMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getCorreoMutableLiveData() {
        if (correoMutableLiveData == null) {
            correoMutableLiveData = new MutableLiveData<>();
        }
        return correoMutableLiveData;
    }

    public LiveData<String> getNomApeMutableLiveData() {
        if (nomApeMutableLiveData == null) {
            nomApeMutableLiveData = new MutableLiveData<>();
        }
        return nomApeMutableLiveData;
    }

    public void cambiarUser() {

        SharedPreferences sp = getApplication().getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<Propietario> propietarioCall = ApiClient.getMyApiClient().leer(accessToken);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Propietario propietario = response.body();
                    correoMutableLiveData.setValue(propietario.getEmail());
                    nomApeMutableLiveData.setValue(propietario.getApellido()+" "+propietario.getNombre());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void prueba(Propietario propietarioVosto) {
        correoMutableLiveData.setValue(propietarioVosto.getEmail());
        nomApeMutableLiveData.setValue(propietarioVosto.getApellido()+" "+propietarioVosto.getNombre());
    }
}


