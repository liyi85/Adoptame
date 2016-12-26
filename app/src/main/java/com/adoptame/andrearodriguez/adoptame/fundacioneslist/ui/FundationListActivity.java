package com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaApp;
import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Fundacion;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.FundationListPresenter;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.adapters.FundationListAdapter;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.adapters.OnItemClickListenerF;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListActivity extends AppCompatActivity implements FundationListView, OnItemClickListenerF {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewFundation)
    RecyclerView recyclerViewFundation;
    @Bind(R.id.progresBarAddFundation)
    ProgressBar progresBarAddFundation;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    FundationListAdapter adapter;

    @Inject
    FundationListPresenter presenter;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundation);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.subscribe();

    }

    private void setupRecyclerView() {
        recyclerViewFundation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFundation.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        BebeAdoptaApp app = (BebeAdoptaApp) getApplication();
        app.getFundationListComponent(this, this, this).inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_fundacio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public void showList() {
        recyclerViewFundation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewFundation.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddFundation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddFundation.setVisibility(View.GONE);
    }

    @Override
    public void addFundation(Fundacion fundacion) {
        adapter.addFundation(fundacion);
    }

    @Override
    public void onFundationError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onFundationEmail(Fundacion fundacion) {
        String email = fundacion.getEmail().toString();
        if (email != null) {
            Intent intenEmail = new Intent(Intent.ACTION_SEND);
            intenEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intenEmail.setType("message/rfc822");
            startActivity(Intent.createChooser(intenEmail, "Eliga su cliente de Email :"));
        }
    }


    @Override
    public void onFundationCall(Fundacion fundacion) {
        String phoneNumber = fundacion.getTelefono().toString();

        Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(intentPhone);

//
    }
}