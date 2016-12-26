package com.adoptame.andrearodriguez.adoptame.perrodetail.perrofragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.adoptame.andrearodriguez.adoptame.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerroFragment extends Fragment {


    @Bind(R.id.txtNombre)
    TextView txtNombre;
    @Bind(R.id.imgPerro)
    ImageView imgPerro;
    @Bind(R.id.txtEdad)
    TextView txtEdad;
    @Bind(R.id.txtTamano)
    TextView txtTamano;
    @Bind(R.id.txtSexo)
    TextView txtSexo;
    @Bind(R.id.txtVacunacion)
    TextView txtVacunacion;
    @Bind(R.id.txtEsterilizacion)
    TextView txtEsterilizacion;
    @Bind(R.id.txtDiscapacidad)
    TextView txtDiscapacidad;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public PerroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_perro1, container, false);
        ButterKnife.bind(this, view);


        String nombre = getActivity().getIntent().getExtras().getString("nombrePerro");
        String edad = getActivity().getIntent().getExtras().getString("edadPerro");
        String tamano = getActivity().getIntent().getExtras().getString("tamanoPerro");
        String sexo = getActivity().getIntent().getExtras().getString("sexoPerro");
        String vacunado = getActivity().getIntent().getExtras().getString("vacunaPerro");
        String esterilizado = getActivity().getIntent().getExtras().getString("esterilizadoPerro");
        String foto = getActivity().getIntent().getExtras().getString("fotoPerro");
        String discapacitado = getActivity().getIntent().getExtras().getString("discapacitadoPerro");

        Toast.makeText(getContext(), "Cargando la foto, por favor espere", Toast.LENGTH_SHORT).show();
        String foto2 = (foto + ".jpg");
        Glide.with(getActivity()).load(foto2).centerCrop().into(imgPerro);


        nombre = nombre.toUpperCase();
        txtNombre.setText(nombre);
        txtEdad.setText(edad);
        txtTamano.setText(tamano);
        txtSexo.setText(sexo);
        txtVacunacion.setText(vacunado);
        txtEsterilizacion.setText(esterilizado);
        txtDiscapacidad.setText(discapacitado);

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
