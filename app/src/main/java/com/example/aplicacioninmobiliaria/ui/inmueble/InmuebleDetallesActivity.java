package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Inmueble;

public class InmuebleDetallesActivity extends AppCompatActivity {

    private TextView tvEliModInmueble;
    private EditText etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud;
    private CheckBox cbPublicado, cbHabilitado;
    private Button btnIEditar, btnIEliminar, btnIEditModificarInmueble;
    private Inmueble inmueble;
    private InmuebleDetallesViewModel inmuebleDetallesVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmueble_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iniciarVistas();
    }

    private void iniciarVistas() {
        inmuebleDetallesVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(InmuebleDetallesViewModel.class);
        etDireccion = (EditText)findViewById(R.id.etInmuebleDireccion);
        etAmbientes = (EditText)findViewById(R.id.etInmuebleAmbientes);
        etSuperficie = (EditText)findViewById(R.id.etInmuebleSuperficie);
        etLatitud = (EditText)findViewById(R.id.etInmuebleLatitud);
        etLongitud = (EditText)findViewById(R.id.etInmuebleLongitud);
        cbPublicado = (CheckBox)findViewById(R.id.cbInmueblePublicado);
        cbHabilitado = (CheckBox)findViewById(R.id.cbInmuebleHabilitado);
        tvEliModInmueble = (TextView)findViewById(R.id.tvMansajeInmuebleModificado);
        btnIEditar = (Button)findViewById(R.id.btnEditarInmueble);
        btnIEliminar = (Button)findViewById(R.id.btnEliminarInmueble);
        btnIEditModificarInmueble = (Button)findViewById(R.id.btnModificarInmueble);

        try{
            inmueble = (Inmueble) getIntent().getExtras().getSerializable("Inmueble");
            inmuebleDetallesVM.getMensajeModificadoMutableLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    tvEliModInmueble.setText(s);
                }
            });

            btnIEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inmuebleDetallesVM.botonHabilitadoInmueble(etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud, cbPublicado, cbHabilitado, btnIEditar, btnIEditModificarInmueble, btnIEliminar);
                }
            });

            btnIEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inmuebleDetallesVM.eliminarInmueble(inmueble.getInmuebleId(), tvEliModInmueble, btnIEliminar, btnIEditar);
                }
            });

            btnIEditModificarInmueble.setOnClickListener(new View.OnClickListener() {
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
                    inmuebleDetallesVM.actualizarInmueble(inmueble, tvEliModInmueble);
                }
            });
//---------------------- Aca se Cargan Los TextView---------------------------
            etDireccion.setText(inmueble.getDireccion());
            etAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
            etSuperficie.setText(String.valueOf(inmueble.getSuperficie()));
            etLatitud.setText(inmueble.getLatitud());
            etLongitud.setText(inmueble.getLongitud());
            if (inmueble.getEstaPublicado()==true) cbPublicado.setChecked(true);
            else cbPublicado.setChecked(false);
            if (inmueble.getEstaHabilitado()==true) cbHabilitado.setChecked(true);
            else cbHabilitado.setChecked(false);
//---------------------- Aca se Cargan Los TextView---------------------------

        }
        catch (Exception e){finish(); }
    }


    public static Intent getCallingIntent(Context context, Inmueble inmueble){
        Intent intent= new Intent(context, InmuebleDetallesActivity.class);
        intent.putExtra("Inmueble", inmueble);
        return intent;
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
