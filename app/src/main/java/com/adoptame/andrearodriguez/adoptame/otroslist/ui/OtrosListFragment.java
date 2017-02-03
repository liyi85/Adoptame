package com.adoptame.andrearodriguez.adoptame.otroslist.ui;


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

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaApp;
import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListPresenter;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter.OnItemClickListenerOtros;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter.OtroListAdapter;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 2/2/17.
 */
public class OtrosListFragment extends Fragment implements OtroListView, OnItemClickListenerOtros {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewOtro)
    RecyclerView recyclerViewOtro;
    @Bind(R.id.progresBarAddOtro)
    ProgressBar progresBarAddOtro;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    OtroListAdapter adapter;

    @Inject
    OtrosListPresenter presenter;

    public OtrosListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
    }

    private void setupInjection() {
        BebeAdoptaApp app = (BebeAdoptaApp) getActivity().getApplication();
        app.getOtroListComponent(this, this, this).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otros_list, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return view;
    }

    private void setupRecyclerView() {
        recyclerViewOtro.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewOtro.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOtro.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_title_otros);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewOtro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewOtro.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddOtro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddOtro.setVisibility(View.GONE);

    }

    @Override
    public void addOtro(Bebe bebe) {
        adapter.addOtro(bebe);
    }

    @Override
    public void removeOtro(Bebe bebe) {
        adapter.removeOtro(bebe);
    }

    @Override
    public void onOtroError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOtroUpload() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onOtroClick(Bebe bebe) {
        String nombre = bebe.getNombre();
        String edad = bebe.getEdad();
        String sexo = bebe.getSexo();
        String vacunado = bebe.getVacunacion();
        String esterilizado = bebe.getEsterilizacion();
        String foto = bebe.getUrl();
        String fundacion = bebe.getFundacion();
        String email = bebe.getEmail();
        String discapacitado = bebe.getDiscapacitado();


//        Intent intent = new Intent(getActivity(), DetailOtroActivity.class);
//
//        intent.putExtra("nombreGato", nombre);
//        intent.putExtra("edadGato", edad);
//        intent.putExtra("sexoGato", sexo);
//        intent.putExtra("vacunaGato", vacunado);
//        intent.putExtra("esterilizadoGato", esterilizado);
//        intent.putExtra("fotoGato", foto);
//        intent.putExtra("fundacionGato", fundacion);
//        intent.putExtra("emailGato", email);
//        intent.putExtra("discapacitadoGato", discapacitado);
//
//        startActivity(intent);
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
        presenter.removeOtro(bebe);
    }

    @Override
    public void onFavClick(Bebe bebe) {

    }
}
