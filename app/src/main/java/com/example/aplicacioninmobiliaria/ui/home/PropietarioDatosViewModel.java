package com.example.aplicacioninmobiliaria.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.model.Propietario;
import com.example.aplicacioninmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropietarioDatosViewModel extends AndroidViewModel {
    private Context context;
    private Propietario propietarioDatos;

    public PropietarioDatosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void leerPropietario(final EditText dni, final EditText apellido, final EditText nombre, final EditText telefono, final EditText email, final EditText contrasena) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<Propietario> propietarioCall = ApiClient.getMyApiClient().leer(accessToken);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Propietario propietario = response.body();
                    propietarioDatos = propietario;
                    dni.setText(String.valueOf(propietarioDatos.getDni()));
                    apellido.setText(propietarioDatos.getApellido());
                    nombre.setText(propietarioDatos.getNombre());
                    telefono.setText(String.valueOf(propietarioDatos.getTelefono()));
                    email.setText(propietarioDatos.getEmail());
                    contrasena.setText(propietarioDatos.getClave());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


