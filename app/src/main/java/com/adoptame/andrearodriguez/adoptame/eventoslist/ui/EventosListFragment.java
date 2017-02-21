package com.adoptame.andrearodriguez.adoptame.eventoslist.ui;


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
import android.widget.Toast;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaApp;
import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.eventosdetail.ui.DetailEventoActivity;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListPresenter;
import com.adoptame.andrearodriguez.adoptame.eventoslist.adapter.EventosListAdapter;
import com.adoptame.andrearodriguez.adoptame.eventoslist.adapter.OnItemClickListenerEventos;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosListFragment extends Fragment implements EventoListView, OnItemClickListenerEventos{


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewEventos)
    RecyclerView recyclerViewEventos;
    @Bind(R.id.progresBarAddEvento)
    ProgressBar progresBarAddEvento;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    EventosListAdapter adapter;

    @Inject
    EventosListPresenter presenter;

    public EventosListFragment() {
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
        app.getEventosListComponent(this, this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventos_list, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return view;
    }

    private void setupRecyclerView() {
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewEventos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEventos.setAdapter(adapter);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_titles_eventos);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showList() {
        recyclerViewEventos.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideList() {
        recyclerViewEventos.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddEvento.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddEvento.setVisibility(View.GONE);
    }

    @Override
    public void addEvento(Eventos eventos) {
        adapter.addEvento(eventos);
    }

    @Override
    public void removeEvento(Eventos eventos) {
        adapter.removeEvento(eventos);
    }

    @Override
    public void onEventoError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEventoClick(Eventos eventos) {
        String nombreE = eventos.getNombre();
        String lugar = eventos.getLugar();
        String fecha = eventos.getFecha();
        String hora = eventos.getHora();
        String tipo = eventos.getTipoevento();
        String email = eventos.getEmail();
        String fundacion = eventos.getFundacion();

        Intent intent = new Intent(getActivity(), DetailEventoActivity.class);

        intent.putExtra("nombreEvento", nombreE);
        intent.putExtra("lugarEvento", lugar);
        intent.putExtra("fechaEvento", fecha);
        intent.putExtra("horaEvento", hora);
        intent.putExtra("tipoEvento", tipo);
        intent.putExtra("emailE", email);
        intent.putExtra("fundacionE", fundacion);

        startActivity(intent);

    }

    @Override
    public void onShareclick(Eventos eventos, ImageView img) {
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
    public void onDeleteClick(Eventos eventos) {
        presenter.removeEvento(eventos);
    }



    @Override
    public void onEventoUpload() {
        adapter.notifyDataSetChanged();
    }
}
