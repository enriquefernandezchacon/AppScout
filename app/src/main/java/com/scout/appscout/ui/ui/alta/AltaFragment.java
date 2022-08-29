package com.scout.appscout.ui.ui.alta;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.scout.appscout.R;

public class AltaFragment extends Fragment {

    private AltaViewModel mViewModel;
    private View view;
    private Button botonEnviar;
    private String dirección = "tesoreria.scout.alpha@gmail.com", asunto, contenido;
    private EditText etNombreNino, etDniNino, etFechaNacimientoNinio, etNombreTutor, etDniTutor, etTelefonoTutor;


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
                enviarMensaje();
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

    public void enviarMensaje() {
        asunto = "Alta " + etNombreNino.getText().toString();

        contenido = "DATOS NIÑO:\n"+
                "Nombre y Apellidos: " + etNombreNino.getText().toString() + "\n" +
                "DNI: " + etDniNino.getText().toString() + "\n"+
                "Fecha nacimiento: " + etFechaNacimientoNinio.getText().toString() + "\n" +
                "\n\n DATOS TUTOR:\n" +
                "Nombre y Apellidos: " + etNombreTutor.getText().toString() + "\n" +
                "DNI: " + etDniTutor.getText().toString() + "\n"+
                "Teléfono: " + etTelefonoTutor.getText().toString();


        Intent enviar = new Intent(Intent.ACTION_SENDTO);
        enviar.setData(Uri.parse("mailto:"));
        enviar.putExtra(Intent.EXTRA_EMAIL,new String[]{dirección});
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