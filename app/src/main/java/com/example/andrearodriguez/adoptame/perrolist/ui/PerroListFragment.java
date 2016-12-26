package com.example.andrearodriguez.adoptame.perrolist.ui;


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
import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.perrodetail.ui.DetailPerroActivity;
import com.example.andrearodriguez.adoptame.perrolist.PerroListPresenter;
import com.example.andrearodriguez.adoptame.perrolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.adoptame.perrolist.ui.adapter.PerroListAdapter;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerroListFragment extends Fragment implements PerroListView, OnItemClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewPerro)
    RecyclerView recyclerViewPerro;
    @Bind(R.id.progresBarAddPerro)
    ProgressBar progresBarAddPerro;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;


    @Inject
    PerroListAdapter adapter;

    @Inject
    PerroListPresenter presenter;

    public PerroListFragment() {
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
        app.getPerroLisComponent(this, this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perro_list, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return view;
    }



    private void setupRecyclerView() {
        recyclerViewPerro.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPerro.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPerro.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_titles_perros);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewPerro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewPerro.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddPerro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddPerro.setVisibility(View.GONE);

    }



    @Override
    public void addPerro(Bebe bebe) {
        adapter.addPerro(bebe);
    }

    @Override
    public void removePerro(Bebe bebe) {
        adapter.removePerro(bebe);
    }

    @Override
    public void onPerroError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onPerroClick(Bebe bebe) {

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


        Intent intent = new Intent(getActivity(), DetailPerroActivity.class);

        intent.putExtra("nombrePerro", nombre);
        intent.putExtra("edadPerro", edad);
        intent.putExtra("tamanoPerro", tamano);
        intent.putExtra("sexoPerro", sexo);
        intent.putExtra("vacunaPerro", vacunado);
        intent.putExtra("esterilizadoPerro", esterilizado);
        intent.putExtra("fotoPerro", foto);
        intent.putExtra("fundacionPerro", fundacion);
        intent.putExtra("emailPerro", email);
        intent.putExtra("discapacitadoPerro", discapacitado);


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
        presenter.removePerro(bebe);
    }

    @Override
    public void onFavClick(Bebe bebe) {

    }

    @Override
    public void onPerroUpload() {
        adapter.notifyDataSetChanged();
    }



}
