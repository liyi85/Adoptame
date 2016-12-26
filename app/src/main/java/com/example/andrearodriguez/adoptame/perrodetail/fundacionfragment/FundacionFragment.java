package com.example.andrearodriguez.adoptame.perrodetail.fundacionfragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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



    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public FundacionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_perro2, container, false);
        ButterKnife.bind(this, view);


        final String email = getActivity().getIntent().getExtras().getString("emailPerro");

        Firebase ref = new Firebase("https://fundacionesapp.firebaseIO.com/Fundaciones");


        Query query = ref.orderByChild("email").equalTo(email);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Fundacion fundacion = dataSnapshot.getValue(Fundacion.class);

                String nombreFundacion = fundacion.getNombreFundacion();
                String telefono = fundacion.getTelefono();
                String direccion = fundacion.getDireccion();
                String personaContacto = fundacion.getPersonaContacto();

                //Toast.makeText(getContext(), " " + nombreFundacion + " " + telefono + " " + direccion + " " + personaContacto, Toast.LENGTH_LONG).show();

                nombreFundacion = nombreFundacion.toUpperCase();
                txtNombreFundacion.setText(nombreFundacion);
                txtTelefono.setText(telefono);
                txtDireccion.setText(direccion);
                txtRepresentante.setText(personaContacto);
                txtEmail.setText(email);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.linealPhone)
    public void onClickPhone() {
        String phoneNumber = txtTelefono.getText().toString();

        Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(intentPhone);

    }

    @OnClick(R.id.linealEmail)
    public void onClickEmail() {
        String email = txtEmail.getText().toString();
        if (email != null) {
            Intent intenEmail = new Intent(Intent.ACTION_SEND);
            intenEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intenEmail.setType("message/rfc822");
            startActivity(Intent.createChooser(intenEmail, "Eliga su cliente de Email :"));
        }

    }
}
