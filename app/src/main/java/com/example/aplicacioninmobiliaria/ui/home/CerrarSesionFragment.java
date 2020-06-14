package com.example.aplicacioninmobiliaria.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.aplicacioninmobiliaria.R;


public class CerrarSesionFragment extends Fragment {

    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cerrarsesion, container, false);
        root = view;
        cerrar();
        return view;
    }

    private void cerrar(){

        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.hotelicon)
                .setTitle("Cerrar Aplicacion")
                .setCancelable(false)
                .setMessage("Estas seguro de esto?")
                .setPositiveButton("Si, Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })

                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Navigation.findNavController(root).navigate(R.id.nav_home, null);
                    }
                })
                .show();
    }

}