package com.adoptame.andrearodriguez.adoptame.gatolist.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.adoptame.andrearodriguez.adoptame.BebeAdoptaApp;
import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.gatodetail.ui.DetailGatoActivity;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListPresenter;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter.GatoListAdapter;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter.OnItemClickListener;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GatoListFragment extends Fragment implements GatoListView, OnItemClickListener {



    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewGato)
    RecyclerView recyclerViewGato;
    @Bind(R.id.progresBarAddGato)
    ProgressBar progresBarAddGato;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    GatoListAdapter adapter;

    @Inject
    GatoListPresenter presenter;

    public GatoListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
    }

    private void setupInjection() {
        BebeAdoptaApp app = (BebeAdoptaApp) getActivity().getApplication();
        app.getGatoListComponent(this, this, this).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gato_list, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return view;
    }

    private void setupRecyclerView() {
        recyclerViewGato.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewGato.setItemAnimator(new DefaultItemAnimator());
        recyclerViewGato.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_title_gatos);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewGato.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewGato.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddGato.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddGato.setVisibility(View.GONE);

    }

    @Override
    public void addGato(Bebe bebe) {
        adapter.addGato(bebe);
    }

    @Override
    public void removeGato(Bebe bebe) {
        adapter.removeGato(bebe);
    }

    @Override
    public void onGatoError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onGatoClick(Bebe bebe) {
        String nombre = bebe.getNombre();
        String edad = bebe.getEdad();
        String tamano = bebe.getTamaño();
        String sexo = bebe.getSexo();
        String vacunado = bebe.getVacunacion();
        String esterilizado = bebe.getEsterilizacion();
        String foto = bebe.getUrl();
        String fundacion = bebe.getFundacion();
        String email = bebe.getEmail();
        String discapacitado = bebe.getDiscapacitado();


        Intent intent = new Intent(getActivity(), DetailGatoActivity.class);

        intent.putExtra("nombreGato", nombre);
        intent.putExtra("edadGato", edad);
        intent.putExtra("tamanoGato", tamano);
        intent.putExtra("sexoGato", sexo);
        intent.putExtra("vacunaGato", vacunado);
        intent.putExtra("esterilizadoGato", esterilizado);
        intent.putExtra("fotoGato", foto);
        intent.putExtra("fundacionGato", fundacion);
        intent.putExtra("emailGato", email);
        intent.putExtra("discapacitadoGato", discapacitado);

        startActivity(intent);
    }

    @Override
    public void onShareclick(Bebe bebe, ImageView img, TextView sexo, TextView edad) {

        try {
            Drawable dr = ((ImageView) img).getDrawable();
            Bitmap bitmap =  ((GlideBitmapDrawable)dr.getCurrent()).getBitmap();
            //Bitmap bitmap = ((GlideBitmapDrawable) img.getDrawable()).getBitmap();
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null);
            Uri imageUri = Uri.parse(path);

            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
        }catch (Exception e){
            Toast.makeText(getActivity(), R.string.cargando_fotos, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDeleteClick(Bebe bebe) {
        presenter.removeGato(bebe);
    }

    @Override
    public void onFavClick(Bebe bebe) {

    }
    @Override
    public void onGatoUpload() {
        adapter.notifyDataSetChanged();

    }


}
