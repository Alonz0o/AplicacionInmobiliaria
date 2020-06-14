package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Inmueble;


public class InmuebleAgregarActivity extends AppCompatActivity {

    private TextView tvMensajeAgree;
    private EditText etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud;
    private CheckBox cbPublicado, cbHabilitado;
    private Button btnIGuardar;
    private Inmueble inmueble = new Inmueble();
    private InmuebleAgregarViewModel inmuebleAgregarVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_inmueble);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iniciarVistas();
    }

    private void iniciarVistas() {
        inmuebleAgregarVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(InmuebleAgregarViewModel.class);

        etDireccion = (EditText)findViewById(R.id.etInmuebleDireccion);
        etAmbientes = (EditText)findViewById(R.id.etInmuebleAmbientes);
        etSuperficie = (EditText)findViewById(R.id.etInmuebleSuperficie);
        etLatitud = (EditText)findViewById(R.id.etInmuebleLatitud);
        etLongitud = (EditText)findViewById(R.id.etInmuebleLongitud);
        cbPublicado = (CheckBox)findViewById(R.id.cbInmueblePublicado);
        cbHabilitado = (CheckBox)findViewById(R.id.cbInmuebleHabilitado);
        btnIGuardar = (Button)findViewById(R.id.btnGuardarInmueble);
        tvMensajeAgree = (TextView)findViewById(R.id.tvMansajeInmuebleAgregado);

        inmuebleAgregarVM.getMensajeAgregadoMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMensajeAgree.setText(s);
            }
        });

        btnIGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inmueble.setDireccion(etDireccion.getText().toString());
                inmueble.setAmbientes(Integer.parseInt(etAmbientes.getText().toString()));
                inmueble.setSuperficie(Integer.parseInt(etSuperficie.getText().toString()));
                inmueble.setLatitud(etLatitud.getText().toString());
                inmueble.setLongitud(etLongitud.getText().toString());
                if (cbPublicado.isChecked()) inmueble.setEstaPublicado(true);
                else inmueble.setEstaPublicado(false);
                if (cbHabilitado.isChecked()) inmueble.setEstaHabilitado(true);
                else inmueble.setEstaHabilitado(false);
                inmuebleAgregarVM.guardarInmueble(inmueble, tvMensajeAgree, etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud, cbPublicado, cbHabilitado);

            }
        });

    }

    //Para el boton de regresar atras la flecha
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
