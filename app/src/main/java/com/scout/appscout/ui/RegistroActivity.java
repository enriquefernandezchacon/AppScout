package com.scout.appscout.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        // Instanciar front
        findView();
        events();
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
            etEmail.setError("Se requiere el email");
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
            etEmail.setError("Email no válido");
        } else {
            if (datosOk) {
                FirebaseAcceso firebaseAcceso = new FirebaseAcceso(0, email, clave, this, etEmail, etClave);
            }
        }

    }

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
            etClave.setError("Se requiere la contraseña");
            return false;
        }

    }//FIN VALIDAR CONTRASEÑA

    private boolean validarEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
