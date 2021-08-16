package com.scout.appscout.LiveData;

import androidx.lifecycle.ViewModel;

import com.scout.appscout.common.Usuario;

public class LiveData extends ViewModel {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        LiveData.usuario = usuario;
    }
}
