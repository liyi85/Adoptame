package com.example.andrearodriguez.adoptame.perrolist.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.addperro.ui.AddPerroFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerroListActivity extends AppCompatActivity implements PerroListView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewPerro)
    RecyclerView recyclerViewPerro;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro);
        ButterKnife.bind(this);
        setupToolbar();


    }

    private void setupToolbar() {
        toolbar.setTitle("perros");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.fab)
    public void addPerro() {
        new AddPerroFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
    }

    @Override
    public void onUploadComplete() {

    }

    @Override
    public void onUploadInit() {

    }

    @Override
    public void onUploadError() {

    }
}
