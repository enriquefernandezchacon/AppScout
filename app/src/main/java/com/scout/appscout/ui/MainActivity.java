package com.scout.appscout.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.scout.appscout.R;
import com.scout.appscout.common.FirebaseAcceso;
import com.scout.appscout.common.Usuario;

// TODO: REVISAR CLASE
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private Button btLogin;
    private Usuario usuario;
    //private Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Instanciar objetos del layout
        findView();
        // Añadir eventos a los objetos del layout
        events();
        // Comprobar si el usuario tiene la sesión abierta
        comprobarInicio();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.panel_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_registro) {
            Intent i = new Intent(this, RegistroActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Se instancian los objetos del layout
     */
    private void findView() {
        etEmail = findViewById(R.id.editTextloginEmail);
        etPassword = findViewById(R.id.editTextLoginPassword);
        btLogin = findViewById(R.id.buttonLogin);
    }

    /**
     * Se añade al objeto btLogin el evento de OnClickListener()
     */
    private void events() {
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin) {
            iniciarSesion();
        }
    }

    /**
     * Se comprueba que estén los datos de correo y contraseña rellenos para poder proceder
     * a comprobar los datos de inicio de sesión en firebase.
     */
    private void iniciarSesion() {
        boolean datosOk = true;
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Intent i = new Intent(this, NavigationActivity.class);
        this.startActivity(i);
        ((Activity)(this)).finish();
//DESBLOQUEAR MAS TARDE
//        if (email.isEmpty()) {
//            etEmail.setError(getString(R.string.requiere_email));
//            datosOk = false;
//        } else if (password.isEmpty()) {
//            etPassword.setError(getString(R.string.requiere_contrasena));
//            datosOk = false;
//        }
//
//        if (datosOk) {
//            FirebaseAcceso firebaseAcceso = new FirebaseAcceso(1, email, password, this, etEmail, etPassword);
//        }
    }

    /**
     * Se comprueba si el usuario ha dejado la sesión abierta para evitar que tenga que volver a loguearse
     */
    private void comprobarInicio() {
        // TODO: Comprobar si el usuario tiene la sesion iniciada al entrar
    }
}
