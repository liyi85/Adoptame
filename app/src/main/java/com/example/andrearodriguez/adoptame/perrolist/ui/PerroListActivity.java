package com.example.andrearodriguez.adoptame.perrolist.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.main.ui.MainActivity;
import com.example.andrearodriguez.adoptame.perrodetail.ui.DetailPerroActivity;
import com.example.andrearodriguez.adoptame.perrolist.PerroListPresenter;
import com.example.andrearodriguez.adoptame.perrolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.adoptame.perrolist.ui.adapter.PerroListAdapter;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PerroListActivity extends AppCompatActivity implements PerroListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewPerro)
    RecyclerView recyclerViewPerro;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.progresBarAddFundation)
    ProgressBar progresBarAddDgo;

    @Inject
    PerroListAdapter adapter;

    @Inject
    PerroListPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.subscribe();
    }

    private void setupRecyclerView() {
        recyclerViewPerro.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPerro.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPerro.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        BebeAdoptaApp app = (BebeAdoptaApp) getApplication();
        app.getPerroLisComponent(this, this, this).inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle("perros");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_show_all){
            presenter.showAll();
        }else if (id == R.id.action_show_fav){
            presenter.showFavs();
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
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
        progresBarAddDgo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddDgo.setVisibility(View.GONE);

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

        String nombre = bebe.getNombre().toString();
        String edad = bebe.getEdad().toString();
        String tamano = bebe.getTamaño().toString();
        String sexo = bebe.getSexo().toString();
        String vacunado = bebe.getVacunacion();
        String esterilizado = bebe.getEsterilizacion();
        String foto = bebe.getUrl().toString();
        String fundacion = bebe.getFundacion();
        String email = bebe.getEmail();


        Intent intent = new Intent(this, DetailPerroActivity.class);

        intent.putExtra("nombrePerro", nombre);
        intent.putExtra("edadPerro", edad);
        intent.putExtra("tamanoPerro", tamano);
        intent.putExtra("sexoPerro", sexo);
        intent.putExtra("vacunaPerro", vacunado);
        intent.putExtra("esterilizadoPerro", esterilizado);
        intent.putExtra("fotoPerro", foto);
        intent.putExtra("fundacionPerro", fundacion);
        intent.putExtra("emailPerro", email);

        startActivity(intent);
    }


    @Override
    public void onShareclick(Bebe bebe, ImageView img, TextView sexo, TextView edad) {
        Bitmap bitmap = ((GlideBitmapDrawable) img.getDrawable()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
    }


    @Override
    public void onDeleteClick(Bebe bebe) {
        presenter.removePerro(bebe);
    }

    @Override
    public void onPerroUpload() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onFavClick(Bebe bebe) {
        presenter.toggleFavorite(bebe);
    }

}
