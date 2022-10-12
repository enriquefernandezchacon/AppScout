package com.scout.appscout.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.scout.appscout.common.Usuario;

public class DatosSesion {

    private Usuario usuario;

    public DatosSesion() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void guardarPreferencias(Context context, Usuario usuario) {
        SharedPreferences preferences = context.getSharedPreferences("lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login", true);
        //editor.putInt("id",usuario.get(0).getId());
        editor.putString("correo", usuario.getEmail());
        //editor.putString("nombre",usuario.get(0).getNombre());
        //editor.putString("apellidos",usuario.get(0).getApellidos());
        editor.putString("clave", usuario.getPassword());
        editor.commit();
    }

    public void cargarPreferencias(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("lista", Context.MODE_PRIVATE);
        MainActivity.setLogin(preferences.getBoolean("login", false));
        if (MainActivity.isLogin()) {
            //int id = preferences.getInt("id",0);
            String correo = preferences.getString("correo", "");
            //String nombre = preferences.getString("nombre","");
            //String apellidos = preferences.getString("apellidos","");
            String clave = preferences.getString("clave", "");
            //usuario.add(new Usuario(id,correo,nombre,apellidos,clave));
            usuario = new Usuario(correo, clave);
            MainActivity.setUsuario(usuario);
        }

        //ActualizarEstado(LoginActivity.isLogin(), getApplicationContext());

    }

    public void limpiarPreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences("lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean("login",false);
        editor.commit();
        usuario = new Usuario("","");
        MainActivity.setUsuario(usuario);
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

//    public static void ActualizarEstado(boolean login, final Context context) {
//        if (login) {
//            tvNombreUsu.setText(usuarios.get(0).getNombre());
//            btLogin.setText(R.string.perfil);
//            btRegistrar.setText(R.string.cerrar_sesion);
//
//            btLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, PanelActivity.class);
//                    intent.putExtra("usuarios", usuarios);
//                    context.startActivity(intent);
//                }
//            });
//
//            btRegistrar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    usuarios.clear();
//                    LoginActivity.setLogin(false);
//                    ActualizarEstado(LoginActivity.isLogin(), context);
//
//                }
//            });
//        } else {
//            tvNombreUsu.setText(R.string.inicie_sesion);
//            btLogin.setText(R.string.iniciar_sesion);
//            btRegistrar.setText(R.string.registrar);
//
//            btLogin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    context.startActivity(intent);
//                }
//            });
//
//            btRegistrar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    intent = new Intent(context, RegistroActivity.class);
//                    context.startActivity(intent);
//                }
//            });
//        }
//    }
}
