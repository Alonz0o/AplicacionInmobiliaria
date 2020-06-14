package com.example.aplicacioninmobiliaria.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Inmueble;
import com.example.aplicacioninmobiliaria.ui.inmueble.UsersAdapter;

import java.util.List;

public class InmuebleDatosFragment extends Fragment  {
    private ListView lvDatInmuebles;
    private UsersAdapter userAdapter;
    private Context context;
    private InmuebleDatosViewModel inmuebleDatosVM;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmueble_tab, container, false);
        context = getActivity();
        lvDatInmuebles = view.findViewById(R.id.lvDatosInmuebles);
        inmuebleDatosVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleDatosViewModel.class);
        inmuebleDatosVM.cargarListViewInmuebles();

        inmuebleDatosVM.getListInmueblesMutableLiveData().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                userAdapter = new UsersAdapter(context, inmuebles);
                lvDatInmuebles.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}


