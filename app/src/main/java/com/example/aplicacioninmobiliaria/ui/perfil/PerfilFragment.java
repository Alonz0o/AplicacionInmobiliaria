package com.example.aplicacioninmobiliaria.ui.perfil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Propietario;
import com.example.aplicacioninmobiliaria.ui.home.MainActivityViewModel;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilVM;
    private EditText etPDn, etPApe, etPNom, etPTel, etPCorreo, etPContra;
    private TextView tvPEditar;
    private Button btnPEdit, btnPEditAceptar;
    private Propietario propietarioVisto = null;

    private MainActivityViewModel mainVM;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        perfilVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

        mainVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MainActivityViewModel.class);

        tvPEditar = view.findViewById(R.id.tvPEditar);
        etPDn = view.findViewById(R.id.etPDni);
        etPApe = view.findViewById(R.id.etPApellido);
        etPNom = view.findViewById(R.id.etPNnombre);
        etPTel = view.findViewById(R.id.etPTelefono);
        etPCorreo = view.findViewById(R.id.etPEmail);
        etPContra = view.findViewById(R.id.etPContraseña);
        btnPEdit = view.findViewById(R.id.BtnEditarPropietario);
        btnPEditAceptar = view.findViewById(R.id.BtnEditarAceptarPropietario);

        btnPEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilVM.botonHabilitado(etPDn, etPApe, etPNom, etPTel, etPCorreo, etPContra, btnPEdit, btnPEditAceptar);
            }
        });
        btnPEditAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                propietarioVisto.setDni(Integer.parseInt(etPDn.getText().toString()));
                propietarioVisto.setApellido(etPApe.getText().toString());
                propietarioVisto.setNombre(etPNom.getText().toString());
                propietarioVisto.setTelefono(Integer.parseInt(etPTel.getText().toString()));
                propietarioVisto.setEmail(etPCorreo.getText().toString());
                propietarioVisto.setClave(etPContra.getText().toString());
                perfilVM.actualizarPropietario(propietarioVisto);
                //mainVM.cambiarUser();
            }
        });

        perfilVM.getPropietarioMutableLiveData().observe(getActivity(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                propietarioVisto = propietario;
                fijarDatos(propietario);

            }
        });

        perfilVM.getMutableLiveDataPerfilModificado().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvPEditar.setText(s);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignorar
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignorar
            }

            @Override
            public void afterTextChanged(Editable s) {
                perfilVM.datosCambian(etPDn, etPApe, etPNom, etPTel, etPCorreo, etPContra, btnPEdit);
            }
        };
        etPDn.addTextChangedListener(afterTextChangedListener);
        etPApe.addTextChangedListener(afterTextChangedListener);
        etPNom.addTextChangedListener(afterTextChangedListener);
        etPTel.addTextChangedListener(afterTextChangedListener);
        etPCorreo.addTextChangedListener(afterTextChangedListener);
        etPContra.addTextChangedListener(afterTextChangedListener);
        perfilVM.leerPropietario();

        return view;
    }

    public void fijarDatos(Propietario propietarioLogeado){

        etPDn.setText(String.valueOf(propietarioLogeado.getDni()));
        etPApe.setText(String.valueOf(propietarioLogeado.getApellido()));
        etPNom.setText(String.valueOf(propietarioLogeado.getNombre()));
        etPTel.setText(String.valueOf(propietarioLogeado.getTelefono()));
        etPCorreo.setText(String.valueOf(propietarioLogeado.getEmail()));
        etPContra.setText(String.valueOf(propietarioLogeado.getClave()));
    }

}

