package com.example.aplicacioninmobiliaria.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.aplicacioninmobiliaria.R;
import com.example.aplicacioninmobiliaria.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {
    private HomeViewModel homeVM;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);

        ViewPager viewPager = view.findViewById(R.id.view_datosDelPropietario);
        TabLayout tabs = view.findViewById(R.id.tabsInmobiliaria);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        return view;
    }


}
