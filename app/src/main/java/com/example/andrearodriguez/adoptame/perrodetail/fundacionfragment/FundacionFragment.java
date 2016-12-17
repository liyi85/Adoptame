package com.example.andrearodriguez.adoptame.perrodetail.fundacionfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrearodriguez.adoptame.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FundacionFragment extends Fragment {


    @Bind(R.id.txtNombreFundacion)
    TextView txtNombreFundacion;
    @Bind(R.id.txtTelefono)
    TextView txtTelefono;
    @Bind(R.id.txtEmail)
    TextView txtEmail;
    @Bind(R.id.txtDireccion)
    TextView txtDireccion;
    @Bind(R.id.txtRepresentante)
    TextView txtRepresentante;

    public FundacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_perro2, container, false);
        ButterKnife.bind(this, view);

        String fundacion = getActivity().getIntent().getExtras().getString("fundacionPerro");
        String email = getActivity().getIntent().getExtras().getString("emailPerro");

        txtNombreFundacion.setText(fundacion);
        txtEmail.setText(email);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
