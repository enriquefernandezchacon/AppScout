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
import com.scout.appscout.ui.NavigationActivity;

public class FirebaseAcceso {

    private FirebaseAuth auth;
    private int acceso;
    private String email, clave;
    private Context context;
    private EditText etEmail, etPassword;

    // Constructor por defecto
    public FirebaseAcceso() {

    }

    public FirebaseAcceso(int acceso, String email, String clave, Context context, EditText etEmail, EditText etPassword) {
        this.acceso = acceso;
        this.email = email;
        this.clave = clave;
        this.context = context;
        this.etEmail = etEmail;
        this.etPassword = etPassword;

        auth = FirebaseAuth.getInstance();

        // Si el control de acceso es 0 se realizará el registro, si por el contrario es uno se realizará el inicio de sesión del usuario
        if (acceso == 0) {
            registro();
        } else if (acceso == 1) {
            inicioSesion();
        }
    }

    private void registro() {
        auth.createUserWithEmailAndPassword(email, clave)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(context, "Usuario creado.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error al crear el usuario.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void inicioSesion() {
        auth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(context, NavigationActivity.class);
                            context.startActivity(i);
                            ((Activity)(context)).finish();
                        } else {
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            etEmail.setText("");
                            etPassword.setText("");
                            etEmail.requestFocus();
                        }
                    }
                });
    }
}
