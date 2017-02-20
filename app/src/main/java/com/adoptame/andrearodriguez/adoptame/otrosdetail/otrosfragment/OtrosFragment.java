package com.adoptame.andrearodriguez.adoptame.otrosdetail.otrosfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adoptame.andrearodriguez.adoptame.R;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 2/17/17.
 */
public class OtrosFragment extends Fragment {

    @Bind(R.id.txtNombre)
    TextView txtNombre;
    @Bind(R.id.imgVarios)
    ImageView imgVarios;
    @Bind(R.id.txtVacunacion)
    TextView txtVacunacion;
    @Bind(R.id.txtEsterilizacion)
    TextView txtEsterilizacion;
    @Bind(R.id.txtDiscapacidad)
    TextView txtDiscapacidad;
    @Bind(R.id.txtEdad)
    TextView txtEdad;
    @Bind(R.id.txtEspecie)
    TextView txtEspecie;
    @Bind(R.id.txtSexo)
    TextView txtSexo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public OtrosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_otros1, container, false);

        ButterKnife.bind(this, view);

        String nombre = getActivity().getIntent().getExtras().getString("nombreOtro");
        String edad = getActivity().getIntent().getExtras().getString("edadOtro");
        String sexo = getActivity().getIntent().getExtras().getString("sexoOtro");
        String vacunado = getActivity().getIntent().getExtras().getString("vacunaOtro");
        String esterilizado = getActivity().getIntent().getExtras().getString("esterilizadoOtro");
        String foto = getActivity().getIntent().getExtras().getString("fotoOtro");
        String discapacitado = getActivity().getIntent().getExtras().getString("discapacitadoOtro");

        Toast.makeText(getContext(), "Cargando la foto, por favor espere", Toast.LENGTH_SHORT).show();
        String foto2 = (foto + ".jpg");
        Glide.with(getActivity()).load(foto2).centerCrop().into(imgVarios);

        nombre = nombre.toUpperCase();
        txtNombre.setText(nombre);
        txtEdad.setText(edad);
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
