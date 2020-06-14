package com.example.aplicacioninmobiliaria.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacioninmobiliaria.R;

public class PropietarioDatosFragment extends Fragment {
    private PropietarioDatosViewModel propietarioDatosVM;
    private EditText etDni, etApellido, etNombre, etTelefono, etCorreo, etContrasena;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propietario_tab, container, false);
        propietarioDatosVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PropietarioDatosViewModel.class);

        etDni = view.findViewById(R.id.etDatosni);
        etApellido = view.findViewById(R.id.etDatosApellido);
        etNombre = view.findViewById(R.id.etDatosNombre);
        etTelefono = view.findViewById(R.id.etDatosTelefono);
        etCorreo = view.findViewById(R.id.etDatosEmail);
        etContrasena = view.findViewById(R.id.etDatosContraseña);

        propietarioDatosVM.leerPropietario(etDni, etApellido, etNombre, etTelefono, etCorreo, etContrasena);

        return view;
    }
}

