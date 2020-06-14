package com.example.aplicacioninmobiliaria.ui.main;

import androidx.fragment.app.Fragment;

import com.example.aplicacioninmobiliaria.ui.home.InmuebleDatosFragment;
import com.example.aplicacioninmobiliaria.ui.home.PropietarioDatosFragment;

public class PlaceholderFragment extends Fragment {

    public static Fragment newInstance(int index) {
        Fragment fragment = new Fragment();
        switch (index){
            case 1: fragment = new PropietarioDatosFragment(); break;
            case 2: fragment = new InmuebleDatosFragment(); break;
        }
        return fragment;
    }

}