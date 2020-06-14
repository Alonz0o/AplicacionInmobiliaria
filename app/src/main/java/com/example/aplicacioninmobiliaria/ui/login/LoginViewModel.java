package com.example.aplicacioninmobiliaria.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aplicacioninmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> tvMensaje;
    private MutableLiveData<String> token;
    private MutableLiveData<Boolean> error;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<String> getTvMensaje(){
        if(tvMensaje == null){
            tvMensaje = new MutableLiveData<>();
        }
        return tvMensaje;
    }

    public LiveData<Boolean> getError(){
        if(error==null){
            error=new MutableLiveData<>();
        }
        return error;
    }

    public MutableLiveData<String> getToken(){
        if(token==null){
            token=new MutableLiveData<>();
        }
        return token;
    }

    public void inputsCambian(EditText email, EditText contrasena, Button btnLogin) {

        String emailInput = email.getText().toString().trim();
        String contrasenaInput = contrasena.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!emailInput.matches(emailPattern))  {
            email.setError("Ingrese Email valido..");
            email.requestFocus();
            email.setTextColor(Color.rgb( 197, 34, 9));
        } else email.setTextColor(Color.rgb(20, 73, 160));
        if (contrasenaInput.length() < 5 ) {
            contrasena.setError("La Contraseña debe contener 5 o más caracteres..");
            contrasena.setTextColor(Color.rgb(197, 34, 9));
        } else
            contrasena.setTextColor(Color.rgb( 20, 73, 160 ));
        if ( contrasenaInput.length() > 4 && emailInput.matches(emailPattern)){
            btnLogin.setEnabled(true);
        }
        else {
            btnLogin.setEnabled(false);
        }
    }

    public void login(String email, String password){
        Call<String> cuentaLogeo = ApiClient.getMyApiClient().login(email, password);
        cuentaLogeo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {

                    token.postValue(response.body());
                    SharedPreferences sharedPreferences = context.getSharedPreferences("token",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String t="Bearer "+response.body();
                    editor.putString("token", t);
                    editor.commit();

                    error.postValue(true);

                }else{
                    error.postValue(false);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplication(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void registro() {
    }
}

