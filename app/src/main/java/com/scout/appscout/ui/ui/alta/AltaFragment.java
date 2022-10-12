package com.scout.appscout.ui.ui.alta;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.scout.appscout.R;

public class AltaFragment extends Fragment {

    private AltaViewModel mViewModel;
    private View view;
    private Button botonEnviar;
    private String dirección = "tesoreria.scout.alpha@gmail.com", asunto, contenido;
    private EditText etNombreNino, etDniNino, etFechaNacimientoNinio, etNombreTutor, etDniTutor, etTelefonoTutor;
    private String nombreNino, dniNino, fechaNacimientoNino, nombreTutor, dniTutor, telefonoTutor;
    private boolean enviar;


    public static AltaFragment newInstance() {
        return new AltaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alta, container, false);

        instanciarObjetos();
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlDatos();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AltaViewModel.class);
        // TODO: Use the ViewModel
    }

    private void controlDatos() {
        enviar = true;

        obtenerDatos();

        if (nombreNino.isEmpty()) {
            etNombreNino.requestFocus();
            etNombreNino.setError("Se tiene que completar el nombre");
            enviar = false;
        }

        if (fechaNacimientoNino.isEmpty()) {
            etFechaNacimientoNinio.requestFocus();
            etFechaNacimientoNinio.setError("Se tiene que completar la fecha de nacimiento");
            enviar = false;
        }

        if (!fechaNacimientoNino.isEmpty()) {
            //TODO implementar control de que la fecha esta bien insertada
        }

        if (nombreTutor.isEmpty()) {
            etNombreTutor.requestFocus();
            etNombreTutor.setError("Se tiene que completar el nombre");
            enviar = false;
        }

        if (dniTutor.isEmpty()) {
            etDniTutor.requestFocus();
            etDniTutor.setError("Se tiene que completar el DNI");
            enviar = false;
        }

        if (telefonoTutor.isEmpty()) {
            etTelefonoTutor.requestFocus();
            etTelefonoTutor.setError("Se tiene que completar el telefono");
            enviar = false;
        }

        if (!telefonoTutor.isEmpty() && telefonoTutor.length() != 9) {
            etTelefonoTutor.requestFocus();
            etTelefonoTutor.setError("Telefono incorrecto");
            enviar = false;
        }

        if (!dniNino.isEmpty()) {
            comprobarDNI(etDniNino);
        }

        if (!dniTutor.isEmpty()) {
            comprobarDNI(etDniTutor);
        }

        if (enviar) {
            // Se rellenan los datos que se van a enviar en el correo
            rellenarDatos();
            // Se envia el mensaje con los datos rellenos
            enviarMensaje();
        }
    }

    private void obtenerDatos() {
        nombreNino = etNombreNino.getText().toString();
        dniNino = etDniNino.getText().toString();
        fechaNacimientoNino = etFechaNacimientoNinio.getText().toString();
        nombreTutor = etNombreTutor.getText().toString();
        dniTutor = etDniTutor.getText().toString();
        telefonoTutor = etTelefonoTutor.getText().toString();
    }

    private void comprobarDNI(EditText dni) {
        String letraMayuscula = "";
        if (dni.getText().toString().length() != 9) {
            dni.requestFocus();
            dni.setError("DNI incorrecto");
            enviar = false;
        } else {
            letraMayuscula = (dni.getText().toString().substring(8)).toUpperCase();

            if (soloNumeros(dni.getText().toString()) != true && !letraDNI(dni.getText().toString()).equals(letraMayuscula)) {
                dni.requestFocus();
                dni.setError("DNI incorrecto");
                enviar = false;
            }
        }
    }

    private boolean soloNumeros(String dni) {
        int i, j = 0;
        String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
        String miDNI = ""; // Guardamos en una cadena los números para después calcular la letra
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < dni.length() - 1; i++) {
            numero = dni.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }

        if (miDNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    private String letraDNI(String dni) {
        // El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella
        // pasar miNumero a integer
        int miDNI = Integer.parseInt(dni.substring(0, 8));
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDNI % 23;
        miLetra = asignacionLetra[resto];
        return miLetra;
    }

    private void rellenarDatos() {
        asunto = "Alta " + etNombreNino.getText().toString();

        contenido = "DATOS NIÑO:\n" +
                "Nombre y Apellidos: " + etNombreNino.getText().toString() + "\n" +
                "DNI: " + etDniNino.getText().toString() + "\n" +
                "Fecha nacimiento: " + etFechaNacimientoNinio.getText().toString() + "\n" +
                "\n\n DATOS TUTOR:\n" +
                "Nombre y Apellidos: " + etNombreTutor.getText().toString() + "\n" +
                "DNI: " + etDniTutor.getText().toString() + "\n" +
                "Teléfono: " + etTelefonoTutor.getText().toString();
    }

    private void enviarMensaje() {
        Intent enviar = new Intent(Intent.ACTION_SENDTO);
        enviar.setData(Uri.parse("mailto:"));
        enviar.putExtra(Intent.EXTRA_EMAIL, new String[]{dirección});
        enviar.putExtra(Intent.EXTRA_SUBJECT, asunto);
        enviar.putExtra(Intent.EXTRA_TEXT, contenido);
        startActivity(enviar);
    }

    private void instanciarObjetos() {
        botonEnviar = view.findViewById(R.id.botonEnviar);
        etNombreNino = view.findViewById(R.id.eTnombreApellidoNino);
        etDniNino = view.findViewById(R.id.etDniNino);
        etFechaNacimientoNinio = view.findViewById(R.id.etFechaNacimientoNino);
        etNombreTutor = view.findViewById(R.id.eTnombreApellidoTutor);
        etTelefonoTutor = view.findViewById(R.id.etTelefonoTutor);
        etDniTutor = view.findViewById(R.id.etDniTutor);
    }

}