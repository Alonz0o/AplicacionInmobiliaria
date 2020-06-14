package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Inmueble;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<Inmueble> {


    public UsersAdapter(Context context, List<Inmueble> inmuebleList) {
        super(context, 0, inmuebleList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Inmueble inmueble = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fila_inmuebleslv, parent, false);
        }
        TextView tvDireccion = convertView.findViewById(R.id.tvDomicilio);
        TextView tvDueño = convertView.findViewById(R.id.tvDueño);
        tvDireccion.setText(inmueble.getDireccion());
        tvDueño.setText(inmueble.getDuenio().getApellido() + inmueble.getDuenio().getNombre());

        return convertView;
    }
}