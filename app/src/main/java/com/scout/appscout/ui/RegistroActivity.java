package com.scout.appscout.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.scout.appscout.R;
import com.scout.appscout.common.FirebaseAcceso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etNombre, etApellidos, etClave, etRepClave;
    private Button btRegistro;
    private boolean datosOk = true;
    private String email, nombre, apellidos, clave, repClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().hide();

        // Instanciar objetos del front
        findView();
        events();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRegistro) {
            registro();
        }
    }

    private void registro() {
        email = etEmail.getText().toString().trim();
        //nombre = etNombre.getText().toString().trim();
        //apellidos = etApellidos.getText().toString().trim();
        clave = etClave.getText().toString().trim();
        repClave = etRepClave.getText().toString().trim();
        //TODO: insertar campos texto en resources
        if (email.isEmpty()) {
            datosOk = false;
            etEmail.setError(getString(R.string.requiere_email));
            //} else if (nombre.isEmpty()) {
            //datosOk = false;
            //etNombre.setError("Se requiere el nombre");
            // break;
            //} //else if (apellidos.isEmpty()) {
            //datosOk = false;
            //etApellidos.setError("Se requieren los apellidos");
            //break;
        } else if (!validarContraseña()) {
            datosOk = false;
        } else if (!validarEmail(email)) {
            datosOk = false;
            etEmail.setError(getString(R.string.email_no_valido));
        } else {
            if (datosOk) {
                FirebaseAcceso firebaseAcceso = new FirebaseAcceso(0, email, clave, this, etEmail, etClave);
            }
        }
    }

    /**
     * Valida que la contraseña introducida por el usuario tenga un mínimo de caracteres y este rellena
     * @return true si la contraseña esta correcta, false si no es correcta o no está rellena
     */
    private boolean validarContraseña() {
        if (!clave.isEmpty()) {
            if (clave.length() < 6) {
                //etClave.setError(getResources().getString(R.string.clave_min_caracteres));
                return false;
            } else {
                if (clave.equals(repClave)) {
                    return true;
                } else {
                    //etClave.setError(getResources().getString(R.string.clave_no_coinciden));
                    return false;
                }
            }
        } else {
            etClave.setError(getString(R.string.requiere_contrasena));
            return false;
        }
    }

    /**
     * Se comprueba si el correo tiene una composición correcta.
     * @param email correo del usuario
     * @return true si el correo tiene una composición correcta, false si la composición del correo es incorrecta
     */
    private boolean validarEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void findView() {
        etEmail = findViewById(R.id.editTextRegistoEmail);
        //etNombre = findViewById(R.id.editTextRegistroNombre);
        //etApellidos = findViewById(R.id.editTextRegistroApellidos);
        etClave = findViewById(R.id.editTextRegistroContrasena);
        etRepClave = findViewById(R.id.editTextRegistroRepContrasena);
        btRegistro = findViewById(R.id.buttonRegistro);
    }

    private void events() {
        btRegistro.setOnClickListener(this);
    }






}
