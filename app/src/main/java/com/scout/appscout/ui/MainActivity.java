package com.scout.appscout.ui;

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

    private static Usuario usuario;
    //private Intent
    private static Boolean login = false;
    private EditText etEmail, etPassword;
    private Button btLogin;
    private DatosSesion datos = new DatosSesion();

    public static boolean isLogin() {
        return MainActivity.login;
    }

    public static void setLogin(boolean login) {
        MainActivity.login = login;
    }

    public static void setUsuario(Usuario usuario) {
        MainActivity.usuario = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Instanciar objetos del layout
        findView();
        // Añadir eventos a los objetos del layout
        events();
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
//        if (id == R.id.action_registro) {
//            Intent i = new Intent(this, RegistroActivity.class);
//            startActivity(i);
//            return true;
//        }

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

    @Override
    protected void onStart() {
        super.onStart();
        comprobarInicio();
    }

    /**
     * Se comprueba que estén los datos de correo y contraseña rellenos para poder proceder
     * a comprobar los datos de inicio de sesión en firebase.
     */
    private void iniciarSesion() {
        boolean datosOk = true;
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError(getString(R.string.requiere_email));
            datosOk = false;
        } else if (password.isEmpty()) {
            etPassword.setError(getString(R.string.requiere_contrasena));
            datosOk = false;
        }

        if (datosOk) {
            FirebaseAcceso firebaseAcceso = new FirebaseAcceso(2, email, password, this, etEmail, etPassword);
        }
    }

    /**
     * Se comprueba si el usuario ha dejado la sesión abierta para evitar que tenga que volver a loguearse, en el caso
     * de que haya dejado la sesión anterior abierta se inicia el proceso de login con firebase
     */
    private void comprobarInicio() {
        datos.cargarPreferencias(this);
        if (login) {
            FirebaseAcceso firebaseAcceso = new FirebaseAcceso(1, usuario.getEmail(), usuario.getPassword(), this);
        }
    }
}
