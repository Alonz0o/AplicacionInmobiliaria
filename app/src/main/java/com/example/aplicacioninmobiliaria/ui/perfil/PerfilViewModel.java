package com.example.aplicacioninmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.model.Propietario;
import com.example.aplicacioninmobiliaria.request.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<String> mutableLiveDataPerfilModificado;
    private MutableLiveData<Propietario> propietarioMutableLiveData;
    private Context context;



    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutableLiveData() {
        if (propietarioMutableLiveData == null) {
            propietarioMutableLiveData = new MutableLiveData<>();
        }
        return propietarioMutableLiveData;
    }

    public LiveData<String> getMutableLiveDataPerfilModificado() {
        if (mutableLiveDataPerfilModificado == null) {
            mutableLiveDataPerfilModificado = new MutableLiveData<>();
        }
        return mutableLiveDataPerfilModificado;
    }


    public void botonHabilitado(EditText Dni, EditText Apellido, EditText Nombre, EditText Telefono, EditText Correo, EditText Contraseña, Button editar, Button aceptar) {
        Dni.setEnabled(true);
        Apellido.setEnabled(true);
        Nombre.setEnabled(true);
        Telefono.setEnabled(true);
        Correo.setEnabled(true);
        Contraseña.setEnabled(true);
        aceptar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.GONE);
        aceptar.setEnabled(true);
    }

    public void leerPropietario() {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<Propietario> propietarioCall = ApiClient.getMyApiClient().leer(accessToken);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Propietario propietario = response.body();
                    propietarioMutableLiveData.postValue(propietario);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void actualizarPropietario(Propietario propietario) {
        SharedPreferences sp = context.getSharedPreferences("token", 0);
        String accessToken = sp.getString("token", "");
        Call<Propietario> proActualizado = ApiClient.getMyApiClient().actualizar(
                accessToken,
                propietario.getPropietarioId(),
                propietario.getDni(),
                propietario.getApellido(),
                propietario.getNombre(),
                propietario.getTelefono(),
                propietario.getEmail(),
                propietario.getClave());
        proActualizado.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Propietario Actualizado", Toast.LENGTH_LONG).show();
                    mutableLiveDataPerfilModificado.setValue("Perfil Modificado");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Propietario Error", Toast.LENGTH_LONG).show();
                mutableLiveDataPerfilModificado.setValue("Error Perfil");
            }
        });
    }
        public void datosCambian (EditText Dni, EditText Apellido, EditText Nombre, EditText
        Telefono, EditText Correo, EditText Contraseña, Button btnEdit){

            String dniInput = Dni.getText().toString().trim();
            String apellidoInput = Apellido.getText().toString().trim();
            String nomInput = Nombre.getText().toString().trim();
            String telInput = Telefono.getText().toString().trim();
            String correInput = Correo.getText().toString().trim();
            String contraInput = Contraseña.getText().toString().trim();

            String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            String numerosPattern = "^[0-9]{8,11}$";
            String letrasPattern = "^[a-zA-Z][a-zA-Z0]{2,20}$";
            String contraseñaPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

            if (!dniInput.matches(numerosPattern)) {
                Dni.setError("Ingrese Dni valido");
                Dni.requestFocus();
                Dni.setTextColor(Color.rgb( 197, 34, 9));
            } else {
                Dni.setTextColor(Color.rgb( 20, 73, 160 ));
            }
            if (!apellidoInput.matches(letrasPattern)) {
                Apellido.setError("Ingrese Apellido valido");
                Apellido.setTextColor(Color.rgb(197, 34, 9));
            } else {
                Apellido.setError(null);
                Apellido.setTextColor(Color.rgb( 20, 73, 160 ));
            }
            if (!nomInput.matches(letrasPattern)) {
                Nombre.setError("Ingrese Nombre valido");
                Nombre.setTextColor(Color.rgb(197, 34, 9));
            } else {
                Nombre.setError(null);
                Nombre.setTextColor(Color.rgb( 20, 73, 160 ));
            }
            if (!telInput.matches(numerosPattern)) {
                Telefono.setError("Ingrese Telefono valido");
                Telefono.setTextColor(Color.rgb(197, 34, 9));
            } else {
                Telefono.setError(null);
                Telefono.setTextColor(Color.rgb( 20, 73, 160 ));
            }
            if (!correInput.matches(emailPattern) || correInput == null) {
                Correo.setError("Ingrese Email valido");
                Correo.setTextColor(Color.rgb(197, 34, 9));
            } else {
                Correo.setError(null);
                Correo.setTextColor(Color.rgb( 20, 73, 160 ));
            }
            if (!contraInput.matches(contraseñaPattern)) {
                Contraseña.setError("La Contraseña tiene que tener Mayusculas, Minisculas y Numeros");
                Contraseña.setTextColor(Color.rgb(197, 34, 9));
            } else {
                Contraseña.setError(null);
                Contraseña.setTextColor(Color.rgb( 20, 73, 160 ));
            }

        }
    }
