package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.model.Inmueble;
import com.example.aplicacioninmobiliaria.request.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleAgregarViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mensajeAgregadoMutableLiveData;

    public InmuebleAgregarViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<String> getMensajeAgregadoMutableLiveData() {
        if (mensajeAgregadoMutableLiveData == null) {
            mensajeAgregadoMutableLiveData = new MutableLiveData<>();
        }
        return mensajeAgregadoMutableLiveData;
    }

    public void guardarInmueble(Inmueble inmueble, final TextView mensaje, final TextView direccion, final TextView ambientes, final TextView superficie, final TextView latitud, final TextView longitud, final CheckBox  publicado, final CheckBox habilitado) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");

        Call<Inmueble> proActualizado = ApiClient.getMyApiClient().crearInmueble(
                accessToken,
                inmueble.getInmuebleId(),
                inmueble.getDireccion(),
                inmueble.getAmbientes(),
                inmueble.getSuperficie(),
                inmueble.getLatitud(),
                inmueble.getLongitud(),
                inmueble.getPropietarioId(),
                inmueble.getEstaPublicado(),
                inmueble.getEstaHabilitado());
        proActualizado.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Inmueble Agregado", Toast.LENGTH_LONG).show();
                    mensajeAgregadoMutableLiveData.setValue("Inmueble Agregado Correctamente");
                    mensaje.setVisibility(View.VISIBLE);
                    direccion.setText("");
                    ambientes.setText("");
                    superficie.setText("");
                    latitud.setText("");
                    longitud.setText("");
                    publicado.setChecked(false);
                    habilitado.setChecked(false);
                    direccion.setFocusableInTouchMode(true);
                    direccion.setFocusable(true);
                    direccion.requestFocus();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mensaje.setVisibility(View.GONE);
                        }
                    },6000);
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show();
                mensajeAgregadoMutableLiveData.setValue("Inmueble Agregar Error");
            }
        });
    }
}

