package com.scout.appscout.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scout.appscout.R;
import com.scout.appscout.ui.DatosSesion;
import com.scout.appscout.ui.MainActivity;
import com.scout.appscout.ui.NavigationActivity;

public class FirebaseAcceso {

    private FirebaseAuth auth;
    private int acceso;
    private String email, clave;
    private Context context;
    private EditText etEmail, etPassword;
    private Usuario usuario;
    private DatosSesion datos = new DatosSesion();

    // Constructor por defecto
    public FirebaseAcceso() {

    }

    /**
     * Constructor que recoge los datos del usuario tanto para el registro como para el inicio de sesión.
     *
     * @param acceso     0 - registro, 1 - inicio de sesión
     * @param email      correo que ha insertado el usuario
     * @param clave      contraseña que ha insertado el usuario
     * @param context    contexto de la clase con la que se esta llamando
     * @param etEmail    objeto de layout que almacena el correo
     * @param etPassword objeto del layout que almacena la contaseña
     */
    public FirebaseAcceso(int acceso, String email, String clave, Context context, EditText etEmail, EditText etPassword) {
        this.acceso = acceso;
        this.email = email;
        this.clave = clave;
        this.context = context;
        this.etEmail = etEmail;
        this.etPassword = etPassword;

        auth = FirebaseAuth.getInstance();

        controlAcceso();
    }

    public FirebaseAcceso(int acceso, String email, String clave, Context context) {
        this.acceso = acceso;
        this.email = email;
        this.clave = clave;
        this.context = context;

        auth = FirebaseAuth.getInstance();

        controlAcceso();
    }

    private void controlAcceso() {
        // Si el control de acceso es 0 se realizará el registro, si por el contrario es uno se realizará el inicio de sesión del usuario
        if (acceso == 0) {
            registro();
        } else if (acceso == 1) {
            inicioSesionAlmacenado();
        } else if (acceso == 2) {
            inicioSesionNuevo();
        }
    }

    /**
     * Se accede a firebase auth para realizar el registro del usuario con correo y contraseña
     */
    private void registro() {
        auth.createUserWithEmailAndPassword(email, clave)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(context, context.getString(R.string.usuario_creado),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, context.getString(R.string.error_crear_usuario),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Se inicia sesión con los datos guardados de la ultima sesión en SharedPreferences
     */
    private void inicioSesionAlmacenado() {
        auth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(context, NavigationActivity.class);
                            context.startActivity(i);
                            ((Activity) (context)).finish();
                        } else {
                            Toast.makeText(context, context.getString(R.string.autenticacion_fallida),
                                    Toast.LENGTH_SHORT).show();
                            etEmail.setText("");
                            etPassword.setText("");
                            etEmail.requestFocus();
                        }
                    }
                });
    }

    /**
     * Se accede a firebase auth para realizar el inicio de sesion del usuario (si existe) con correo y contraseña no guardado en SharedPreferences
     */
    private void inicioSesionNuevo() {
        auth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(context, NavigationActivity.class);
                            usuario = new Usuario(email,clave);
                            datos.guardarPreferencias(context,usuario);
                            context.startActivity(i);
                            ((Activity) (context)).finish();
                        } else {
                            Toast.makeText(context, context.getString(R.string.autenticacion_fallida),
                                    Toast.LENGTH_SHORT).show();
                            etEmail.setText("");
                            etPassword.setText("");
                            etEmail.requestFocus();
                        }
                    }
                });
    }
}
