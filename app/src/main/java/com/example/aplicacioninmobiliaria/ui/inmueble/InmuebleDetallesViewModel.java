package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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


public class InmuebleDetallesViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mensajeModificadoMutableLiveData;
    public InmuebleDetallesViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<String> getMensajeModificadoMutableLiveData() {
        if (mensajeModificadoMutableLiveData == null) {
            mensajeModificadoMutableLiveData = new MutableLiveData<>();
        }
        return mensajeModificadoMutableLiveData;
    }

    public void botonHabilitadoInmueble(EditText Direccion, EditText Ambientes, EditText Superficie, EditText Latitud, EditText Longitud, CheckBox Publicado, CheckBox Habilitado,  Button Editar, Button Aceptar, Button Eliminar) {
        Direccion.setEnabled(true);
        Ambientes.setEnabled(true);
        Superficie.setEnabled(true);
        Latitud.setEnabled(true);
        Longitud.setEnabled(true);
        Publicado.setEnabled(true);
        Habilitado.setEnabled(true);
        Editar.setVisibility(View.GONE);
        Eliminar.setVisibility(View.GONE);
        Aceptar.setVisibility(View.VISIBLE);
        Aceptar.setEnabled(true);

    }

    public void actualizarInmueble(Inmueble inmueble, final TextView mensajeModificado) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");

        Call<Inmueble> proActualizado = ApiClient.getMyApiClient().actualizarInmueble(
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
                    Toast.makeText(getApplication(), "Inmueble Modificado", Toast.LENGTH_LONG).show();
                    mensajeModificadoMutableLiveData.setValue("Inmueble Modificado Correctamente");
                    mensajeModificado.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mensajeModificado.setVisibility(View.GONE);
                        }
                    },6000);
                }
            }
            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Inmueble Modificado Error", Toast.LENGTH_LONG).show();
                mensajeModificadoMutableLiveData.setValue("Inmueble Error");
            }
        });
    }

    public void eliminarInmueble(int id, final TextView mensajeEliminado, final Button btnEliminar, final Button btnEditar) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<Inmueble> cursoCall = ApiClient.getMyApiClient().eliminarInmueble(accessToken, id);
        cursoCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "ARREGLAR", Toast.LENGTH_LONG).show();
                    btnEliminar.setEnabled(false);
                    btnEditar.setVisibility(View.GONE);
                    btnEditar.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Inmueble Eliminado Permanentemente", Toast.LENGTH_LONG).show();
                mensajeEliminado.setVisibility(View.VISIBLE);
                btnEliminar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEditar.setVisibility(View.GONE);
                mensajeModificadoMutableLiveData.setValue("Inmueble Eliminado Correctamente");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mensajeEliminado.setVisibility(View.GONE);
                    }
                },6000);

            }
        });

    }
}

