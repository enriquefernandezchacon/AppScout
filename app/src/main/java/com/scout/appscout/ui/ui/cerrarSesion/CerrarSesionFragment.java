package com.scout.appscout.ui.ui.cerrarSesion;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scout.appscout.R;
import com.scout.appscout.ui.DatosSesion;
import com.scout.appscout.ui.NavigationActivity;

public class CerrarSesionFragment extends Fragment {
    private DatosSesion datos = new DatosSesion();

    public CerrarSesionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        datos.limpiarPreferences(getContext());
        return inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
    }
}