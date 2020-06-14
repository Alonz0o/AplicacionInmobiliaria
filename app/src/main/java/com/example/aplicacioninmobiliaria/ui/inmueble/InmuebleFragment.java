package com.example.aplicacioninmobiliaria.ui.inmueble;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.model.Inmueble;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class InmuebleFragment extends Fragment {
    private ListView lvInmuebles;
    private InmuebleViewModel inmuebleVM;
    private UsersAdapter userAdapter;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmuble, container, false);
        context = getActivity();
        swipeRefreshLayout = view.findViewById(R.id.refresco);
        inmuebleVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        lvInmuebles = view.findViewById(R.id.lvInmuebles);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inmuebleVM.cargarListViewInmuebles();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },300);
            }
        });
        FloatingActionButton agregarInmueble = view.findViewById(R.id.btnAgregarInmueble);

        inmuebleVM.getListInmueblesMutableLiveData().observe(getActivity(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {

                userAdapter = new UsersAdapter(context, inmuebles);
                lvInmuebles.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }
        });

        agregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Vas a Crear un Inmueble", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent= new Intent(getActivity(), InmuebleAgregarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        lvInmuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Inmueble inmueble = (Inmueble) userAdapter.getItem(position);
                startActivity(InmuebleDetallesActivity.getCallingIntent(context, inmueble));
            }
        });
        inmuebleVM.cargarListViewInmuebles();

        return view;
    }
}
